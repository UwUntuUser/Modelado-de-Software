/**
 * 
 */
package Presentacion.Comando;


public abstract class FactoriaComando {

	private static FactoriaComando Instancia;

	public synchronized static FactoriaComando getInstancia() {
		if(Instancia == null){
		 Instancia = new FactoriaComandoImp();
		}
		return Instancia;
	}

	public abstract Comando getComando(Integer comm);
}