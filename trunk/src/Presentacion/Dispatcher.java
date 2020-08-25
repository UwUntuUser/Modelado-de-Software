package Presentacion;

import Presentacion.Comando.Contexto;

public abstract class Dispatcher {
	
private static DispatcherIMP instancia;
	
	public static DispatcherIMP getInstancia(){
		if(instancia == null){
			instancia = new DispatcherIMP();
		}
		return instancia;
	}
	
	public abstract void actualizaVistas(Contexto r);

}