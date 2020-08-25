package Presentacion.Comando.Departamento;

import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.TDepartamento;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarDepartamentoComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {

		int  id= (int) contexto.getDato();
		
		boolean ok = FactoriaSA.getInstance().generateSADepartamento().bajaDepartamento(id);
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_DEPARTAMENTO_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_DEPARTAMENTO_KO,ok);
	}

	

}
