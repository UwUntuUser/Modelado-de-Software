package Presentacion.Comando.Departamento;


import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarEmpleadoDepartamentoComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TEmpleado emp = (TEmpleado) contexto.getDato();
		
		boolean ok = FactoriaSA.getInstance().generateSADepartamento().EliminarEmpleadoDepartamento(emp.getIdDep(), emp);
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO_KO,ok);
	}

	

}
