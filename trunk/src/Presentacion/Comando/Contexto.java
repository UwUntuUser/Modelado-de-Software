package Presentacion.Comando;

public class Contexto {
	
	private Object dato;
	private Integer comando;
	
	public Contexto(){
		this.dato = null;
		this.comando = null;
	}
	public Contexto(int comando, Object dato)
	{
		this.comando=comando;
		this.dato=dato;
	}
	
	public Object getDato() {
		return dato;
	}
	public void setDato(Object dato) {
		this.dato = dato;
	}

	public Integer getEvento() {
		return comando;
	}
	public void setEvento(Integer evento) {
		this.comando = evento;
	}
	
	

}
