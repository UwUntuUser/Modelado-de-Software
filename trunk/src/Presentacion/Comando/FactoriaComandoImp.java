package Presentacion.Comando;

import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.FactoriaComando;
import Presentacion.Comando.Competicion.*;
import Presentacion.Comando.Departamento.*;
import Presentacion.Comando.Empleado.*;
import Presentacion.Comando.Pase.*;
import Presentacion.Comando.Proyeccion.*;
import Presentacion.Comando.Venta.*;


public class FactoriaComandoImp extends FactoriaComando {

	@Override
	public Comando getComando(Integer comm) {
		switch (comm) {
		
		case CommList.ACTUALIZAR_PASE:return new ActualizarPaseComm();
		case CommList.CREAR_PASE:return new CrearPaseComm();
		case CommList.ELIMINAR_PASE:return new EliminarPaseComm();
		case CommList.LEER_PASE:return new LeerPaseComm();
		case CommList.LEER_TODOS_PASE:return new LeerTodosPaseComm();
		case CommList.LEER_TODOS_PASE_HORA:return new LeerTodosPaseHoraComm();
		case CommList.QUERY_LEER_PELIS_POR_DURACION:return new QueryLeerPorHoraComm();
		case CommList.QUERY_LEER_PELIS_POR_GENERO:return new QueryLeerPorGeneroComm();
			
		case CommList.ACTUALIZAR_PROYECCION:return new ActualizarProyeccionComm();
		case CommList.CREAR_PROYECCION:return new CrearProyeccionComm();
		case CommList.ELIMINAR_PROYECCION:return new EliminarProyeccionComm();
		case CommList.LEER_TODOS_PROYECCION:return new LeerTodosProyeccionComm();
		case CommList.LEER_PROYECCION:return new LeerProyeccionComm();

			
		case CommList.ANIADIR_LINEA_VENTA:return new AniadirLineaVentaComm();
		case CommList.CREAR_VENTA:return new CrearVentaComm();
		case CommList.CERRAR_VENTA:return new CerrarVentaComm();
		case CommList.ELIMINAR_VENTA:return new EliminarVentaComm();
		case CommList.LEER_TODOS_VENTA:return new LeerTodosVentaComm();
		case CommList.LEER_VENTA:return new LeerVentaComm();
			
		case CommList.ACTUALIZAR_EMPLEADO:return new ActualizarEmpleadoComm();
		case CommList.CREAR_EMPLEADO:return new CrearEmpleadoComm();
		case CommList.ELIMINAR_EMPLEADO:return new EliminarEmpleadoComm();
		case CommList.LEER_EMPLEADO:return new LeerEmpleadoComm();
		case CommList.LEER_TODOS_EMPLEADO:return new LeerTodosEmpleadoComm();
			
		case CommList.ACTUALIZAR_DEPARTAMENTO:return new ActualizarDepartamentoComm();
		case CommList.ANIADIR_EMPLEADO_DEPARTAMENTO:return new AniadirEmpleadoDepartamentoComm();
		case CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO:return new EliminarEmpleadoDepartamentoComm();
		case CommList.CREAR_DEPARTAMENTO:return new CrearDepartamentoComm();
		case CommList.ELIMINAR_DEPARTAMENTO:return new EliminarDepartamentoComm();
		case CommList.LEER_DEPARTAMENTO:return new LeerDepartamentoComm();
		case CommList.LEER_TODOS_DEPARTAMENTO:return new LeerTodosDepartamentoComm();
		case CommList.CALCULAR_NOMINA:return new CalcularNominaComm();
			
		case CommList.ACTUALIZAR_COMPETICION:return new ActualizarCompeticionComm();
		case CommList.ANIADIR_EMPLEADO_COMPETICION:return new AniadirEmpleadoCompeticionComm();
		case CommList.ELIMINAR_EMPLEADO_COMPETICION:return new EliminarEmpleadoCompeticionComm();
		case CommList.CREAR_COMPETICION:return new CrearCompeticionComm();
		case CommList.ELIMINAR_COMPETICION:return new EliminarCompeticionComm();
		case CommList.LEER_COMPETICION:return new LeerCompeticionComm();
		case CommList.LEER_TODOS_COMPETICION:return new LeerTodosCompeticionComm();
			
		}
		return null;	
	}
}


