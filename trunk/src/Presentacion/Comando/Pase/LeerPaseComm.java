package Presentacion.Comando.Pase;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.SAPase;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerPaseComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TPase pase = FactoriaSA.getInstance().generateSAPase().leerPase((int)contexto.getDato());
		
		if(pase != null)
			return new Contexto(CommList.LEER_PASE_OK,pase);
		else
			return new Contexto(CommList.LEER_PASE_KO,pase);
		
	}

	
	
}
