package Presentacion.Comando.Proyeccion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proyeccion.SAProyeccion;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerProyeccionComm implements Comando 
{

	@Override
	public Contexto execute(Contexto contexto) {


		TProyeccion p = FactoriaSA.getInstance().generateSAProyeccion().mostrar((int)contexto.getDato());
		
		if(p != null)
			return new Contexto(CommList.LEER_PROYECCION_OK,p);
		else
			return new Contexto(CommList.LEER_PROYECCION_KO,p);
	}

	
	
	

}
