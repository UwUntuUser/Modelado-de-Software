package Presentacion.Comando.Empleado;

import java.util.ArrayList;

import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.SAPase;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class ActualizarEmpleadoComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		boolean ok = FactoriaSA.getInstance().generateSAEmpleado().actualizarEmpleado((TEmpleado)requestContext.getDato());
		
		if(ok)
			return new Contexto(CommList.ACTUALIZAR_EMPLEADO_OK,ok);
		else
			return new Contexto(CommList.ACTUALIZAR_EMPLEADO_KO,ok);
	}

	

}
