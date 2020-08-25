package Negocio.Empleado;

public class TEmpleadoCompeticion {

	
	private int idEmpleado;
	private int idCompeticion;
	private int premio;
	
	public TEmpleadoCompeticion(int emp, int c, int premio)
	{
		this.setIdCompeticion(c);
		this.setIdEmpleado(emp);
		this.setPremio(premio);
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

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}
	
}
