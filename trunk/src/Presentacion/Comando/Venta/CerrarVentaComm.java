package Presentacion.Comando.Venta;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Venta.SAVenta;
import Negocio.Venta.TVenta;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CerrarVentaComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		
		
		
		double ok = FactoriaSA.getInstance().generateSAVenta().cerrarVenta((int)contexto.getDato());
		
		if(ok != -1)
			return new Contexto(CommList.CERRAR_VENTA_OK,ok);
		else
			return new Contexto(CommList.CERRAR_VENTA_KO,ok);
	}

	

}
