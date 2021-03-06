package Presentacion.Comando.Pase;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.SAPase;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerTodosPaseComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {

		ArrayList<TPase> pase = FactoriaSA.getInstance().generateSAPase().leerTodos();
		
		if(!pase.isEmpty())
			return new Contexto(CommList.LEER_TODOS_PASE_OK,pase);
		else
			return new Contexto(CommList.LEER_TODOS_PASE_KO,pase);
	}
	
	
	

}
