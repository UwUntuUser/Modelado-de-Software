package Negocio.Venta;

public class TLinea_Venta {

	private int Cantidad;
	private int ID_Pase;
	private final double Precio = 5.0;
	public int ID_Venta;
	
	public TLinea_Venta(int venta,int pase, int cant){
		ID_Venta = venta;
		ID_Pase = pase;
		Cantidad = cant;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int Cantidad) {
		this.Cantidad = Cantidad;
	}

	public int getID_Pase() {
		return ID_Pase;
	}

	public void setID_Pase(int ID_Pase) {
		this.ID_Pase = ID_Pase;
	}

	public double getPrecio() {
		return Precio;
	}

/*	public void setPrecio(double Precio) {
		this.Precio = Precio;
	}*/
	
	public int getID_Venta(){
		return this.ID_Venta;
	}

	@Override
	public String toString(){
		return "ID Pase:"+this.ID_Pase+"\tCantidad:"+this.Cantidad+"\tPrecio:"+this.Precio;
	}
}