package edu.uptc.view;

import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class MainStadistics extends JFrame {
	private JLabel title;
	private DefaultTableModel defaultTable;
	private JTable stadistics;
	private JScrollPane scroll;
	private DefaultTableCellRenderer tcr;
	
	public MainStadistics() {
		setResizable(true);
		setLayout(null);
		setSize(670, 300);
		setLocation(700,300);
		setTitle("Estadisticas por modulos");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		initialize();
		characteristics();
		addElements();
	}
	
	private void initialize() {
		title = new JLabel("ESTADISTICAS DE ATENCION DE MODULOS");
		String[] titles = {"MODULO", "TRAMITE", "USUARIOS ATENDIDOS", "TIEMPO (HH:MM:SS:MS)"};
		defaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		defaultTable.setColumnIdentifiers(titles);
		stadistics = new JTable(defaultTable);
		stadistics.setRowSelectionAllowed(false);
		scroll = new JScrollPane(stadistics);
		tcr = new DefaultTableCellRenderer();
	}

	@SuppressWarnings("static-access")
	private void characteristics() {
		title.setFont(new Font("Arial", Font.BOLD, 15));
		title.setBounds(180, -5, 400, 50);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		stadistics.setAutoResizeMode(stadistics.AUTO_RESIZE_ALL_COLUMNS);
		scroll.setBounds(10, 35, 630, 160);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		for (int i = 0; i < 4; i++) {
			stadistics.getColumnModel().getColumn(i).setPreferredWidth(100);
			stadistics.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
	}

	private void addElements() {
		add(title);
		add(scroll);
	}

	public DefaultTableModel getDefaultTable() {
		return defaultTable;
	}

	public JTable getStadistics() {
		return stadistics;
	}

	public void setDefaultTable(DefaultTableModel defaultTable) {
		this.defaultTable = defaultTable;
	}

	public void setStadistics(JTable stadistics) {
		this.stadistics = stadistics;
	}
}
