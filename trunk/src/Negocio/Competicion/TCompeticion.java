package Negocio.Competicion;

import java.util.ArrayList;

import Negocio.Empleado.TEmpleado;
import Negocio.Venta.TLinea_Venta;

public class TCompeticion {
	private Integer idCompeticion;
	private String nombre;
	private boolean activo;
	private ArrayList<TEmpleado> empleados;
	
	public TCompeticion(){}
	
	public TCompeticion(String nombre){
		this.nombre = nombre;
		empleados = null;
	}
	
	public TCompeticion(Integer idCompeticion, String nombre){
		this.idCompeticion = idCompeticion;
		this.nombre = nombre;
		empleados = null;
	}
	
	public TCompeticion(Integer idCompeticion, String nombre, boolean activo,
			ArrayList<TEmpleado> empleados) {
		super();
		this.idCompeticion = idCompeticion;
		this.nombre = nombre;
		this.activo = activo;
		this.empleados = empleados;
	}
	public Integer getIdCompeticion() {
		return idCompeticion;
	}
	public void setIdCompeticion(Integer idCompeticion) {
		this.idCompeticion = idCompeticion;
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
	public ArrayList<TEmpleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(ArrayList<TEmpleado> empleados) {
		this.empleados = empleados;
	}
	public String toString(){
		String s="ID Competicion: "+this.getIdCompeticion()+" Nombre: "+this.getNombre()+"\n ";
		for (TEmpleado Lineas: empleados) {
			s+=Lineas.toString()+System.lineSeparator();
		}
		return s;
	}
}
