package Negocio.Empleado;

import java.io.Serializable;
import java.util.jar.Attributes.Name;

import javax.persistence.*;
import Negocio.Departamento.BODepartamento;

@Entity
@Table(name="Completo")
@NamedQueries({
	 @NamedQuery(name="completo.findByActivo", query = "Select obj from BOEmpleadoCompleto obj where :activo=obj.activo"),
	@NamedQuery(name="completo.findByDNI", query = "Select obj from BOEmpleadoCompleto obj where :dni=obj.dni"),
	@NamedQuery(name ="completo.findById",query="select obj from BOEmpleadoCompleto obj where :idEmpleado = obj.idEmpleado")
})


public class BOEmpleadoCompleto extends BOEmpleado implements Serializable{

	private static final long serialVersionUID = 0;
	
	private double sueldoMensual;
	private double antiguedad;
	
	public BOEmpleadoCompleto(){
		super();
		this.antiguedad=0;
		this.sueldoMensual=0;
	}

	public BOEmpleadoCompleto(String dni,String nombre,String apellido, Integer numSS,boolean activo,boolean parcial,double antiguedad,double Sueldo)
	{
		super(dni,nombre,apellido,numSS,activo,parcial);
		this.antiguedad=antiguedad;
		this.sueldoMensual=Sueldo;
	}
	
	public BOEmpleadoCompleto(BODepartamento departamento, TEmpleadoFijo tp) {
		super(departamento, tp);
		this.antiguedad = tp.getAntiguedad();
		this.sueldoMensual = tp.getSueldoMensual();
	}
	
	public void setSueldoMensual(double sueldo)
	{
		this.sueldoMensual=sueldo;
	}
	public void setAntiguedad(double antiguedad)
	{
		this.antiguedad=antiguedad;
	}
	public double getSueldoMensual()
	{
		return this.sueldoMensual;
	}
	public double getAntiguedad()
	{
		return this.antiguedad;
	}
	@Override
	public double calcularNomina() {
		return sueldoMensual;
	}

}
