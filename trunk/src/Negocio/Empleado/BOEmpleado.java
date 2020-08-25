package Negocio.Empleado;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import Negocio.Departamento.BODepartamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.persistence.NamedQueries;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
@Table(name="empleado")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(
		{
			 @NamedQuery(name="BOEmpleado.findByActivo", query = "Select obj from BOEmpleado obj where :activo=obj.activo"),
			@NamedQuery(name="BOEmpleado.findByDNI", query = "Select obj from BOEmpleado obj where :dni=obj.dni"),
			@NamedQuery(name="BOEmpleado.findById", query = "Select obj from BOEmpleado obj where :id=obj.idEmpleado")
		}
)
public abstract class BOEmpleado implements Serializable{
	



private static final long serialVersionUID =0;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEmpleado;
	
	@ManyToOne
	private BODepartamento departamento;
	
	@OneToMany(mappedBy = "empleado")
	private Collection<BOEmpleadoCompeticion> empleadoCompeticion;
	
	private String dni;
	private String nombre;
	private String apellido;
	private Integer numSS;
	private Boolean activo;
	private Boolean es_parcial;
	
	@Version
	private Integer version;
	
	
	
	public BODepartamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(BODepartamento departamento) {
		this.departamento = departamento;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getNumSS() {
		return numSS;
	}
	public void setNumSS(Integer numSS) {
		this.numSS = numSS;
	}
	public Boolean getEs_parcial() {
		return es_parcial;
	}
	public void setEs_parcial(Boolean es_parcial) {
		this.es_parcial = es_parcial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getApellido() {
		return apellido;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Integer getId()
	{
		return this.idEmpleado;
	}
	public boolean getActivo()
	{
		return this.activo;
	}
	public String getNombre(){
		return this.nombre;
	}
	public BOEmpleado()
	{
		this.idEmpleado=null;
		this.version=null;
		this.dni=null;
		this.nombre=null;
		this.apellido=null;
		this.numSS=null;
		this.activo=null;
		this.es_parcial=null;
	}
	
	public BOEmpleado(String dni,String nombre,String apellido,Integer numSS,boolean activo,boolean parcial)
	{
		this.dni=dni;
		this.nombre=nombre;
		this.apellido=apellido;
		this.numSS=numSS;
		this.es_parcial=parcial;
		this.activo=activo;
	}
	
	public BOEmpleado(BODepartamento d, TEmpleado e) {
		this.departamento = d;
		this.dni=e.getDNI();
		this.nombre=e.getNombre();
		this.apellido=e.getApellido();
		this.numSS=e.getNumSS();
		this.es_parcial=e.isEsParcial();
		this.activo=e.isActivo();
	}
	
	public void aniadirCompeticion(BOEmpleadoCompeticion emp)
	{
		this.empleadoCompeticion.add(emp);
	}
	

	public abstract double calcularNomina();
}
