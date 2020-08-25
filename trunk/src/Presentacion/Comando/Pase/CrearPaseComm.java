package Presentacion.Comando.Pase;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.SAPase;
import Negocio.Pase.TPase;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CrearPaseComm implements Comando {

	

	@Override
	public Contexto execute(Contexto contexto) {
		
		TPase pase = (TPase) contexto.getDato();
		int ok = FactoriaSA.getInstance().generateSAPase().crearPase(pase);
		
		if(ok != -1)
			return new Contexto(CommList.CREAR_PASE_OK,ok);
		else
			return new Contexto(CommList.CREAR_PASE_KO,ok);
		}

}
