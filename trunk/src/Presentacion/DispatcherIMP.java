package Presentacion;

import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Competicion.VistaCompeticion;
import Presentacion.Departamento.VistaDepartamento;
import Presentacion.Empleado.VistaEmpleado;
import Presentacion.Pase.VistaPase;
import Presentacion.Proyeccion.VistaProyeccion;
import Presentacion.Venta.VistaVentas;

public class DispatcherIMP extends Dispatcher {

	@Override
	public void actualizaVistas(Contexto context) {
		if (context.getEvento() <= CommList.QUERY_LEER_PELIS_POR_GENERO_KO && context.getEvento() >= CommList.ACTUALIZAR_PASE_OK) {
			VistaPase.getInstance().update(context);
		}
		else if (context.getEvento() <= CommList.LEER_TODOS_PROYECCION_KO && context.getEvento() >= CommList.ACTUALIZAR_PROYECCION_OK) {
			VistaProyeccion.getInstance().update(context);
		}
		else if (context.getEvento() <= CommList.LEER_VENTA_KO && context.getEvento() >= CommList.ANIADIR_LINEA_VENTA_OK) {
			VistaVentas.getInstance().update(context);
		}
		else if (context.getEvento() <= CommList.LEER_TODOS_EMPLEADO_KO && context.getEvento() >= CommList.ACTUALIZAR_EMPLEADO_OK) {
			VistaEmpleado.getInstance().update(context);
		}
		else if (context.getEvento() <= CommList.CALCULAR_NOMINA_KO && context.getEvento() >= CommList.ACTUALIZAR_DEPARTAMENTO_OK) {
			VistaDepartamento.getInstance().update(context);
		}
		else if (context.getEvento() <= CommList.LEER_TODOS_COMPETICION_KO && context.getEvento() >= CommList.ACTUALIZAR_COMPETICION_OK) {
			VistaCompeticion.getInstance().update(context);
		}
		
	}

}
