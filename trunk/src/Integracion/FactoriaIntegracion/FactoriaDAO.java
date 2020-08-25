package Integracion.FactoriaIntegracion;

import Integracion.Pase.DAOPase;
import Integracion.Proyeccion.DAOProyeccion;
import Integracion.Venta.DAOVenta;

public abstract class FactoriaDAO {

	private static FactoriaDAO Instance = null;

	public synchronized static FactoriaDAO getInstance(){
		if(Instance == null){
			Instance = new FactoriaDAOimp();
		}
		return Instance;
	}
	
	public abstract DAOPase generateDAOPase();

	public abstract DAOProyeccion generateDAOProyeccion();

	public abstract DAOVenta generateDAOVenta();
}