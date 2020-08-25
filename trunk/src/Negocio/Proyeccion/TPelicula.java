package Negocio.Proyeccion;

public class TPelicula extends TProyeccion {

	private String Sinopsis;
	
	public TPelicula(){}
	
	public TPelicula(int id, String sin, String nom, String gen, int dur, boolean act) {
		super(id, nom, gen, dur, act, false);
		this.Sinopsis = sin;
	}
	
	public TPelicula(String sin, String nom, String gen, int dur, boolean act) {
		super(nom, gen, dur, act, false);
		this.Sinopsis = sin;
	}

	public String getSinopsis() {
		return Sinopsis;
	}
	
	public void setSinopsis(String sinopsis) {
		this.Sinopsis = sinopsis;
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += "\tTipo: Pelicula" + System.lineSeparator();
		s += "Sinopsis: " + this.Sinopsis + System.lineSeparator();
		return s;
	}
}