package Presentacion.Comando.Proyeccion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proyeccion.SAProyeccion;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarProyeccionComm implements Comando{

	@Override
	public Contexto execute(Contexto contexto) {
		
	
		boolean ok = FactoriaSA.getInstance().generateSAProyeccion().eliminarProyeccion((int)contexto.getDato());
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_PROYECCION_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_PROYECCION_KO,ok);
	}

	

}
