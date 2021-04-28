package edu.uptc.models;

import java.util.ArrayList;
import java.util.LinkedList;

public class Management {
	private LinkedList<User> usuarios;
	private ArrayList<Procedure> formalities;
	
	public Management() {
		usuarios = new LinkedList<User>();
		formalities = new ArrayList<Procedure>();
		
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
					runShifts();
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
	
	public String[][] tramites(){
        String[][] nombresProcedimientos = new String[1][formalities.size()];
        for (int i = 0; i < formalities.size(); i++) {
            nombresProcedimientos[0][i] = formalities.get(i).getTitle();
        }


        return nombresProcedimientos;
    }

	public String [][] showUsers(){
		String[][] output = new String[usuarios.size()][3];
		for(int i = 0; i < usuarios.size(); i++) {
			if(usuarios.get(i).isStatus()) {
				usuarios.remove(i);
			} else if(!usuarios.get(i).isStatus()) {
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
	
	private int numModules() {
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
		String[][] output = new String[numModules()][4];
		int count = 0;
		for(int i = 0; i < formalities.size(); i++) {
			for(int j = 0; j < formalities.get(i).getModules().size(); j++) {
				output[count][0] = formalities.get(i).getModules().get(j).getTitle();
				output[count][1] = formalities.get(i).getModules().get(j).getProcedure();
				output[count][2] = ""+formalities.get(i).getModules().get(j).numUsersServed();
				output[count][3] = formalities.get(i).getModules().get(j).timeWorked();
					count++;
				if(count == numModules()) {
					break;
				}
				}
			}
		return output;
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

	public ArrayList<Procedure> getFormalities() {
		return formalities;
	}

	public void setFormalities(ArrayList<Procedure> formalities) {
		this.formalities = formalities;
	}
}