package Presentacion.Comando.Venta;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Venta.TLinea_Venta;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class AniadirLineaVentaComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		TLinea_Venta lv = (TLinea_Venta) contexto.getDato();
		boolean ok = FactoriaSA.getInstance().generateSAVenta().aniadirLineaDeVenta(lv);
		if(ok)
			return new Contexto(CommList.ANIADIR_LINEA_VENTA_OK,ok);
		else
			return new Contexto(CommList.ANIADIR_LINEA_VENTA_KO,ok);
	}
	
}
	
	

	


