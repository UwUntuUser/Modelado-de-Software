package Integracion.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Integracion.Transactionmanager.Transaccion;
import Integracion.Transactionmanager.TransactionManagerSingleton;
import Negocio.Proyeccion.TPelicula;

public class PelisPorDuracion implements Query{

	private String query="SELECT * FROM proyeccion JOIN pelicula ON "
						+"proyeccion.idproyeccion=pelicula.idproyeccion  WHERE duracion = ? AND activo = 1"
						+" AND activo=1";
	private String bloqueo = " FOR UPDATE";
	
	private Connection con;
	
	@Override
	public Object execute(String duracion) {
		ArrayList<TPelicula> ps = new ArrayList<TPelicula>();
		TransactionManagerSingleton TM = TransactionManagerSingleton.getInstancia();
		Transaccion t = TM.getTransaccion();
		if(t != null){
			con = (Connection)t.getResource();
			if(con != null)
			{
				try {
					PreparedStatement p = con.prepareStatement(query + bloqueo);
					p.setString(1, duracion);
					ResultSet r = p.executeQuery();
					while(r.next())
						ps.add(new TPelicula(r.getInt("idproyeccion"), r.getString("sinopsis"), r.getString("nombre"),r.getString("genero"), r.getInt("duracion"),true));
					return ps;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
	}
		return null;
	}

}
