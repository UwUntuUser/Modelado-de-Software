package Presentacion.Comando.Venta;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Venta.SAVenta;
import Negocio.Venta.TVenta;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerVentaComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {
		
		TVenta nuevo = FactoriaSA.getInstance().generateSAVenta().leerVenta((int) contexto.getDato());
		
		if(nuevo != null)
			return new Contexto(CommList.LEER_VENTA_OK,nuevo);
		else
			return new Contexto(CommList.LEER_VENTA_KO,nuevo);
	}

	

}
