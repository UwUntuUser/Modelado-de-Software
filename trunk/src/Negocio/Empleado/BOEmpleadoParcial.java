package Negocio.Empleado;

import java.io.Serializable;

import Negocio.Departamento.BODepartamento;
import javax.persistence.*;

 @Entity
 @Table(name="Parcial")
 @NamedQueries({
	 @NamedQuery(name="parcial.findByActivo", query = "Select obj from BOEmpleadoParcial obj where :activo=obj.activo"),
	 @NamedQuery(name="parcial.findByDNI", query = "Select obj from BOEmpleadoParcial obj where :dni=obj.dni"),
	 @NamedQuery(name ="parcial.findById", query= "Select obj from BOEmpleadoParcial obj where :idEmpleado = obj.idEmpleado")})
public class BOEmpleadoParcial extends BOEmpleado implements Serializable{

	private double HorasJornada;
	private double Sueldo_Hora;
	
	private static final long serialVersionUID = 0;
	public BOEmpleadoParcial()
	{
		super();
		this.HorasJornada=0;
		this.Sueldo_Hora=0;
	}
	
	public BOEmpleadoParcial(String dni,String nombre,String apellido, Integer numSS,boolean activo,boolean parcial,double HorasJornada,double SueldoHora)
	{
		super(dni,nombre,apellido,numSS,activo,parcial);
		this.HorasJornada=HorasJornada;
		this.Sueldo_Hora=SueldoHora;
	}
	
	public BOEmpleadoParcial(BODepartamento departamento, TEmpleadoParcial tp) {
		super(departamento, tp);
		this.HorasJornada = tp.getHorasJOrnada();
		this.Sueldo_Hora = tp.getSueldoHora();
	}
	public void setHorasJornada(double horas)
	{
		this.HorasJornada=horas;
	}
	public void setSueldoHora(double sueldo)
	{
		this.Sueldo_Hora=sueldo;
	}
	public double getHorasJornada()
	{
		return this.HorasJornada;
	}
	public double getSueldoHora()
	{
		return this.Sueldo_Hora;
	}
	
	@Override
	public double calcularNomina() {
		return this.HorasJornada*this.Sueldo_Hora;
	}

	
	
	
}
