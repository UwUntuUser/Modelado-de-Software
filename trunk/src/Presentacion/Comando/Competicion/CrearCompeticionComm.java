package Presentacion.Comando.Competicion;

import Negocio.Competicion.TCompeticion;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CrearCompeticionComm implements Comando{

	@Override
	public Contexto execute(Contexto requestContext) {
		
		TCompeticion comp = (TCompeticion)requestContext.getDato();
		int ok = FactoriaSA.getInstance().generateSACompeticion().CrearCompeticion(comp);
		
		if(ok != -1)
			return new Contexto(CommList.CREAR_COMPETICION_OK,ok);
		else
			return new Contexto(CommList.CREAR_COMPETICION_KO,ok);
	}

	
	
}
