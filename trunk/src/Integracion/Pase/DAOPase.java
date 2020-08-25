/**
 * 
 */
package Integracion.Pase;

import Negocio.Pase.TPase;
import java.util.ArrayList;


public interface DAOPase {
	
	public int crearPase(TPase tPase);
	public int eliminarPase(int id);
	public int actualizarPase(TPase tPase, int id);
	public TPase leerPase(int ID_Pase);
	public ArrayList<TPase> leerTodos();
	public ArrayList<TPase> leerPasesPorHora();
}