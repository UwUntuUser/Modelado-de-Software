package Presentacion.Comando.Venta;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Venta.SAVenta;
import Negocio.Venta.TVenta;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class EliminarVentaComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		boolean ok = FactoriaSA.getInstance().generateSAVenta().eliminarVenta((int)contexto.getDato());
		
		if(ok)
			return new Contexto(CommList.ELIMINAR_VENTA_OK,ok);
		else
			return new Contexto(CommList.ELIMINAR_VENTA_KO,ok);
	}

	

}
