package Presentacion.Comando.Pase;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.SAPase;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarPaseComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		boolean ok = FactoriaSA.getInstance().generateSAPase().eliminarPase((int)contexto.getDato());
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_PASE_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_PASE_KO,ok);
	}
	
	

}
