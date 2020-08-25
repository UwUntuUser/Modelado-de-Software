/**
 * 
 */
package Integracion.Transactionmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransaccionIMP implements Transaccion {
	private Connection connection;

	public TransaccionIMP(){
		try {
			String DB_URL = "jdbc:mysql://localhost/emeseii";
			String USER = "root";
			String PASS = "1234";
			this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			connection=null;
			e.printStackTrace();
		}
	}
	public void start() {
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commit() {
		try {
			this.connection.commit();
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			this.connection.rollback();;
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Object getResource() {
		return this.connection;
	}
}