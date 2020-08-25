package Negocio.Venta;

import Negocio.Pase.SAPase;
import Negocio.Pase.SAPaseIMP;
import Negocio.Pase.TPase;
import Negocio.Venta.TLinea_Venta;
import Negocio.Venta.TVenta;
import java.util.ArrayList;

import Integracion.FactoriaIntegracion.FactoriaDAO;
import Integracion.Transactionmanager.Transaccion;
import Integracion.Transactionmanager.TransactionManagerSingleton;
import Integracion.Venta.DAOVenta;

public class SAVentaImp implements SAVenta {

	public int crearVenta() 
	{
		int id = -1;
		TransactionManagerSingleton tManager=TransactionManagerSingleton.getInstancia();
		Transaccion transaction = tManager.nuevaTransaccion();
		if(!transaction.equals(null))
		{
			transaction.start();
			DAOVenta DAOVenta = FactoriaDAO.getInstance().generateDAOVenta();
			id = DAOVenta.create();
			if(id < 0){
				transaction.rollback();
			}else{
				transaction.commit();
			}
			tManager.eliminaTransaccion();
		}
		else
		{
			transaction.rollback();
		}
		
		return id;
	}

	public TVenta leerVenta(int ID) {
		TVenta venta = null;
		TransactionManagerSingleton tManager=TransactionManagerSingleton.getInstancia();
		Transaccion transaction = tManager.nuevaTransaccion();
		if(!transaction.equals(null))
		{
			transaction.start();
			DAOVenta DAOVenta = FactoriaDAO.getInstance().generateDAOVenta();
			venta = DAOVenta.read(ID);
			if(venta == null){
				transaction.rollback();
			}else{
				transaction.commit();
			}		
		}
		
		tManager.eliminaTransaccion();
		return venta;
	}

	public ArrayList<TVenta> leerTodas() {
		ArrayList<TVenta> lista = new ArrayList<TVenta>();
		TransactionManagerSingleton tManager=TransactionManagerSingleton.getInstancia();
		Transaccion transaction = tManager.nuevaTransaccion();
		if(!transaction.equals(null))
		{
			transaction.start();
			DAOVenta DAOVenta = FactoriaDAO.getInstance().generateDAOVenta();
			lista = DAOVenta.readAll();
			if(lista.isEmpty()){
				transaction.rollback();
			}else {
				transaction.commit();
			}
		}
		tManager.eliminaTransaccion();
		return lista;
	}

	public boolean eliminarVenta(int ID) {
		boolean ok = false;
		TransactionManagerSingleton tManager=TransactionManagerSingleton.getInstancia();
		Transaccion transaction = tManager.nuevaTransaccion();
		if(!transaction.equals(null)){
			transaction.start();
			if(!FactoriaDAO.getInstance().generateDAOVenta().read(ID).equals(null)){
				ok = FactoriaDAO.getInstance().generateDAOVenta().delete(ID);
				if(ok){
					transaction.commit();

				}else{
					transaction.rollback();

				}
			}
			else{
				transaction.rollback();
			}
			tManager.eliminaTransaccion();
		}
		
		return ok;
	}

	public boolean aniadirLineaDeVenta(TLinea_Venta linea_venta) {
		boolean ok = false;
		TransactionManagerSingleton tManager=TransactionManagerSingleton.getInstancia();
		Transaccion transaction = tManager.nuevaTransaccion();
		if(!transaction.equals(null))
		{
		transaction.start();
		TVenta venta = FactoriaDAO.getInstance().generateDAOVenta().read(linea_venta.getID_Venta()); 
		if(venta == null){
				transaction.rollback();
				
		}else{
				TPase pase = FactoriaDAO.getInstance().generateDAOPase().leerPase(linea_venta.getID_Pase());
				if (pase != null) {
					ok = FactoriaDAO.getInstance().generateDAOVenta().addLineaVenta(linea_venta);
					
					if(!ok){
						transaction.rollback();
					}else{
						transaction.commit();
					}
				}
				else {
					transaction.rollback();
				}
			}
		}
		tManager.eliminaTransaccion();
		return ok;
	}
	
	public double cerrarVenta(int ID) {
		boolean ok = false;
		double precio = 0;
		TransactionManagerSingleton tManager=TransactionManagerSingleton.getInstancia();
		Transaccion transaction = tManager.nuevaTransaccion();
		if(!transaction.equals(null))
		{
			transaction.start();
			TVenta vent = FactoriaDAO.getInstance().generateDAOVenta().read(ID);
			if(vent != null){
				for(TLinea_Venta venta : vent.getLinea_Venta()){
					precio = precio + (venta.getPrecio() * venta.getCantidad());
				}
				ok = FactoriaDAO.getInstance().generateDAOVenta().close(ID);
				if(!ok){
					precio = -1;
					transaction.rollback();
				}else{
					transaction.commit();
				}
			}
			else{
				precio = -1;
				transaction.rollback();
			}
		}
		tManager.eliminaTransaccion();
		return precio;
	}
}