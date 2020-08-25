package Negocio.Departamento;

import java.util.ArrayList;

import Negocio.Empleado.TEmpleado;

public interface SADepartamento {
	
	public int altaDepartamento(TDepartamento td);
	public boolean bajaDepartamento(int id);
	public TDepartamento mostrarDepartamento(int id);
	public ArrayList<TDepartamento> mostrarDepartamentos();
	public boolean actualizarDepartamento(TDepartamento departamento);
	public boolean AniadirEmpleadoDepartamento(TEmpleado empleado); 
	public boolean EliminarEmpleadoDepartamento(int dep, TEmpleado emp); 
	public int CalcularNominaDepartamento(int depid);
}
