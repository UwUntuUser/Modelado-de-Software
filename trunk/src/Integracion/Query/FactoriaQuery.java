package Integracion.Query;

public abstract class FactoriaQuery {

	private static FactoriaQuery instance;
	
	public static FactoriaQuery getInstance() {
		if(instance == null){
			instance = new FactoriaQueryImp();
		}
		return instance;
	}
		
		
		public abstract PelisPorDuracion generarPelisPorHora();
		public abstract PelisPorGenero generarPelisPorGenero();
}
