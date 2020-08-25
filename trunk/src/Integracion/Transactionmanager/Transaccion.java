package Integracion.Transactionmanager;

public interface Transaccion {

	public void start();
	public void commit();
	public void rollback();
	public Object getResource();
}