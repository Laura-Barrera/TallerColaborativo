package edu.uptc.run;

import edu.uptc.control.Control;
import edu.uptc.view.MainWindow;

public class Run {

	public static void main(String[] args) {
		MainWindow mainWindow = new MainWindow();
		Control control=new Control(mainWindow);
		mainWindow.startProgram(control);
	}
}