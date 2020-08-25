package Presentacion.Pase;

import javax.swing.JPanel;

public abstract class VistaPase extends JPanel {
	
private static VistaPaseIMP instancia;
	
	public static VistaPaseIMP getInstance(){
		if(instancia == null){
			instancia = new VistaPaseIMP();
		}
		return instancia;
	}

}