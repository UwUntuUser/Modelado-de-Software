package Presentacion.Proyeccion;

import javax.swing.JPanel;

public abstract class VistaProyeccion extends JPanel {
	
private static VistaProyeccionIMP instancia;
	
	public static VistaProyeccionIMP getInstance(){
		if(instancia == null){
			instancia = new VistaProyeccionIMP();
		}
		return instancia;
	}

}