package Integracion.Pase;

import Negocio.Pase.TPase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Integracion.Transactionmanager.Transaccion;
import Integracion.Transactionmanager.TransactionManagerSingleton;


public class DAOPaseImp implements DAOPase {

	public int crearPase(TPase tPase) {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		int id = -1;
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try{
					PreparedStatement st = c.prepareStatement("INSERT INTO pase (hora,idproyeccion,activo) VALUES(?,?,1)",java.sql.Statement.RETURN_GENERATED_KEYS);
					st.setInt(2, tPase.getID_Proyeccion());
					st.setInt(1, tPase.getHora());
					st.executeUpdate();
					ResultSet eSet = st.getGeneratedKeys();
					if(eSet.next()){
						id =eSet.getInt(1);
					}
				}
				catch (SQLException e) {
						e.printStackTrace();
						return id;
				}
			}
		}
		return id;
	}

	public int eliminarPase(int id) {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					PreparedStatement st = c.prepareStatement("UPDATE pase SET activo = 0 WHERE idpase = ?");
					st.setInt(1, id);
					st.executeUpdate();
				}
				catch (SQLException e) {
						e.printStackTrace();
						return -1;
				}
				return id;
			}
		}
		return -1;
		
	}

	public int actualizarPase(TPase tPase, int id) {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
						PreparedStatement st = c.prepareStatement("UPDATE pase SET hora = ?, idproyeccion = ? WHERE idpase = ? and activo = 1");
						st.setInt(1, tPase.getHora());
						st.setInt(2, tPase.getID_Proyeccion());
						st.setInt(3, id);
						st.executeUpdate();
						return id;
					
				}
				catch (SQLException e) {
						e.printStackTrace();
						return -1;
				}
			}
		}
		return -1;
	}

	public TPase leerPase(int ID_Pase) {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					PreparedStatement st = c.prepareStatement("SELECT * FROM pase WHERE idpase = ? and activo = 1 FOR UPDATE");
					st.setInt(1, ID_Pase);
					ResultSet rs = st.executeQuery();
					if(rs.next()){
						TPase pase = new TPase();
						pase.setID_Pase(rs.getInt("idpase"));
						pase.setID_Proyeccion(rs.getInt("idproyeccion"));
						pase.setHora(rs.getInt("hora"));
						rs.close();
						return pase;
					}
				}
				catch (SQLException e) {
						e.printStackTrace();
						return null;
				}
			}
		}
		return null;
	}

	public ArrayList<TPase> leerTodos() {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					PreparedStatement st = c.prepareStatement("SELECT * FROM pase where activo = 1 FOR UPDATE");
					ResultSet rs = st.executeQuery();
					ArrayList<TPase> array = new ArrayList<TPase>();
					while(rs.next()) {
						TPase pase= new TPase();
						pase.setID_Pase(rs.getInt("idpase"));
						pase.setHora(rs.getInt("hora"));
						pase.setID_Proyeccion(rs.getInt("idproyeccion"));
						array.add(pase);
					}
					rs.close();
					return array;
				}
				catch (SQLException e) {
						e.printStackTrace();
						return null;
				}
			}
		}
		return null;
	}

	public ArrayList<TPase> leerPasesPorHora() {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try
				{
					PreparedStatement st = c.prepareStatement("SELECT * FROM pase where activo = 1 ORDER BY hora ASC FOR UPDATE");
					ResultSet rs = st.executeQuery();
					ArrayList<TPase> array = new ArrayList<TPase>();
					while(rs.next()) {
						TPase pase= new TPase(); 
						pase.setID_Pase(rs.getInt("idpase"));
						pase.setHora(rs.getInt("hora"));
						pase.setID_Proyeccion(rs.getInt("idproyeccion"));
						array.add(pase);
					}
					rs.close();
					return array;

				}
				catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
}