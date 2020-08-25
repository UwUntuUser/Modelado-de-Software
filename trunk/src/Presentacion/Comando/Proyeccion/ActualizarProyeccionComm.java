package Presentacion.Comando.Proyeccion;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Pase.TPase;
import Negocio.Proyeccion.SAProyeccion;
import Negocio.Proyeccion.TDocumental;
import Negocio.Proyeccion.TPelicula;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class ActualizarProyeccionComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TProyeccion proy = (TProyeccion) contexto.getDato();
		
		boolean ok = FactoriaSA.getInstance().generateSAProyeccion().modificarProyeccion(proy);
		
		if(ok)
			return new Contexto(CommList.ACTUALIZAR_PROYECCION_OK,ok);
		else
			return new Contexto(CommList.ACTUALIZAR_PROYECCION_KO,ok);
	}

}
