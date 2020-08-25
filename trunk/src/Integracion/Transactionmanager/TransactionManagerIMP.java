package Integracion.Transactionmanager;

import java.util.concurrent.ConcurrentHashMap;

public class TransactionManagerIMP extends TransactionManagerSingleton  {

	
	private ConcurrentHashMap<Thread, Transaccion> transactions;

	public TransactionManagerIMP() {
		transactions=new ConcurrentHashMap<Thread, Transaccion>();
	}
	
	public Transaccion nuevaTransaccion() {
		Thread thread = Thread.currentThread();
		Transaccion transaction = null;
		if (transactions != null) {
			transaction = this.transactions.get(thread);
			if(transaction == null){
				TransactionFactorySingleton transactionFactory = TransactionFactorySingleton.getInstance();
				transaction = transactionFactory.createTransaccion();
				this.transactions.put(thread, transaction);
			}
		}
		return transaction;
	}

	public void eliminaTransaccion() {
		Thread thread = Thread.currentThread();
		if (this.transactions != null) {
			this.transactions.remove(thread);
		}
		
	}

	public Transaccion getTransaccion() {
		Thread thread = Thread.currentThread();
		Transaccion transaction = null;
		if (this.transactions != null) {
			transaction = this.transactions.get(thread);
		}
		return transaction;
	}
}