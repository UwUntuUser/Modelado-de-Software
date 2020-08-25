package Negocio.Competicion;

import java.util.ArrayList;

import Negocio.Empleado.TEmpleado;

public interface SACompeticion {
	public Integer CrearCompeticion(TCompeticion competicion);
	
	public boolean eliminarCompeticion(Integer id);
	
	public boolean actualizarCompeticion(TCompeticion competicion);
	
	public TCompeticion leerCompeticion(Integer id);
	
	public ArrayList <TCompeticion> leerTodosCompeticion();

	public boolean AniairEmpleadoCompeticion(int comp, int emp);
	
	public boolean EliminarEmpleadoCompeticion(int comp, int emp);
}
