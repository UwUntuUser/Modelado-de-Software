package Presentacion.Comando.Departamento;

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CalcularNominaComm implements Comando{

	@Override
	public Contexto execute(Contexto contexto) {
		
		int id = (int)contexto.getDato();
		int ok = FactoriaSA.getInstance().generateSADepartamento().CalcularNominaDepartamento(id);
		
		if(ok != -1)
			return new Contexto(CommList.CALCULAR_NOMINA_OK,ok);
		else
			return new Contexto(CommList.CALCULAR_NOMINA_KO,ok);
	}

}
