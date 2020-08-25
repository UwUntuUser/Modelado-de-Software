	/**
 * 
 */
package Integracion.Proyeccion;

import Negocio.Proyeccion.TDocumental;
import Negocio.Proyeccion.TPelicula;
import Negocio.Proyeccion.TProyeccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Integracion.Transactionmanager.Transaccion;
import Integracion.Transactionmanager.TransactionManagerSingleton;

public class DAOProyeccionImp implements DAOProyeccion 
{
	//TODO posibilidad de pasarle ademas un boolean y asi saber si es pelicula o documental para hacer un join en la base de datos corresponientemente,sustituyendo en la query con un if
		public TProyeccion read(int ID_proy) {
			TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
			Transaccion t = TM.getTransaccion();
			if (t != null) {
				Connection c = (Connection) t.getResource();
				if (c != null) {
					try {
						PreparedStatement st = c.prepareStatement("SELECT * FROM proyeccion WHERE idproyeccion = ? AND activo = 1 FOR UPDATE");
						st.setInt(1, ID_proy);
						ResultSet rs = st.executeQuery();
						if (rs.next()) {
							if (rs.getInt("es_documental") == 1) {
								PreparedStatement st2 = c.prepareStatement("SELECT * FROM documental WHERE idproyeccion = ? FOR UPDATE");
								st2.setInt(1, ID_proy);
								ResultSet rs2 = st2.executeQuery();
								if (rs2.next()) {
									boolean activo;
									if (rs.getInt("activo") == 0) activo = false;
									else activo = true;
									TDocumental docu = new TDocumental(ID_proy, rs2.getString("narrador"), rs.getString("nombre"), rs.getString("genero"), rs.getInt("duracion"), activo);
									return docu;
								}
								return null;
							}
							else {
								PreparedStatement st3 = c.prepareStatement("SELECT * FROM pelicula WHERE idproyeccion = ? FOR UPDATE");
								st3.setInt(1, ID_proy);
								ResultSet rs3 = st3.executeQuery();
								if (rs3.next()) {
									boolean activo;
									if (rs.getInt("activo") == 0) activo = false;
									else activo = true;
									TPelicula peli = new TPelicula(ID_proy, rs3.getString("sinopsis"), rs.getString("nombre"), rs.getString("genero"), rs.getInt("duracion"), activo);
									return peli;
								}
								return null;
							}
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

		public boolean delete(int ID_proy) {
			boolean delete = false;
			TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
			Transaccion t = TM.getTransaccion();
			if (t != null) {
				Connection c = (Connection) t.getResource();
				if (c != null) {
					try {
						PreparedStatement st = c
								.prepareStatement("UPDATE proyeccion SET activo = ? WHERE idproyeccion = ? ");
						st.setInt(1, 0);
						st.setInt(2, ID_proy);
						st.executeUpdate();
						delete = true;
					}
					catch (SQLException e) {
						delete = false;
						e.printStackTrace();
					}
				}
			}
			return delete;
		}

		public ArrayList<TProyeccion> readAll() {
			ArrayList<TProyeccion> lista_proyeccion = new ArrayList<TProyeccion>();
			TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
			Transaccion t = TM.getTransaccion();
			if (t != null) {
				Connection c = (Connection) t.getResource();
				if (c != null) {
					try {
						PreparedStatement ps = c.prepareStatement("SELECT * FROM proyeccion FOR UPDATE");
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							if (rs.getInt("activo") == 1 && rs.getInt("es_documental") == 0) {
								PreparedStatement psp = c.prepareStatement("SELECT * FROM pelicula WHERE idproyeccion = ? FOR UPDATE");
								psp.setInt(1, rs.getInt("idproyeccion"));
								ResultSet rsp = psp.executeQuery();
								if(rsp.next()) {
									lista_proyeccion.add(new TPelicula(rs.getInt("idproyeccion"), rsp.getString("sinopsis"), rs.getString("nombre"), rs.getString("genero"), rs.getInt("duracion"), true));
								}
							}
							else if (rs.getInt("activo") == 1 && rs.getInt("es_documental") == 1) {
								PreparedStatement psd = c.prepareStatement("SELECT * FROM documental WHERE idproyeccion = ? FOR UPDATE");
								psd.setInt(1, rs.getInt("idproyeccion"));
								ResultSet rsd = psd.executeQuery();
								if(rsd.next()) {
									lista_proyeccion.add(new TDocumental(rs.getInt("idproyeccion"), rsd.getString("narrador"), rs.getString("nombre"), rs.getString("genero"), rs.getInt("duracion"), true));
								}
							}
						}
					} 
					catch (Exception e) {
						e.getMessage();
						return null;
					}
				}
			}
			return lista_proyeccion;
		}

		@Override
		public int createProyeccion(TProyeccion Tproy) {
			int id = -1;
			TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
			Transaccion t = TM.getTransaccion();
			if (t != null) {
				Connection c = (Connection) t.getResource();
				if (c != null) {
					try {
						PreparedStatement st = c.prepareStatement("INSERT INTO proyeccion (genero, nombre, duracion, es_documental, activo) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
						st.setString(1, Tproy.getGenero());
						st.setString(2, Tproy.getNombre());
						st.setInt(3, Tproy.getDuracion());
						if (Tproy.isEs_Documental()) {
							st.setInt(4, 1);
						}
						else st.setInt(4, 0);
						st.setInt(5, 1);
						st.executeUpdate();
						ResultSet eSet = st.getGeneratedKeys();
						if(eSet.next()) {
							id = eSet.getInt(1);
							if (!Tproy.isEs_Documental()) {
								TPelicula peli = (TPelicula) Tproy;
								PreparedStatement psp = c.prepareStatement("INSERT INTO pelicula (idproyeccion, sinopsis) VALUES (?, ?)");
								psp.setInt(1, id);
								psp.setString(2, peli.getSinopsis());
								psp.executeUpdate();
							}
							if (Tproy.isEs_Documental()) {
								TDocumental docu = (TDocumental) Tproy;
								PreparedStatement psd = c.prepareStatement("INSERT INTO documental (idproyeccion, narrador) VALUES (?, ?)");
								psd.setInt(1, id);
								psd.setString(2, docu.getNarrador());
								psd.executeUpdate();
							}
						}
					} 
					catch (Exception e) {
						System.out.println(e.getMessage());
						return -1;
					}
				}
			}
			return id;
		}

		@Override
		public boolean updateProyeccion(int id, TProyeccion TProy) {
			boolean update = false;
			TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
			Transaccion t = TM.getTransaccion();
			if (t != null) {
				Connection c = (Connection) t.getResource();
				if (c != null) {
					try {
						PreparedStatement st = c.prepareStatement("UPDATE proyeccion SET genero = ?, nombre = ?, duracion = ?, es_documental = ?, activo = ? WHERE idproyeccion = ?");
						st.setString(1, TProy.getGenero());
						st.setString(2, TProy.getNombre());
						st.setInt(3, TProy.getDuracion());
						if (TProy.isEs_Documental()) {
							st.setInt(4, 1);
						}
						else st.setInt(4, 0);
						if (TProy.getActivo()) {
							st.setInt(5, 1);
						}
						else st.setInt(5, 0);
						st.setInt(6, id);
						st.executeUpdate();
						if (TProy.isEs_Documental()) {
							TDocumental docu = (TDocumental) TProy;
							PreparedStatement std = c.prepareStatement("UPDATE documental SET idproyeccion = ?, narrador = ? WHERE idproyeccion = ?");
							std.setInt(1, id);
							std.setString(2, docu.getNarrador());
							std.setInt(3, id);
							std.executeUpdate();
							update = true;
						}
						else if (!TProy.isEs_Documental()) {
							TPelicula peli = (TPelicula) TProy;
							PreparedStatement std = c.prepareStatement("UPDATE pelicula SET idproyeccion = ?, sinopsis = ? WHERE idproyeccion = ?");
							std.setInt(1, id);
							std.setString(2, peli.getSinopsis());
							std.setInt(3, id);
							std.executeUpdate();
							update = true;
						}
					} 
					catch (Exception e) {
						update = false;
						e.getMessage();
					}
				}
			}
			return update;
		}

		@Override
		public ArrayList<TProyeccion> readAllTrue() {
			ArrayList<TProyeccion> lista_proyeccion = new ArrayList<TProyeccion>();
			TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
			Transaccion t = TM.getTransaccion();
			if (t != null) {
				Connection c = (Connection) t.getResource();
				if (c != null) {
					try {
						PreparedStatement ps = c.prepareStatement("SELECT * FROM proyeccion FOR UPDATE");
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							boolean act = true;
							if(rs.getInt("activo") == 1){
								 act = true;
							}else{
								 act = false;
							}
							if (rs.getInt("es_documental") == 0) {
								PreparedStatement psp = c.prepareStatement("SELECT * FROM pelicula WHERE idproyeccion = ? FOR UPDATE");
								psp.setInt(1, rs.getInt("idproyeccion"));
								ResultSet rsp = psp.executeQuery();
								if(rsp.next()) {
									lista_proyeccion.add(new TPelicula(rs.getInt("idproyeccion"), rsp.getString("sinopsis"), rs.getString("nombre"), rs.getString("genero"), rs.getInt("duracion"), act));
								}
							}
							else if ( rs.getInt("es_documental") == 1) {
								PreparedStatement psd = c.prepareStatement("SELECT * FROM documental WHERE idproyeccion = ? FOR UPDATE");
								psd.setInt(1, rs.getInt("idproyeccion"));
								ResultSet rsd = psd.executeQuery();
								if(rsd.next()) {
									lista_proyeccion.add(new TDocumental(rs.getInt("idproyeccion"), rsd.getString("narrador"), rs.getString("nombre"), rs.getString("genero"), rs.getInt("duracion"), act));
								}
							}
						}
					} 
					catch (Exception e) {
						e.getMessage();
						return null;
					}
				}
			}
			return lista_proyeccion;
		}
}