/**
 * 
 */
package Presentacion.Controlador;

import Presentacion.Comando.Contexto;

public abstract class ControladorDeAplicacion {
	
	public static ControladorDeAplicacion instancia;
	
	public static ControladorDeAplicacion getInstancia()
	{
		if(instancia == null){
			instancia = new ControladorDeAplicacionImp();
		}
		return instancia;
	}
	
	public abstract void manejarPeticion(Contexto context);
	public abstract void manejarRespuesta(Contexto context);


}