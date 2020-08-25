package Presentacion.Venta;

import javax.swing.JPanel;

public abstract class VistaVentas extends JPanel {
	
private static VistaVentasIMP instancia;
	
	public static VistaVentasIMP getInstance(){
		if(instancia == null){
			instancia = new VistaVentasIMP();
		}
		return instancia;
	}

}