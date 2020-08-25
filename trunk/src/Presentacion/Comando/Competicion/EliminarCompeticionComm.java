package Presentacion.Comando.Competicion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarCompeticionComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		boolean ok = FactoriaSA.getInstance().generateSACompeticion().eliminarCompeticion((int)requestContext.getDato());
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_COMPETICION_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_COMPETICION_KO,ok);
	}

	

}
