package edu.uptc.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.uptc.control.Control;

@SuppressWarnings("serial")
public class PanelModule extends JPanel {
	private JLabel title;
	private DefaultTableModel defaultTable;
	private JTable modules;
	private JScrollPane scroll;
	private DefaultTableCellRenderer tcr;
	private JButton btnStadistics;
	
	public PanelModule() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);
		setBackground(Color.white);
		initialize();
		characteristics();
		addElements();
	}

	private void initialize() {
		title = new JLabel("ATENCION DE MODULOS");
		String[] titles = {"MODULO", "TRAMITE", "ESTADO", "TURNO"};
		defaultTable = new DefaultTableModel() {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		defaultTable.setColumnIdentifiers(titles);
		modules = new JTable(defaultTable);
		modules.setRowSelectionAllowed(false);
		scroll = new JScrollPane(modules);
		tcr = new DefaultTableCellRenderer();
		
		btnStadistics = new JButton("Estadisticas Modulos");
	}

	@SuppressWarnings("static-access")
	private void characteristics() {
		title.setFont(new Font("Arial", Font.BOLD, 15));
		title.setBounds(280, -5, 200, 50);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		modules.setAutoResizeMode(modules.AUTO_RESIZE_ALL_COLUMNS);
		scroll.setBounds(10, 35, 740, 250);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		for (int i = 0; i < 4; i++) {
			modules.getColumnModel().getColumn(i).setPreferredWidth(100);
			modules.getColumnModel().getColumn(i).setCellRenderer(tcr);
		}
		
		btnStadistics.setBounds(550, 300, 200, 25);
	}

	private void addElements() {
		add(title);
		add(scroll);
		add(btnStadistics);
	}
	
	public void toAssing(Control control) {
		btnStadistics.setActionCommand(Actions.STADISTICS);
		btnStadistics.addActionListener(control);
	}

	public DefaultTableModel getDefaultTable() {
		return defaultTable;
	}

	public JTable getModules() {
		return modules;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setDefaultTable(DefaultTableModel defaultTable) {
		this.defaultTable = defaultTable;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}
}