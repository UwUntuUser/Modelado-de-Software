package Presentacion.Comando.Empleado;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerEmpleadoComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		TEmpleado emp = FactoriaSA.getInstance().generateSAEmpleado().mostrarEmpleado(Integer.parseInt((String) requestContext.getDato()));
		
		if(emp != null)
			return new Contexto(CommList.LEER_EMPLEADO_OK, emp);
		else
			return new Contexto(CommList.LEER_EMPLEADO_KO, emp);
	}

	

}
