package edu.uptc.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.uptc.models.Management;
import edu.uptc.models.Procedure;
import edu.uptc.models.Module;

public class PersistenceFormalities {
	
	public static void cargarDatos(Management management) {
		JSONObject objectManagement = new JSONObject();
		JSONArray arrayFormalities = new JSONArray();
		try {
			FileWriter fileWriter = new FileWriter("src\\edu\\uptc\\files\\archivo.json");
			for(int i = 0; i < management.getFormalities().size(); i++) {
				JSONObject objectProcedure = new JSONObject();
				objectProcedure.put("title", management.getFormalities().get(i).getTitle());
				
				JSONArray arrayModules = new JSONArray();
				for(int j = 0; j < management.getFormalities().get(i).getModules().size(); j++) {
					JSONObject objectModule = new JSONObject();
					objectModule.put("title", management.getFormalities().get(i).getModules().get(j).getTitle());
					objectModule.put("procedure", management.getFormalities().get(i).getModules().get(j).getProcedure());
					
					arrayModules.add(objectModule);
				}
				objectProcedure.put("modules", arrayModules);
				arrayFormalities.add(objectProcedure);
			}
			objectManagement.put("formalities", arrayFormalities);
			fileWriter.write(objectManagement.toJSONString());
            fileWriter.flush();
            fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Archivo generado");
	}
	
	public static Management descargarDatos() {
		Management management = new Management();
		JSONParser parser = new JSONParser();
		Object archivo = null;
		
		try {
			archivo = parser.parse(new InputStreamReader(new FileInputStream("src\\edu\\uptc\\files\\archivo.json")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONObject objectEPS = (JSONObject) archivo;
		JSONArray arrayproce = (JSONArray) objectEPS.get("formalities");
		Iterator iteradorProcedimientos = arrayproce.iterator();
		ArrayList<Procedure> procedures = new ArrayList<Procedure>();

		while (iteradorProcedimientos.hasNext()) {
		    JSONObject objectProcedimiento = (JSONObject) iteradorProcedimientos.next();
		    String nombre = (String) objectProcedimiento.get("title");

		    JSONArray arrayModules = (JSONArray) objectProcedimiento.get("modules");
		    Iterator iteradorModule = arrayModules.iterator();
		    ArrayList<Module> modules = new ArrayList<Module>();

		    while (iteradorModule.hasNext()) {
		        JSONObject objetoModule = (JSONObject) iteradorModule.next();
		        String id = (String) objetoModule.get("title");
		        String procedure = (String) objetoModule.get("procedure");
		        
		        Module module= new Module(id, procedure);

		        modules.add(module);
		    }

		    Procedure procedimiento = new Procedure(nombre, modules);

		    procedures.add(procedimiento);
		}

		management.setFormalities(procedures);
		return management;
	}
}