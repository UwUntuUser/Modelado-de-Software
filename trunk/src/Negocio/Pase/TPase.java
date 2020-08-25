package Negocio.Pase;

public class TPase {

	private int ID_Pase;
	private int hora;
	private int ID_Proyeccion;
	private boolean activo;
	
	public TPase(int id, int idProyeccion, int h, boolean activo) {
		this.ID_Pase = id;
		this.hora = h;
		this.ID_Proyeccion = idProyeccion;
		this.activo = activo;
	}

	public TPase(int id, int h) {
		this.ID_Proyeccion = id;
		this.hora = h;
	}

	public TPase() {
	}

	public int getID_Pase() {
		return ID_Pase;
	}

	public void setID_Pase(int ID_Pase) {
		this.ID_Pase = ID_Pase;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getID_Proyeccion() {
		return ID_Proyeccion;
	}

	public void setID_Proyeccion(int ID_Proyeccion) {
		this.ID_Proyeccion = ID_Proyeccion;
	}
	
	public String toString(){
		String s ="";
		s += "ID Pase: " + ID_Pase + "\tID_Proyeccion: "+ ID_Proyeccion + System.lineSeparator();
		s += "Hora: " + hora/100 + ":" + hora%100 + System.lineSeparator();
		return s;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}