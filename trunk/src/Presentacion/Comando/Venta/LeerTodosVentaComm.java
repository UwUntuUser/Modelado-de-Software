package Presentacion.Comando.Venta;

import java.util.ArrayList;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.Venta.SAVenta;
import Negocio.Venta.TVenta;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class LeerTodosVentaComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {
		
		
		ArrayList<TVenta> v = FactoriaSA.getInstance().generateSAVenta().leerTodas();
		
		if(!v.isEmpty())
			return new Contexto(CommList.LEER_TODOS_VENTA_OK,v);
		else
			return new Contexto(CommList.LEER_TODOS_VENTA_KO,v);
	}

	
	

}
