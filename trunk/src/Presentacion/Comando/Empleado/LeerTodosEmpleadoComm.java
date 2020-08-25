package Presentacion.Comando.Empleado;

import java.util.ArrayList;

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.SAEmpleado;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerTodosEmpleadoComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		ArrayList<TEmpleado> lista = FactoriaSA.getInstance().generateSAEmpleado().mostrarEmpleados();
		
		if(!lista.isEmpty())
			return new Contexto(CommList.LEER_TODOS_EMPLEADO_OK,lista);
		else
			return new Contexto(CommList.LEER_TODOS_EMPLEADO_KO,lista);
	}		
}
