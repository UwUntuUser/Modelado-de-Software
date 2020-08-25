package Presentacion.Comando.Venta;

import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class CrearVentaComm implements Comando {

	@Override
	public Contexto execute(Contexto contexto) {


		int ok = FactoriaSA.getInstance().generateSAVenta().crearVenta();
		
		if(ok != -1)
			return new Contexto(CommList.CREAR_VENTA_OK,ok);
		else
			return new Contexto(CommList.CREAR_VENTA_KO,ok);
	}

	

}
