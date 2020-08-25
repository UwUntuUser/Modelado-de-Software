package Presentacion.Comando.Proyeccion;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proyeccion.SAProyeccion;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerTodosProyeccionComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		ArrayList<TProyeccion> lista = FactoriaSA.getInstance().generateSAProyeccion().mostrarTodas();
		
		if(!lista.isEmpty())
			return new Contexto(CommList.LEER_TODOS_PROYECCION_OK,lista);
		else
			return new Contexto(CommList.LEER_TODOS_PROYECCION_KO,lista);
	}

	

}
