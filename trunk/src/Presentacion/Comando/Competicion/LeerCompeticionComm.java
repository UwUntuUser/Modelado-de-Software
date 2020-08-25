package Presentacion.Comando.Competicion;


import Negocio.Competicion.TCompeticion;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerCompeticionComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
	TCompeticion comps = FactoriaSA.getInstance().generateSACompeticion().leerCompeticion((int)requestContext.getDato());
		
		if(comps != null)
			return new Contexto(CommList.LEER_COMPETICION_OK,comps);
		else
			return new Contexto(CommList.LEER_COMPETICION_KO,comps);
	}

	

}
