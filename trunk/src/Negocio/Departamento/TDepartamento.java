package Negocio.Departamento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import Negocio.Empleado.TEmpleado;


/*@Entity
@NamedQueries({
		@NamedQuery(name = "negocio.departamento.Departamento.mostrarDepartamentos", query = "select obj from TDepartamento obj where obj.activo = true"),
		@NamedQuery(name = "negocio.departamento.Departamento.encontrarPorNombre", query = "select obj from TDepartamento obj where obj.nombre = :nombre") })

*/
public class TDepartamento 
{
	
	private String nombre;
	private boolean activo;
	
	//@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//@OneToMany(mappedBy = "Departamento")
	private ArrayList<TEmpleado> empleados; //Hasta que empleado no sea entity, no va a funcionar
	
	public TDepartamento(){}
	
	public TDepartamento(String nombre, boolean activo)
	{
		this.nombre = nombre;
		this.activo = activo;
	}
	
	public TDepartamento(int id, String nombre)
	{
		this.id = id;
		this.nombre = nombre;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<TEmpleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<TEmpleado> empleados) {
		this.empleados = empleados;
	}
	public String toString(){
		String s = "Nombre: " +this.getNombre()+ " Id: " +this.getId() +"\n";
		for (TEmpleado Lineas: empleados) {
			s+=Lineas.toString()+System.lineSeparator();
		}
		return s;
	}
}
