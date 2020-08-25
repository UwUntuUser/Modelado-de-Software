package Presentacion.Comando.Pase;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.SAPase;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class ActualizarPaseComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TPase pase = (TPase) contexto.getDato();
		boolean ok = FactoriaSA.getInstance().generateSAPase().actualizarPase(pase);
		
		if(ok)
			return new Contexto(CommList.ACTUALIZAR_PASE_OK,ok);
		else
			return new Contexto(CommList.ACTUALIZAR_PASE_KO,ok);

	}

	

}
