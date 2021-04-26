package edu.uptc.view;

import javax.swing.*;

import edu.uptc.control.Control;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements Actions{
	private PanelUserRegistration userRegistration;
	private PanelModule module;
	private PanelWaitingRoom waitingRoom;
	private MainStadistics mainStadistics;
	
	public MainWindow() {
		setResizable(true);
		setLayout(null);
		setSize(800, 720);
		setLocation(10,10);
		setTitle("ATENCION A USUARIOS NUEVA EPS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialize();
		characteristics();
		addElements();
	}

	private void initialize() {
		userRegistration = new PanelUserRegistration();
		module = new PanelModule();
		waitingRoom = new PanelWaitingRoom();
		mainStadistics = new MainStadistics();
	}
	
	private void characteristics() {
		userRegistration.setBounds(10, 10, 760, 90);
		module.setBounds(10, 108, 760, 340);
		waitingRoom.setBounds(10, 455, 760, 215);
	}

	private void addElements() {
		add(userRegistration);
		add(module);
		add(waitingRoom);
	}
	
	public void startProgram(Control control) {
		toAssignListener(control);
		setVisible(true);
	}
	
	public void toAssignListener(Control control) {
		userRegistration.toAssing(control);
		module.toAssing(control);
	}

	@Override
	public void activate(String seccion) {
		if(seccion.equals(Actions.ACCEPT)) {
			add(waitingRoom);
		} else if(seccion.equals(Actions.RUNMODULES)) {
			add(module);
		} else if(seccion.equals(Actions.STADISTICS)) {
			mainStadistics.setVisible(true);
		}
		repaint();
		validate();
	}

	@Override
	public void deactivate(String seccion) {
		if(seccion.equals(Actions.ACCEPT)) {
			remove(waitingRoom);
		} else if(seccion.equals(Actions.RUNMODULES)) {
			remove(module);
		} else if(seccion.equals(Actions.STADISTICS)) {
			mainStadistics.setVisible(false);
		}
		repaint();
		validate();
	}

	@Override
	public String[] catchInfo(String seccion) {
		if(seccion.equals(Actions.ACCEPT)) {
			String[] input = {
					userRegistration.getIdUser().getText(),
					""+userRegistration.getJcbFormalities().getSelectedItem(),
					""
			};
			return input;
		}
		return null;
	}

	@Override
	public void extraMethod(String seccion) {
		if(seccion.equals(Actions.RUNMODULES)) {
			for (int i = 0; i < module.getDefaultTable().getRowCount(); i++) {
				module.getDefaultTable().removeRow(i);
				i--;
			}
		}else if(seccion.equals(Actions.ACCEPT)) {
			userRegistration.getIdUser().setText("");
			userRegistration.getJcbFormalities().setSelectedIndex(0);
		}else if(seccion.equals(Actions.STADISTICS)) {
			for (int i = 0; i < mainStadistics.getDefaultTable().getRowCount(); i++) {
				mainStadistics.getDefaultTable().removeRow(i);
				i--;
			}
		}else if(seccion.equals(Actions.WAITINGROOM)) {
			for(int i = 0; i < waitingRoom.getUserWaitingRoom().getRowCount(); i++) {
				waitingRoom.getDefaultTable().removeRow(i);
				i--;
			}
		}
	}

	@Override
	public void show(String[][] salidas, String seccion) {
		if(seccion.equals(Actions.ACTIVATE)) {
			for (int i = 0; i < salidas.length; i++) {
				module.getDefaultTable().addRow(salidas[i]);
			}
		}else if(seccion.equals(Actions.ACCEPT)) {
			for (int i = 0; i < salidas.length; i++) {
				waitingRoom.getDefaultTable().addRow(salidas[i]);
			}
		}else if(seccion.equals(Actions.RUNMODULES)) {
			for(int i = 0; i < salidas.length; i++) {
				module.getDefaultTable().addRow(salidas[i]);
			}
		}else if(seccion.equals(Actions.STADISTICS)) {
			for(int i = 0; i < salidas.length; i++) {
				mainStadistics.getDefaultTable().addRow(salidas[i]);
			}
		}
		repaint();
        validate();
	}

	@Override
	public void message(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}	
}
