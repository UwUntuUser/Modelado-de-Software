package Presentacion.Empleado;

import javax.swing.JPanel;

public abstract class VistaEmpleado extends JPanel {
	
private static VistaEmpleadoIMP instancia;
	
	public static VistaEmpleadoIMP getInstance(){
		if(instancia == null){
			instancia = new VistaEmpleadoIMP();
		}
		return instancia;
	}

}