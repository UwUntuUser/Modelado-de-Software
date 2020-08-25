package Presentacion.Comando.Departamento;

import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.TDepartamento;
import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class ActualizarDepartamentoComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TDepartamento departamento = (TDepartamento) contexto.getDato();
		
		boolean ok = FactoriaSA.getInstance().generateSADepartamento().actualizarDepartamento(departamento);
		
		if(ok)
			return new Contexto(CommList.ACTUALIZAR_DEPARTAMENTO_OK,ok);
		else
			return new Contexto(CommList.ACTUALIZAR_DEPARTAMENTO_KO,ok);
	}

	

}
