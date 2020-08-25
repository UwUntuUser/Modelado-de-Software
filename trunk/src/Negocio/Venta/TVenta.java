package Negocio.Venta;

import java.util.ArrayList;

public class TVenta {
	
	private int ID_Venta;
	private ArrayList<TLinea_Venta> Linea_Venta;
	private boolean active;
	
	public TVenta (int id){
		this.ID_Venta = id;
		this.Linea_Venta = new ArrayList<TLinea_Venta>();
		this.active = true;
	}
	
	public TVenta() {
		// TODO Auto-generated constructor stub
	}

	public int getID_Venta() {
		return ID_Venta;
	}
	
	public void setID_Venta(int ID_Venta) {
		this.ID_Venta = ID_Venta;
	}

	public ArrayList<TLinea_Venta> getLinea_Venta() {
		return Linea_Venta;
	}

	public void setLinea_Venta(ArrayList<TLinea_Venta> linea_Venta) {
		Linea_Venta = linea_Venta;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString(){
		String s="ID venta: "+this.getID_Venta()+"\n " ;
		for (TLinea_Venta Lineas: Linea_Venta) {
			s+=Lineas.toString()+System.lineSeparator();
		}
		return s;
	}
}