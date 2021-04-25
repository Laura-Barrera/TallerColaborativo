package edu.uptc.models;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Procedure {
	private String title;
	private ArrayList<Module> modules;
	private ArrayDeque<User> users;
	private int turn;
	
	public Procedure(String title, ArrayList<Module> modules) {
		this.title = title;
		this.modules = modules;
		users = new ArrayDeque<User>();
	}

	public String getTitle() {
		return title;
	}

	public ArrayList<Module> getModules() {
		return modules;
	}

	public ArrayDeque<User> getUsers() {
		turn++;
		return users;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setModules(ArrayList<Module> modules) {
		this.modules = modules;
	}

	public void setUsers(ArrayDeque<User> users) {
		this.users = users;
	}
	
	public int getTurn() {
		return turn;
	}

	//Atiende un turno si el modulo para el procedimiento esta vacio
	public void attendShift() {
		//Recorre el arreglo de modulos creados para el tramite
		for(int i = 0; i < modules.size(); i++) {
			//Compara si el modulo esta libre y si la cola de usuarios no esta vacia
			if(!modules.get(i).getRunnableModule().isStatus()) {
				if(!users.isEmpty()) {
					//Activa el metodo de trabajar que implementa el hilo
					modules.get(i).work(users.pop());
				}
			}
		}
	}
}