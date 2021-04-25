package edu.uptc.models;

import java.util.ArrayList;

public class Module {
	private String title;
	private String procedure;
	private ArrayList<User> usersServed;	//Personas atendidas
	private Thread threadAttend;			//Hilo para atender a una persona
	public RunnableModule runnableModule;	//Runneble para el hilo
	
	public Module(String title, String procedure) {
		this.title = title;
		this.procedure = procedure;
		this.usersServed = new ArrayList<User>();
		this.runnableModule = new RunnableModule();
	}
		
	//Iniciar a atender a un usuario
	public void work(User user){
		user.setStatus(true);
		runnableModule.setStatus(true);				//Se cambia el estado del modulo
		usersServed.add(user);						//Se agrega el usuario a usuarios atendidos
		runnableModule.setUser(user);				//Se agrega el usuario al hilo
		threadAttend = new Thread(runnableModule);	//Se crea un hilo y se envia el runnable
		threadAttend.start();						//Inicia el hilo	
	}
	
	public String getTitle() {
		return title;
	}

	public String getProcedure() {
		return procedure;
	}

	public ArrayList<User> getUsersServed() {
		return usersServed;
	}

	public Thread getThreadAttend() {
		return threadAttend;
	}

	public RunnableModule getRunnableModule() {
		return runnableModule;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	public void setUsersServed(ArrayList<User> usersServed) {
		this.usersServed = usersServed;
	}

	public void setThreadAttend(Thread threadAttend) {
		this.threadAttend = threadAttend;
	}

	public void setRunnableModule(RunnableModule runnableModule) {
		this.runnableModule = runnableModule;
	}
}