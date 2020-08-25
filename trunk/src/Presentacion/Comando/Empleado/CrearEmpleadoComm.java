package Presentacion.Comando.Empleado;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CrearEmpleadoComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		int id = FactoriaSA.getInstance().generateSAEmpleado().altaEmpleado((TEmpleado)requestContext.getDato());
		
		if(id != -1)
			return new Contexto(CommList.CREAR_EMPLEADO_OK,id);
		else
			return new Contexto(CommList.CREAR_EMPLEADO_KO,id);
	}

	
}
