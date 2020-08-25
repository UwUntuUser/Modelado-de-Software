package Negocio.Proyeccion;

public class TDocumental extends TProyeccion {

	private String Narrador;
	
	public TDocumental(){}
	
	public TDocumental(int id, String nar, String nom, String gen, int dur, boolean act) {
		super(id, nom, gen, dur , act, true);
		this.Narrador = nar;
	}
	
	public TDocumental(String nar, String nom, String gen, int dur, boolean act) {
		super(nom, gen, dur , act, true);
		this.Narrador = nar;
	}

	public String getNarrador() {
		return Narrador;
	}

	public void setNarrador(String Narrador) {
		this.Narrador = Narrador;
	}

	@Override
	public String toString() {
		String s = super.toString();
		s += "\tTipo: Documental" + System.lineSeparator();
		s += "Narrador: " + this.Narrador + System.lineSeparator();
		return s;
	}
}