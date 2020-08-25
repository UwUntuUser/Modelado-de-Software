package Integracion.Transactionmanager;

public abstract class TransactionFactorySingleton {
	
	private static TransactionFactorySingleton Instance = null;
	public static TransactionFactorySingleton getInstance(){
		if(Instance == null){
			Instance = new TransactionFactoryIMP();
		}
		return Instance;
	}
	public abstract Transaccion  createTransaccion();
	
}