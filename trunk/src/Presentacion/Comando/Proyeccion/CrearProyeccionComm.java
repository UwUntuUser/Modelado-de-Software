package Presentacion.Comando.Proyeccion;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Proyeccion.SAProyeccion;
import Negocio.Proyeccion.TDocumental;
import Negocio.Proyeccion.TPelicula;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CrearProyeccionComm implements Comando 
{

	@Override
	public Contexto execute(Contexto contexto) {
		
		TProyeccion proy = (TProyeccion) contexto.getDato();
		
		int ok = FactoriaSA.getInstance().generateSAProyeccion().crearProyeccion(proy);
		
		if(ok != -1)
			return new Contexto(CommList.CREAR_PROYECCION_OK,ok);
		else
			return new Contexto(CommList.CREAR_PROYECCION_KO,ok);
	}
	
	

}
