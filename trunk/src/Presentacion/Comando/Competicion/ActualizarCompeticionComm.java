package Presentacion.Comando.Competicion;

import Negocio.Competicion.TCompeticion;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class ActualizarCompeticionComm implements Comando{

	@Override
	public Contexto execute(Contexto requestContext) {
		
		TCompeticion comp = (TCompeticion)requestContext.getDato();
		boolean ok = FactoriaSA.getInstance().generateSACompeticion().actualizarCompeticion(comp);
		
		if(ok)
			return new Contexto(CommList.ACTUALIZAR_COMPETICION_OK,ok);
		else
			return new Contexto(CommList.ACTUALIZAR_COMPETICION_KO,ok);
	}

	
}
