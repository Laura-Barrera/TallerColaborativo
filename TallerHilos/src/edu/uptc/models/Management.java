package edu.uptc.models;

import java.util.ArrayList;
import java.util.LinkedList;

public class Management {
	private LinkedList<User> usuarios;
	private ArrayList<Procedure> formalities;
	private ArrayList<Module> modulesA, modulesC, modulesM, modulesL;
	
	public Management() {
		usuarios = new LinkedList<User>();
		formalities = new ArrayList<Procedure>();
		modulesA = new ArrayList<Module>();
		modulesC = new ArrayList<Module>();
		modulesM = new ArrayList<Module>();
		modulesL = new ArrayList<Module>();
		
		addModules();
		addFormalities();
		runShifts();
	}
	
	private void addModules() {
		modulesA.add(new Module("Modulo Uno", "Autorizaciones"));
		modulesA.add(new Module("Modulo Dos", "Autorizaciones"));
		
		modulesC.add(new Module("Modulo Tres", "Asignacion_citas"));
		modulesC.add(new Module("Modulo Cuatro", "Asignacion_citas"));
		
		modulesM.add(new Module("Modulo Cinco", "Entrega_medicamentos"));
		modulesM.add(new Module("Modulo Seis", "Entrega_medicamentos"));
		
		modulesL.add(new Module("Modulo Siete", "Laboratorios"));
		modulesL.add(new Module("Modulo Ocho", "Laboratorios"));
	}
	
	
	private void addFormalities() {
		formalities.add(new Procedure("Autorizaciones", modulesA));
		formalities.add(new Procedure("Asignacion_citas", modulesC));
		formalities.add(new Procedure("Entrega_medicamentos", modulesM));
		formalities.add(new Procedure("Laboratorios", modulesL));
	}
	
	public String addUser(String id, String procedure, String turn) {
		if(id.equals("")) {
			return "Datos incompletos";
		} else if(verifInputs(id)){
			User user = new User(id, procedure, turn);
			for(int i = 0; i < formalities.size(); i++) {
				if(formalities.get(i).getTitle().equals(procedure)) {
					formalities.get(i).getUsers().add(user);
					usuarios.add(user);
				}
			}
			return "Documento: "+user.getId()+"\nTurno: "+user.getTurn();
		} else {
			return "Los datos ingresados no son correctos";
		}
	}
	
	private boolean verifInputs(String id) {
		int lenght = id.length();
		int counter = 0;
		for(int i = 0; i < lenght; i++) {
			if((int)id.charAt(i) >= 48 && (int)id.charAt(i) <= 57) {
				counter++;
			}
		}
		if(counter == lenght) {
			return true;
		} else {
			return false;
		}
	}

	public String [][] showUsers(){
		String[][] output = new String[usuarios.size()][3];
		for(int i = 0; i < usuarios.size(); i++) {
			if(!usuarios.get(i).isStatus()) {
				output[i][0] = usuarios.get(i).getId();
				output[i][1] = usuarios.get(i).getProcedure();
				output[i][2] = usuarios.get(i).getTurn();
			}
		}
		return output;
	}

	
	public String turn(String procedure) {
		for(int i = 0; i < formalities.size(); i++) {
			if(formalities.get(i).getTitle().equals("Autorizaciones") && formalities.get(i).getTitle().equals(procedure)) {
				return "A"+formalities.get(i).getTurn();
			} else if(formalities.get(i).getTitle().equals("Asignacion_citas") && formalities.get(i).getTitle().equals(procedure)) {
				return "C"+formalities.get(i).getTurn();
			} else if(formalities.get(i).getTitle().equals("Entrega_medicamentos") && formalities.get(i).getTitle().equals(procedure)) {
				return "M"+formalities.get(i).getTurn();
			} else if(formalities.get(i).getTitle().equals("Laboratorios") && formalities.get(i).getTitle().equals(procedure)) {
				return "L"+formalities.get(i).getTurn();
			}
		}
		return "Tramite incorrecto";
	}
	
	public int numModules() {
		int number = 0;
		for(int i = 0; i < formalities.size(); i++) {
			number += formalities.get(i).getModules().size();
		}
		return number;
	}
	
	public String[][] showData(){
		String[][] output = new String[numModules()][4];
		int count = 0;
		for(int i = 0; i < formalities.size(); i++) {
			for(int j = 0; j < formalities.get(i).getModules().size(); j++) {
				output[count][0] = formalities.get(i).getModules().get(j).getTitle();
				output[count][1] = formalities.get(i).getModules().get(j).getProcedure();
				output[count][2] = formalities.get(i).getModules().get(j).getRunnableModule().isStatus() ? "Ocupado" : "Libre";
				output[count][3] = formalities.get(i).getModules().get(j).getRunnableModule().getUser().getTurn();
					count++;
				if(count == numModules()) {
					break;
				}
				}
			}
		return output;
	}
	
	public String[][] showStadistics(){
		return null;
	}
	
	//Metodo para que inicie el hilo de atender turnos para cada uno de los tramites
	public void runShifts() {
		new Thread (( ) -> {
			while(true) {
				for(int i = 0; i < formalities.size(); i++) {
					formalities.get(i).attendShift();
				}
			}
		}).start();
	}
}