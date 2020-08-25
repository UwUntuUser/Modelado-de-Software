	package Negocio.Venta;

import Negocio.Venta.TLinea_Venta;
import Negocio.Venta.TVenta;
import java.util.ArrayList;

public interface SAVenta {

	public int crearVenta();

	public TVenta leerVenta(int ID);

	public boolean aniadirLineaDeVenta(TLinea_Venta linea_venta);

	public ArrayList<TVenta> leerTodas();

	public boolean eliminarVenta(int ID);

	public double cerrarVenta(int ID);
	
}