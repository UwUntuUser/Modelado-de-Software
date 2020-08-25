package Negocio.Proyeccion;


public class TProyeccion 
{
	
	private int ID_Proyeccion;
	private String Nombre;
	private String Genero;
	private int Duracion;
	private boolean Activo;
	private boolean es_documental;
	
	public TProyeccion(){}
	
	public TProyeccion(int id, String nom, String gen, int dur , boolean act, boolean es_documental) {
		this.ID_Proyeccion = id;
		this.Nombre = nom;
		this.Genero = gen;
		this.Duracion = dur;
		this.Activo = act;
		this.es_documental = es_documental;
	}
	
	public TProyeccion(String nom, String gen, int dur , boolean act, boolean es_documental) {
		this.Nombre = nom;
		this.Genero = gen;
		this.Duracion = dur;
		this.Activo = act;
		this.es_documental = es_documental;
	}
	
	public boolean isEs_Documental() {
		return es_documental;
	}

	public void setEs_Documental(boolean es_Documental) {
		this.es_documental = es_Documental;
	}

	public int getID_Proyeccion() 
	{
		return ID_Proyeccion;
	}

	public void setID_Proyeccion(int ID_Proyeccion) 
	{
		this.ID_Proyeccion = ID_Proyeccion;
	}

	public String getNombre() 
	{
		return Nombre;
	}

	public void setNombre(String Nombre) 
	{
		this.Nombre = Nombre;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String Genero) {
		this.Genero = Genero;
	}

	public int getDuracion() {
		return Duracion;
	}

	public void setDuracion(int Duracion) {
		this.Duracion = Duracion;
	}
	
	public boolean getActivo()
	{
		return this.Activo;
	}
	
	public void setActivo(boolean activo)
	{
		this.Activo = activo;
	}
	
	@Override
	public String toString() {
		String s = "ID Proyeccion: " + this.ID_Proyeccion + "\tNombre: " + this.Nombre + System.lineSeparator();
		s += "Genero: " + this.Genero + "\tDuracion " + this.Duracion;
		return s;
	}

}