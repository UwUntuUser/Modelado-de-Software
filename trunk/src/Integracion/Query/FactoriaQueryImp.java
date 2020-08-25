package Integracion.Query;

public class FactoriaQueryImp extends FactoriaQuery{

	@Override
	public PelisPorDuracion generarPelisPorHora() {
		// TODO Auto-generated method stub
		return new PelisPorDuracion();
	}

	@Override
	public PelisPorGenero generarPelisPorGenero() {
		// TODO Auto-generated method stub
		return new PelisPorGenero();
	}

}
