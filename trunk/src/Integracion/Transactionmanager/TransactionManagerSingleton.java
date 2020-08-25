package Integracion.Transactionmanager;

public abstract class TransactionManagerSingleton {
	private static TransactionManagerSingleton Instancia;

	public static synchronized TransactionManagerSingleton getInstancia(){
		if(Instancia == null){
			Instancia = new TransactionManagerIMP();
		}
		return Instancia;
	}
	public abstract Transaccion nuevaTransaccion();
	public abstract void eliminaTransaccion();
	public abstract Transaccion getTransaccion();
}