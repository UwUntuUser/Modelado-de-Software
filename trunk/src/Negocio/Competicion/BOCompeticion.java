package Negocio.Competicion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import Negocio.Empleado.BOEmpleado;
import Negocio.Empleado.BOEmpleadoCompeticion;

@Entity
@Table(name="competicion")
@NamedQueries ({
	@NamedQuery (name = "Negocio.Competicion.encontrarPorId",query = "select obj from BOCompeticion obj where :id =obj.id")	,
	@NamedQuery (name = "Negocio.Competicion.findByActivo",query = "select obj from BOCompeticion obj where :activo =obj.activo") ,	
	@NamedQuery (name = "Negocio.Competicion.findByName",query = "select obj from BOCompeticion obj where :nombre = obj.nombre")
})
public class BOCompeticion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue( strategy =GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "competicion")
	private Collection<BOEmpleadoCompeticion> empleadoCompeticion;
	
	@Version
	private Integer version;
	
	private String nombre;
	private boolean activo;
	private Collection<BOEmpleado> empleados;

	public BOCompeticion(){}
	
	public BOCompeticion(Integer id, Integer version, String nombre, boolean activo,
			Collection<BOEmpleado> empleados) {
		super();
		this.id = id;
		this.version = version;
		this.nombre = nombre;
		this.activo = activo;
		this.empleados = empleados;
	}

	public Collection<BOEmpleado> getEmpleados() {
		return  this.empleados;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public Collection<BOEmpleadoCompeticion> getEmpleadoCompeticion() {
		return empleadoCompeticion;
	}

	public void setEmpleadoCompeticion(Collection<BOEmpleadoCompeticion> empleadoCompeticion) {
		this.empleadoCompeticion = empleadoCompeticion;
	}

	public void setEmpleados(Collection<BOEmpleado> empleados) {
		this.empleados = empleados;
	}
	public void aniadirEmpleado(BOEmpleadoCompeticion e)
	{
		this.empleadoCompeticion.add(e);
	}
	public void aniadirEmpleados(BOEmpleado b) {
		empleados.add(b);
	}
	public boolean existenEmpleados() {
		boolean existenEmp = false;
		for (BOEmpleadoCompeticion ec : this.empleadoCompeticion) {
			existenEmp = ec.getEmpleado().getActivo() || existenEmp;
			if (existenEmp) break;
		}
		return existenEmp;
	}
	public void eliminarEmpleado(int idEmp) {
		for (BOEmpleado BOE : this.empleados) {
			if (BOE.getId() == idEmp) {
				this.empleados.remove(BOE);
				break;
			}
		}
	}
}
