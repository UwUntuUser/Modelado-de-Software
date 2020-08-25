package Presentacion.Comando.Departamento;

import Negocio.Departamento.TDepartamento;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerDepartamentoComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		
		TDepartamento dep = FactoriaSA.getInstance().generateSADepartamento().mostrarDepartamento((int)contexto.getDato());
		
		if(dep != null)
			return new Contexto(CommList.LEER_DEPARTAMENTO_OK,dep);
		else
			return new Contexto(CommList.LEER_DEPARTAMENTO_KO,dep);
	}

	

}
