package Negocio.Departamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import Negocio.Empleado.BOEmpleado;
import javax.persistence.*;

 @Entity
 @Table(name="departamento")
 @NamedQueries({
	 @NamedQuery (name="departamento.encontrarPorId", query ="Select obj from BODepartamento obj where :idDep = obj.idDep"),
	 @NamedQuery(name = "departamento.encontrarPorNombre", query = "Select obj from BODepartamento obj where :nombre = obj.nombre"),
	 @NamedQuery(name = "departamento.mostrarTodos", query = "Select obj from BODepartamento obj where :activo = obj.activo")})

public class BODepartamento implements Serializable {

	private static final long serialVersionUID = 0;
	
	@Id
	@Column(name="Id_departamento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idDep;
	public Integer getIdDep() {
		return idDep;
	}
	public void setIdDep(Integer idDep) {
		this.idDep = idDep;
	}
	private String nombre;
	private boolean activo;
	@Version
	private Integer version;
	@OneToMany(mappedBy = "departamento")
	private Collection<BOEmpleado> BOEmpleado;
	
	public Collection<BOEmpleado> getBOEmpleado() {
		return BOEmpleado;
	}
	public void setBOEmpleado(ArrayList<BOEmpleado> bOEmpleado) {
		BOEmpleado = bOEmpleado;
	}
	public BODepartamento()
	{
		this.idDep=null;
		this.nombre=null;
	}
	public BODepartamento(String nombre)
	{
		this.nombre=nombre;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public Collection<BOEmpleado> getEmpleados()
	{
		return this.BOEmpleado;
	}
	public void aniadirEmpleadoDepartamento(BOEmpleado boe) {
		this.BOEmpleado.add(boe);
	}
	public void setActivo(boolean a)
	{
		this.activo=a;
	}
	public boolean getActivo()
	{
		return this.activo;
	}
	public void eliminarEmpleado(BOEmpleado empAux) {
		for (BOEmpleado bo : this.BOEmpleado) {
			if (empAux.getId() == bo.getId()) break;
		}
	}
}
