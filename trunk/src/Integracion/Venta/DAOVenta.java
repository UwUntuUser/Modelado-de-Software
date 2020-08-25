package Integracion.Venta;

import Negocio.Venta.TLinea_Venta;
import Negocio.Venta.TVenta;
import java.util.ArrayList;
public interface DAOVenta {
	public TVenta read(int ID);
	public int create();
	public ArrayList<TVenta> readAll();
	public boolean delete(int ID);
	public boolean close(int ID);
	public boolean addLineaVenta(TLinea_Venta tLineaVenta);
}