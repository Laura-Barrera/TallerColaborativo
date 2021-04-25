package edu.uptc.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelWaitingRoom extends JPanel{
	private JLabel title;
	private DefaultTableModel defaultTable;
	private JTable userWaitingRoom;
	private JScrollPane scroll;
	private DefaultTableCellRenderer tcr;
	
	public PanelWaitingRoom() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		setBackground(Color.white);
		initialize();
		characteristics();
		addElements();
	}

	private void initialize() {
		title = new JLabel("SALA DE ESPERA");
		String[] titles = {"IDENTIFICACION", "TRAMITE", "TURNO"};
		defaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		defaultTable.setColumnIdentifiers(titles);
		userWaitingRoom = new JTable(defaultTable);
		userWaitingRoom.setRowSelectionAllowed(false);
		scroll = new JScrollPane(userWaitingRoom);
		tcr = new DefaultTableCellRenderer();
	}

	@SuppressWarnings("static-access")
	private void characteristics() {
		title.setFont(new Font("Arial", Font.BOLD, 15));
		title.setBounds(320, -5, 200, 50);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		userWaitingRoom.setAutoResizeMode(userWaitingRoom.AUTO_RESIZE_ALL_COLUMNS);
		scroll.setBounds(10, 35, 740, 165);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		for (int i = 0; i < 3; i++) {
			userWaitingRoom.getColumnModel().getColumn(i).setPreferredWidth(100);
			userWaitingRoom.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
	}

	private void addElements() {
		add(title);
		add(scroll);
	}
	
	public DefaultTableModel getDefaultTable() {
		return defaultTable;
	}

	public JScrollPane getScroll() {
		return scroll;
	}
	
	public JTable getUserWaitingRoom() {
		return userWaitingRoom;
	}

	public void setDefaultTable(DefaultTableModel defaultTable) {
		this.defaultTable = defaultTable;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}	
}