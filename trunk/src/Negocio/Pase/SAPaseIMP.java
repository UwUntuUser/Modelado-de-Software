/**
 * 
 */
package Negocio.Pase;

import java.util.ArrayList;

import com.mysql.cj.Query;

import Integracion.FactoriaIntegracion.FactoriaDAO;
import Integracion.Pase.DAOPase;
import Integracion.Proyeccion.DAOProyeccion;
import Integracion.Query.FactoriaQuery;
import Integracion.Query.PelisPorGenero;
import Integracion.Query.PelisPorDuracion;
import Integracion.Transactionmanager.*;
import Negocio.Proyeccion.TPelicula;
import Negocio.Proyeccion.TProyeccion;

public class SAPaseIMP implements SAPase 
{

	public int crearPase(TPase tPase) 
	{
		int id = -1;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();

		if (transaccion != null) {
			transaccion.start();
			DAOPase DAOPase = FactoriaDAO.getInstance().generateDAOPase();
			TPase pase = DAOPase.leerPase(tPase.getID_Pase());
			if(pase == null) {
				DAOProyeccion DAOProyeccion = FactoriaDAO.getInstance().generateDAOProyeccion();
				TProyeccion proy = DAOProyeccion.read(tPase.getID_Proyeccion());
				if(proy != null && proy.getActivo()) {
					id = DAOPase.crearPase(tPase);
					if (id < 0) 
					{
						transaccion.rollback();
					} 
					else {
						transaccion.commit();
					}
				}
				else{
					transaccion.rollback();
				}
				
			}
			else {// si existe, reactivacion
				if(pase.getActivo())
					transaccion.rollback();
				else{
					 id = DAOPase.actualizarPase(pase, pase.getID_Pase());
					 if(id != -1)
						 transaccion.commit();
					 else
						 transaccion.rollback();
				}
			}
		}
		tManager.eliminaTransaccion();
		return id;
	}

	public boolean eliminarPase(int id) 
	{
		boolean ok = false;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();

		if (transaccion != null) {
			transaccion.start();
			DAOPase daoPase = FactoriaDAO.getInstance().generateDAOPase();
			if (daoPase.leerPase(id) != null) {
				if (daoPase.eliminarPase(id) != -1) {
					ok = true;
					transaccion.commit();
				} else
					transaccion.rollback();
			} else
				transaccion.rollback();
		}
		tManager.eliminaTransaccion();
		return ok;
	}

	public boolean actualizarPase(TPase tPase) {
		boolean ok = false;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if (transaccion != null) {
			transaccion.start();
			DAOPase daoPase = FactoriaDAO.getInstance().generateDAOPase();
			DAOProyeccion DAOProy = FactoriaDAO.getInstance().generateDAOProyeccion();
			//comprobar que la proyeccion está activo
			TProyeccion proy = DAOProy.read(tPase.getID_Proyeccion());
			if(proy == null || proy.getActivo() == false)
			{
				transaccion.rollback();
			}
			//Si la proyeccion existe y está activa
			if (daoPase.leerPase(tPase.getID_Pase()) != null) {
				if (daoPase.actualizarPase(tPase, tPase.getID_Pase()) != -1) {
					transaccion.commit();
					ok = true;
				} else {
					transaccion.rollback();
				}
			} else
				transaccion.rollback();
		}
		tManager.eliminaTransaccion();
		return ok;
	}

	public TPase leerPase(int ID) {
		TPase pase = null;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if (transaccion != null) {
			transaccion.start();
			pase = FactoriaDAO.getInstance().generateDAOPase().leerPase(ID);
			if (pase != null && pase.getActivo())
				transaccion.commit();
			else
				transaccion.rollback();
		}
		tManager.eliminaTransaccion();
		return pase;
	}

	public ArrayList<TPase> leerTodos() {
		ArrayList<TPase> listaPases = null;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if (transaccion != null) {
			transaccion.start();
			DAOPase DAOP = FactoriaDAO.getInstance().generateDAOPase();
			listaPases = DAOP.leerTodos();
			if (listaPases != null) {
				transaccion.commit();
			} else {
				transaccion.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return listaPases;
	}

	public ArrayList<TPase> leerPasesPorHora() {
		ArrayList<TPase> listaPases = null;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if (transaccion != null) {
			transaccion.start();
			DAOPase DAOP = FactoriaDAO.getInstance().generateDAOPase();
			listaPases = DAOP.leerPasesPorHora();
			if (listaPases != null) {
				transaccion.commit();
			} else {
				transaccion.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return listaPases;
	}

	@Override
	public ArrayList<TPelicula> pelisPorGenero(String genero) {
		FactoriaQuery fq = FactoriaQuery.getInstance();
		PelisPorGenero query = fq.generarPelisPorGenero();
		TransactionManagerSingleton tm  = TransactionManagerSingleton.getInstancia();
		tm.nuevaTransaccion();
		Transaccion transaccion = tm.getTransaccion();
		transaccion.start();
		
		ArrayList<TPelicula> result = new ArrayList<TPelicula>();
		
		result = (ArrayList<TPelicula>) query.execute(genero);
		transaccion.commit();
		tm.eliminaTransaccion();
		return result;
	}

	@Override
	public ArrayList<TPelicula> pelisPorDuracion(String duracion) {
		FactoriaQuery fq = FactoriaQuery.getInstance();
		PelisPorDuracion query = fq.generarPelisPorHora();
		TransactionManagerSingleton tm  = TransactionManagerSingleton.getInstancia();
		tm.nuevaTransaccion();
		Transaccion transaccion = tm.getTransaccion();
		transaccion.start();
		
		ArrayList<TPelicula> result = new ArrayList<TPelicula>();
		
		
		result = (ArrayList<TPelicula>) query.execute(duracion);
		transaccion.commit();
		tm.eliminaTransaccion();
		return result;
	}
}