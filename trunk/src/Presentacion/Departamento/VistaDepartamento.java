package Presentacion.Departamento;

import javax.swing.JPanel;

public abstract class VistaDepartamento extends JPanel {
	
private static VistaDepartamentoIMP instancia;
	
	public static VistaDepartamentoIMP getInstance(){
		if(instancia == null){
			instancia = new VistaDepartamentoIMP();
		}
		return instancia;
	}

}