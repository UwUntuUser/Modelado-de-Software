package Integracion.Venta;

import Negocio.Venta.TLinea_Venta;
import Negocio.Venta.TVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Integracion.Transactionmanager.Transaccion;
import Integracion.Transactionmanager.TransactionManagerSingleton;

public class DAOVentaImp implements DAOVenta {

	public TVenta read(int ID) {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		TVenta venta = null;
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					ArrayList<TLinea_Venta> lineasVenta = new ArrayList<TLinea_Venta>();
					PreparedStatement st = c.prepareStatement("SELECT * FROM venta WHERE idventa = ? AND active = 1 FOR UPDATE");
					st.setInt(1, ID);
					ResultSet rs = st.executeQuery();
					if (rs.next()) {
						venta = new TVenta(ID);
						PreparedStatement st2 = c.prepareStatement("SELECT * FROM linea_venta WHERE id_venta = ? FOR UPDATE");
						st2.setInt(1, ID);
						ResultSet rs2 = st2.executeQuery();
						while (rs2.next()) {
							lineasVenta.add(new TLinea_Venta(rs2.getInt("id_venta"), rs2.getInt("id_pase"), rs2.getInt("cantidad")));
						}
						venta.setLinea_Venta(lineasVenta);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return venta;
	}

	public int create() {
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		int id = -1;
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					PreparedStatement statement = c.prepareStatement("INSERT INTO venta (active) VALUES (1)", java.sql.Statement.RETURN_GENERATED_KEYS);
					statement.executeUpdate();
					ResultSet rs = statement.getGeneratedKeys();
					if (rs.next()) {
						id = rs.getInt(1);
					}
		
				} catch (SQLException e) {
					e.printStackTrace();
					return -1;
				}
			}
		}
		return id;
	}

	public ArrayList<TVenta> readAll() {
		ArrayList<TVenta> array = new ArrayList<TVenta>();
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		int id = -1;
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					PreparedStatement st = c.prepareStatement("SELECT * FROM venta WHERE active=1 FOR UPDATE");
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						array.add(read(rs.getInt("idventa")));
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return array;
	}

	public boolean delete(int ID) {
		
		boolean delete = false;
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		int id = -1;
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try{
					PreparedStatement st = c.prepareStatement("UPDATE venta set active = 0 where idventa = ?");
					st.setInt(1, ID);
					st.executeUpdate();
					delete = true;
				} catch (SQLException e) {
					delete = false;
					e.printStackTrace();
				}
			}
		}
		return delete;
	} 

	public boolean close(int ID) {
		boolean delete = false;
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		int id = -1;
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try{
					PreparedStatement st = c.prepareStatement("UPDATE venta set active = 0 where idventa = ?");
					st.setInt(1, ID);
					st.executeUpdate();
					delete = true;
				}catch (SQLException e) {
					delete = false;
					e.printStackTrace();
				}
			}
		}
		return delete;

	}

	public boolean addLineaVenta(TLinea_Venta tLineaVenta) {
		boolean ok = false;
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if (t != null) {
			Connection c = (Connection) t.getResource();
			if (c != null) {
				try {
					PreparedStatement st = c.prepareStatement(
							"INSERT INTO linea_venta (id_pase,id_venta,cantidad,precio,activo) VALUES (?,?,?,?,?)");
					st.setInt(1, tLineaVenta.getID_Pase());
					st.setInt(2, tLineaVenta.getID_Venta());
					st.setInt(3, tLineaVenta.getCantidad());
					st.setDouble(4, tLineaVenta.getCantidad() * 20);
					st.setInt(5, 1);
					st.executeUpdate();
					ok = true;
				} catch (SQLException e) {
					e.printStackTrace();
					ok = false;
				}
			}
		}
		return ok;
	}
}