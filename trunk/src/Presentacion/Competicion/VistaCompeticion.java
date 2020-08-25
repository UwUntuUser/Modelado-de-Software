package Presentacion.Competicion;

import javax.swing.JPanel;

public abstract class VistaCompeticion extends JPanel {
	
private static VistaCompeticionIMP instancia;
	
	public static VistaCompeticionIMP getInstance(){
		if(instancia == null){
			instancia = new VistaCompeticionIMP();
		}
		return instancia;
	}

}