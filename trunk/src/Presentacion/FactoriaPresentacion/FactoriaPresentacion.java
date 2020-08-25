/**
 * 
 */
package Presentacion.FactoriaPresentacion;

public abstract class FactoriaPresentacion {
	
private static FactoriaPresentacionImp instancia;
	
	public synchronized static FactoriaPresentacionImp getInstancia(){
		if(instancia == null){
			instancia = new FactoriaPresentacionImp();
		}
		return instancia;
	}

}