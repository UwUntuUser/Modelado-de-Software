package Presentacion.Comando.Competicion;

import java.util.ArrayList;

import Negocio.Competicion.SACompeticion;
import Negocio.Competicion.TCompeticion;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoCompeticionId;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarEmpleadoCompeticionComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		TEmpleadoCompeticionId t = (TEmpleadoCompeticionId) requestContext.getDato();
		boolean ok = FactoriaSA.getInstance().generateSACompeticion().EliminarEmpleadoCompeticion(t.getIdCompeticion(), t.getIdEmpleado());
		if(ok)
			return new Contexto(CommList.ELIMINAR_EMPLEADO_COMPETICION_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_EMPLEADO_COMPETICION_KO,ok);
	}

	

}
