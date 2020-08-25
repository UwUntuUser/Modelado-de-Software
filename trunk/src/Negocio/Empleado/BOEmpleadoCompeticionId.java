package Negocio.Empleado;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class BOEmpleadoCompeticionId implements Serializable {


	private static final long serialVersionUID = 0;
	
	private Integer empleado;
	private Integer competicion;
	
	public BOEmpleadoCompeticionId(int idemp, int idcomp) {
		empleado = idemp;
		competicion = idcomp;
	}
	
	public BOEmpleadoCompeticionId() {}

	public int getCompeticion() {
		return competicion;
	}

	public void setCompeticion(int competicion) {
		this.competicion = competicion;
	}

	public int getEmpleado() {
		return empleado;
	}

	public void setEmpleado(int empleado) {
		this.empleado = empleado;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		else if (obj != this) return false;
		BOEmpleadoCompeticionId BOECId = (BOEmpleadoCompeticionId) obj;
		if (empleado == null) {
			if (BOECId != null) {
				return false;
			}
		}
		if(competicion == null) {
			if (BOECId.competicion != null) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int codigo;
		codigo = ((empleado == null) ? 0 : empleado.hashCode()) + 13;
		codigo = ((empleado == null) ? 0 : empleado.hashCode()) + 13;
		return codigo;
	}

}
