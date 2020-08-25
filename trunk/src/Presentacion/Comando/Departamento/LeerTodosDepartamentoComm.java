package Presentacion.Comando.Departamento;

import java.util.ArrayList;

import Negocio.Departamento.SADepartamento;
import Negocio.Departamento.TDepartamento;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerTodosDepartamentoComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		ArrayList<TDepartamento> dep = FactoriaSA.getInstance().generateSADepartamento().mostrarDepartamentos();
		
		if(!dep.isEmpty())
			return new Contexto(CommList.LEER_TODOS_DEPARTAMENTO_OK,dep);
		else
			return new Contexto(CommList.LEER_TODOS_DEPARTAMENTO_KO,dep);
	}

	
}
