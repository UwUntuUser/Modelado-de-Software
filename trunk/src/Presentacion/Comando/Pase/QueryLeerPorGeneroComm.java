package Presentacion.Comando.Pase;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;

import Negocio.Proyeccion.TPelicula;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class QueryLeerPorGeneroComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		ArrayList<TPelicula> peliculas = FactoriaSA.getInstance().generateSAPase().pelisPorGenero((String) contexto.getDato());
		
		if(peliculas == null)
			return new Contexto(CommList.QUERY_LEER_PELIS_POR_GENERO_KO,peliculas);
		else
			return new Contexto(CommList.QUERY_LEER_PELIS_POR_GENERO_OK,peliculas);
		
	}

	
	
}
