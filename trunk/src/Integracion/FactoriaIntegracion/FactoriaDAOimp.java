package Integracion.FactoriaIntegracion;

import Integracion.Pase.DAOPase;
import Integracion.Pase.DAOPaseImp;
import Integracion.Proyeccion.DAOProyeccion;
import Integracion.Proyeccion.DAOProyeccionImp;
import Integracion.Venta.DAOVenta;
import Integracion.Venta.DAOVentaImp;

public class FactoriaDAOimp extends FactoriaDAO {
	public DAOPase generateDAOPase() 
	{
		return new DAOPaseImp();
	}

	public DAOProyeccion generateDAOProyeccion() 
	{
		return new DAOProyeccionImp();
	}

	public DAOVenta generateDAOVenta() 
	{
		return new DAOVentaImp();
	}
}