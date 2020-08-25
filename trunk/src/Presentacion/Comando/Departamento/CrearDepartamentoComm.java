package Presentacion.Comando.Departamento;

import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.TDepartamento;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CrearDepartamentoComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TDepartamento departamento = (TDepartamento) contexto.getDato();
		
		int ok = FactoriaSA.getInstance().generateSADepartamento().altaDepartamento(departamento);
		
		if(ok != -1)
			return new Contexto(CommList.CREAR_DEPARTAMENTO_OK,ok);
		else
			return new Contexto(CommList.CREAR_DEPARTAMENTO_KO,ok);
	}

	

}
