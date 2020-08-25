/**
 * 
 */
package Negocio.Proyeccion;

import java.util.ArrayList;

import Integracion.FactoriaIntegracion.FactoriaDAO;
import Integracion.Pase.DAOPase;
import Integracion.Proyeccion.DAOProyeccion;
import Integracion.Transactionmanager.Transaccion;
import Integracion.Transactionmanager.TransactionFactorySingleton;
import Integracion.Transactionmanager.TransactionManagerSingleton;
import Negocio.Pase.TPase;

public class SAProyeccionImp implements SAProyeccion 
{
	/** 
	* (non-Javadoc)
	* @see SAProyeccion#eliminarProyeccion(int id)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public boolean eliminarProyeccion(int id) 
	{
		boolean ok = false;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		
		if(transaccion!=null)
		{
			transaccion.start();
			DAOProyeccion DAOProyeccion = FactoriaDAO.getInstance().generateDAOProyeccion();
			DAOPase DAOPase = FactoriaDAO.getInstance().generateDAOPase();
			if(DAOProyeccion.read(id) != null)
			{
				ArrayList<TPase> listaPases = DAOPase.leerTodos();
				int i = 0;
				boolean existe = false;
				
				while(!existe && listaPases != null && i < listaPases.size())
				{
					TPase p = listaPases.get(i);
					if(p.getID_Proyeccion() == id)
					{
						existe = true;
					}
					i++;
				}
				
				if(existe == false) //Si no existen pases de esa Proy
				{
					if(DAOProyeccion.delete(id))
					{
						ok = true;
						transaccion.commit();
					}
					else
					{
						transaccion.rollback();
					}
				}	
			}
			else //Si esa proyección tiene pases
			{
				transaccion.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return ok;
	}

	/** 
	* (non-Javadoc)
	* @see SAProyeccion#mostrar(int id)
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TProyeccion mostrar(int id) 
	{
		TProyeccion pr = null;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if(transaccion != null)
		{
			transaccion.start();
			DAOProyeccion DAOPr = FactoriaDAO.getInstance().generateDAOProyeccion();
			pr = DAOPr.read(id);
			if(pr != null && pr.getActivo())
			{
				transaccion.commit();
			}	
			else
			{
				transaccion.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return pr;
	}

	/** 
	* (non-Javadoc)
	* @see SAProyeccion#mostrarTodas()
	* @generated "UML to Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	
	public ArrayList<TProyeccion> mostrarTodas() 
	{
		ArrayList<TProyeccion> lista_proyeccion = new ArrayList<TProyeccion>();
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if(transaccion != null)
		{
			transaccion.start();
			DAOProyeccion DAOPr= FactoriaDAO.getInstance().generateDAOProyeccion();
			lista_proyeccion = DAOPr.readAll();
			if(lista_proyeccion != null){
				transaccion.commit();
			}
			else{
				transaccion.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return lista_proyeccion;
	}

	@Override
	public int crearProyeccion(TProyeccion tProyeccion) 
	{
		int n = -1;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if(transaccion!=null)
		{
			transaccion.start();
			DAOProyeccion DAOProyeccion = FactoriaDAO.getInstance().generateDAOProyeccion();
			//Comprobamos si la proyección ya existe
			ArrayList <TProyeccion> listaProy =  DAOProyeccion.readAllTrue();
			boolean enc = false;
			int i = 0;
			TProyeccion auxProyeccion = null;
			while ( i < listaProy.size() && !enc)
			{
				if(tProyeccion.getNombre().equals(listaProy.get(i).getNombre())  &&
					tProyeccion.getGenero().equals(listaProy.get(i).getGenero()) &&
					 tProyeccion.getDuracion() == listaProy.get(i).getDuracion() &&
					  tProyeccion.isEs_Documental() == listaProy.get(i).isEs_Documental())
				{
					enc = true;
					tProyeccion.setID_Proyeccion(listaProy.get(i).getID_Proyeccion());
					auxProyeccion = listaProy.get(i);
				}
				i++;
			}
			//Si la proyeccion no existe
			if(!enc)
			{
				n = DAOProyeccion.createProyeccion(tProyeccion);
				
				if(n == -1)
				{
					transaccion.rollback();
				}
				else
				{
					tProyeccion.setID_Proyeccion(n);
					transaccion.commit();
				}
			}
			//Si la proyeccion existe
			else
			{
				
				boolean ok;
				//Si no está activa
				if(!auxProyeccion.getActivo())
				{
						auxProyeccion.setActivo(true);
						ok = DAOProyeccion.updateProyeccion(tProyeccion.getID_Proyeccion(), auxProyeccion);
					if(!ok)
					{
						transaccion.rollback();
					}
					else
					{
						transaccion.commit();
						n = tProyeccion.getID_Proyeccion();
					}
				}
				//Si está activa
				else
				{
					transaccion.rollback();
				}
			}
		}
		tManager.eliminaTransaccion();
		return n;
	}

	@Override
	public boolean modificarProyeccion(TProyeccion tProyeccion) 
	{
		boolean ok = false;
		TransactionManagerSingleton tManager = TransactionManagerSingleton.getInstancia();
		Transaccion transaccion = tManager.nuevaTransaccion();
		if(transaccion != null)
		{
			DAOProyeccion DAOProyeccion = FactoriaDAO.getInstance().generateDAOProyeccion();
			TProyeccion aux = DAOProyeccion.read(tProyeccion.getID_Proyeccion());
			if(aux != null && aux.isEs_Documental() ==tProyeccion.isEs_Documental())
			{
				boolean update = false;
				update = DAOProyeccion.updateProyeccion(tProyeccion.getID_Proyeccion(), tProyeccion);
				if(update)
				{
					transaccion.commit();
					ok = true;
				}
				else
				{
					transaccion.rollback();
				}
			}
			else
			{
				transaccion.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return ok;
	}
}