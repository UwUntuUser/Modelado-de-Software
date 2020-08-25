package Presentacion.Comando.Competicion;

import java.util.ArrayList;

import Negocio.Competicion.TCompeticion;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerTodosCompeticionComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		ArrayList<TCompeticion> comps = FactoriaSA.getInstance().generateSACompeticion().leerTodosCompeticion();
		
		if(!comps.isEmpty())
			return new Contexto(CommList.LEER_TODOS_COMPETICION_OK,comps);
		else
			return new Contexto(CommList.LEER_TODOS_COMPETICION_KO,comps);

	}

	
}
