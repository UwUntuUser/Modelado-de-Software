/**
 * 
 */
package Presentacion.FactoriaPresentacion;

import Presentacion.Competicion.VistaActualizarCompeticion;
import Presentacion.Competicion.VistaAniadirEmpleadoCompeticion;
import Presentacion.Competicion.VistaCrearCompeticion;
import Presentacion.Competicion.VistaEliminarCompeticion;
import Presentacion.Competicion.VistaEliminarEmpleadoCompeticion;
import Presentacion.Competicion.VistaLeerCompeticion;
import Presentacion.Competicion.VistaLeerTodosCompeticion;
import Presentacion.Departamento.VistaActualizarDepartamento;
import Presentacion.Departamento.VistaAniadirEmpleadoDepartamento;
import Presentacion.Departamento.VistaCalcularNominaDepartamento;
import Presentacion.Departamento.VistaCrearDepartamento;
import Presentacion.Departamento.VistaEliminarDepartamento;
import Presentacion.Departamento.VistaEliminarEmpleadoDepartamento;
import Presentacion.Departamento.VistaLeerDepartamento;
import Presentacion.Departamento.VistaLeerTodosDepartamento;
import Presentacion.Empleado.VistaActualizarEmpleado;
import Presentacion.Empleado.VistaCrearEmpleado;
import Presentacion.Empleado.VistaEliminarEmpleado;
import Presentacion.Empleado.VistaLeerEmpleado;
import Presentacion.Empleado.VistaLeerTodosEmpleado;
import Presentacion.Pase.VistaActualizarPase;
import Presentacion.Pase.VistaCrearPase;
import Presentacion.Pase.VistaEliminarPase;
import Presentacion.Pase.VistaLeerPase;
import Presentacion.Pase.VistaLeerTodosPase;
import Presentacion.Proyeccion.VistaActualizarProyeccion;
import Presentacion.Proyeccion.VistaCrearProyeccion;
import Presentacion.Proyeccion.VistaEliminarProyeccion;
import Presentacion.Proyeccion.VistaLeerProyeccion;
import Presentacion.Proyeccion.VistaLeerTodosProyeccion;
import Presentacion.Venta.VistaAniadirLineaVenta;
import Presentacion.Venta.VistaCerrarVenta;
import Presentacion.Venta.VistaCrearVenta;
import Presentacion.Venta.VistaEliminarVenta;
import Presentacion.Venta.VistaLeerTodosVenta;
import Presentacion.Venta.VistaLeerVenta;

public class FactoriaPresentacionImp extends FactoriaPresentacion 
{
	
	//vistas competicion
	public VistaActualizarCompeticion generarVistaActualizarCompeticion() 
	{
		return new VistaActualizarCompeticion();
	}
	public VistaAniadirEmpleadoCompeticion generarVistaAniadirEmpleadoCompeticion() 
	{
		return new VistaAniadirEmpleadoCompeticion();
	}
	public VistaCrearCompeticion generarVistaCrearCompeticion() 
	{
		return new VistaCrearCompeticion();
	}
	public VistaEliminarCompeticion generarVistaEliminarCompeticion() 
	{
		return new VistaEliminarCompeticion();
	}
	public VistaEliminarEmpleadoCompeticion generarVistaEliminarEmpleadoCompeticion() 
	{
		return new VistaEliminarEmpleadoCompeticion();
	}
	public VistaLeerCompeticion generarVistaleerCompeticion() 
	{
		return new VistaLeerCompeticion();
	}
	public VistaLeerTodosCompeticion generarVistaleerTodosCompeticion() 
	{
		return new VistaLeerTodosCompeticion();
	}
	//vistas departamento
	public VistaActualizarDepartamento generarVistaActualizarDepartamento() 
	{
		return new VistaActualizarDepartamento();
	}

	public VistaCrearDepartamento generarVistaCrearDepartamento() 
	{
		return new VistaCrearDepartamento();
	}
	public VistaEliminarDepartamento generarVistaEliminarDepartamento() 
	{
		return new VistaEliminarDepartamento();
	}
	public VistaLeerDepartamento generarVistaleerDepartamento() 
	{
		return new VistaLeerDepartamento();
	}
	public VistaLeerTodosDepartamento generarVistaleerTodosDepartamento() 
	{
		return new VistaLeerTodosDepartamento();
	}
	public VistaAniadirEmpleadoDepartamento generarVistaAniadirEmpleadoDepartamento() 
	{
		return new VistaAniadirEmpleadoDepartamento();
	}
	public VistaEliminarEmpleadoDepartamento generarVistaEliminarEmpleadoDepartamento() 
	{
		return new VistaEliminarEmpleadoDepartamento();
	}
	public VistaCalcularNominaDepartamento generarVistaCalcularNominaDepartamento() {
		return new VistaCalcularNominaDepartamento();
	}
	//vistas empleado
	public VistaActualizarEmpleado generarVistaActualizarEmpleado() 
	{
		return new VistaActualizarEmpleado();
	}
	public VistaCrearEmpleado generarVistaCrearEmpleado() 
	{
		return new VistaCrearEmpleado();
	}
	public VistaEliminarEmpleado generarVistaEliminarEmpleado() 
	{
		return new VistaEliminarEmpleado();
	}
	public VistaLeerEmpleado generarVistaleerEmpleado() 
	{
		return new VistaLeerEmpleado();
	}
	public VistaLeerTodosEmpleado generarVistaleerTodosEmpleado() 
	{
		return new VistaLeerTodosEmpleado();
	}
	//vistas pase
	public VistaActualizarPase generarVistaActualizarPase() 
	{
		return new VistaActualizarPase();
	}
	public VistaCrearPase generarVistaCrearPase() 
	{
		return new VistaCrearPase();
	}
	public VistaEliminarPase generarVistaEliminarPase() 
	{
		return new VistaEliminarPase();
	}
	public VistaLeerPase generarVistaLeerPase() 
	{
		return new VistaLeerPase();
	}
	public VistaLeerTodosPase generarVistaLeerTodosPase() 
	{
		return new VistaLeerTodosPase();
	}
	//vistas proyeccion
	public VistaActualizarProyeccion generarVistaActualizarProyeccion() 
	{
		return new VistaActualizarProyeccion();
	}
	public VistaCrearProyeccion generarVistaCrearProyeccion() 
	{
		return new VistaCrearProyeccion();
	}
	public VistaEliminarProyeccion generarVistaeliEliminarProyeccion() 
	{
		return new VistaEliminarProyeccion();
	}
	public VistaLeerProyeccion generarVistaleerProyeccion() 
	{
		return new VistaLeerProyeccion();
	}
	public VistaLeerTodosProyeccion generarVistaleerTodosProyeccion() 
	{
		return new VistaLeerTodosProyeccion();
	}
	//vistas venta
	public VistaCrearVenta generarVistaCrearVenta() 
	{
		return new VistaCrearVenta();
	}
	public VistaCerrarVenta generarVistaCerrarVenta() 
	{
		return new VistaCerrarVenta();
	}
	public VistaEliminarVenta generarVistaeliEliminarVenta() 
	{
		return new VistaEliminarVenta();
	}
	public VistaLeerVenta generarVistaleerVenta() 
	{
		return new VistaLeerVenta();
	}
	public VistaLeerTodosVenta generarVistaLeerTodosVenta() 
	{
		return new VistaLeerTodosVenta();
	}
	public VistaAniadirLineaVenta generarVistaAniadirLineaVenta() 
	{
		return new VistaAniadirLineaVenta();
	}
	public VistaLeerTodosVenta generarVistaleerTodosVenta() 
	{
		return new VistaLeerTodosVenta();
	}
}
