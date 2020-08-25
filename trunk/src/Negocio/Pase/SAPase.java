package Negocio.Pase;

import java.util.ArrayList;

public interface SAPase {
	public int crearPase(TPase tPase);
	public boolean eliminarPase(int id);
	public boolean actualizarPase(TPase tPase);
	public TPase leerPase(int ID);
	public ArrayList leerTodos();
	public ArrayList leerPasesPorHora();
	public ArrayList pelisPorGenero(String genero);
	public ArrayList pelisPorDuracion(String hora);
}