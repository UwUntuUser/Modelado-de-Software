package Negocio.Empleado;

import java.util.ArrayList;

public interface SAEmpleado {
	public int altaEmpleado(TEmpleado te);
	public boolean bajaEmpleado(Integer id);
	public boolean actualizarEmpleado(TEmpleado empleado);
	public TEmpleado mostrarEmpleado(Integer id);
	public ArrayList<TEmpleado> mostrarEmpleados();
	
}
