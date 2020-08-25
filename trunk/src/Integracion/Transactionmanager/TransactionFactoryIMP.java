package Integracion.Transactionmanager;

public class TransactionFactoryIMP extends TransactionFactorySingleton {
	
	public Transaccion createTransaccion(){
		return new TransaccionIMP();
	}
}