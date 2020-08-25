package Negocio.Empleado;
import java.io.Serializable;

import javax.persistence.*;



import Negocio.Competicion.BOCompeticion;



@Entity
@Table(name = "empleado_competicion")
public class BOEmpleadoCompeticion implements Serializable {


	private static final long serialVersionUID = 0;

	@EmbeddedId private BOEmpleadoCompeticionId id;
	
	@ManyToOne
	@MapsId private BOEmpleado empleado;
	
	@ManyToOne
	@MapsId private BOCompeticion competicion;
	
	@Version
	private int version;
	
	private double premio;
	private boolean activo;
	
	
	public BOEmpleadoCompeticion() {}
	public BOEmpleadoCompeticion(BOEmpleadoCompeticionId id, double premio) {
		
		this.id=id;
		this.premio=premio;
	}
	
	public void setEmpleado(BOEmpleado e)
	{
		this.empleado = e;
	}
	public void setCompeticion(BOCompeticion c)
	{
		this.competicion = c;
	}
	public void setVersion(int v)
	{
		version=v;
	}
	public int getVersion()
	{
		return version;
	}
	public double getPremio()
	{
		return this.premio;
	}
	public void setPremio(double premio)
	{
		this.premio=premio;
	}


	public boolean getActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public BOEmpleadoCompeticionId getId() {
		return id;
	}
	public void setId(BOEmpleadoCompeticionId id) {
		this.id = id;
	}
	public BOEmpleado getEmpleado() {
		return empleado;
	}
	public BOCompeticion getCompeticion() {
		return competicion;
	}
}
