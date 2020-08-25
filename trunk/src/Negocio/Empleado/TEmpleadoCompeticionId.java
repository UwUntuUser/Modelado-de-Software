package Negocio.Empleado;

public class TEmpleadoCompeticionId {
	
	private int premio;
	private int idEmpleado;
	private int idCompeticion;
	
	public TEmpleadoCompeticionId(int p, int e, int c) {
		this.premio = p;
		this.idEmpleado = e;
		this.idCompeticion = c;
	}
	
	public TEmpleadoCompeticionId(int e, int c) {
		this.idEmpleado = e;
		this.idCompeticion = c;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdCompeticion() {
		return idCompeticion;
	}

	public void setIdCompeticion(int idCompeticion) {
		this.idCompeticion = idCompeticion;
	}
	
	

}
