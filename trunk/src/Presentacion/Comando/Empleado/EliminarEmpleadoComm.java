package Presentacion.Comando.Empleado;


import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarEmpleadoComm implements Comando{

	@Override
	public Contexto execute(Contexto requestContext) {
		
		boolean ok = FactoriaSA.getInstance().generateSAEmpleado().bajaEmpleado(Integer.parseInt((String) requestContext.getDato()));
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_EMPLEADO_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_EMPLEADO_KO,ok);
	}

	
}
