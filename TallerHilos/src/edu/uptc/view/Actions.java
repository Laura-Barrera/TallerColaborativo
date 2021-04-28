package edu.uptc.view;

public interface Actions {
	public static String ACCEPT = "accept";
	public static String ACTIVATE = "activate";
	public static String MODULES = "modules";
	public static String RUNMODULES = "runModules";
	public static String STADISTICS = "stadistics";
	public static String WAITINGROOM = "waitingRoom";
	public static String TRAMITE = "tramite";
	
	public void activate(String seccion);
	public void deactivate(String seccion);
	public String[] catchInfo(String seccion);
	public void extraMethod(String seccion);
	public void show(String[][] salidas, String seccion);
	public void message(String mensaje);	
}