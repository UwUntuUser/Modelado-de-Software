/**
 * 
 */
package Integracion.Proyeccion;

import Negocio.Proyeccion.TProyeccion;
import java.util.ArrayList;


public interface DAOProyeccion {
	
	public TProyeccion read(int ID_proy);
	public boolean delete(int ID_proy);
	public int createProyeccion(TProyeccion Tproy);
	public ArrayList<TProyeccion> readAll();
	public ArrayList<TProyeccion> readAllTrue();
	public boolean updateProyeccion(int id, TProyeccion TProy);
}