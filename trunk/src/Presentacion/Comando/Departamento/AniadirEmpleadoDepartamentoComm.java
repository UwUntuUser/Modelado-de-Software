package Presentacion.Comando.Departamento;

import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Pair;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class AniadirEmpleadoDepartamentoComm implements Comando{

	@Override
	public Contexto execute(Contexto contexto) {
		TEmpleado empleado = (TEmpleado) contexto.getDato();
		boolean ok = FactoriaSA.getInstance().generateSADepartamento().AniadirEmpleadoDepartamento(empleado);
		
		if(ok)
			return new Contexto(CommList.ANIADIR_EMPLEADO_DEPARTAMENTO_OK,ok);
		else
			return new Contexto(CommList.ANIADIR_EMPLEADO_DEPARTAMENTO_KO,ok);
	}

	

}
