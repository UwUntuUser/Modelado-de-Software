package Presentacion.Comando.Competicion;

import java.util.ArrayList;

import Negocio.Competicion.TCompeticion;
import Negocio.Empleado.TEmpleado;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;

public class AniadirEmpleadoCompeticionComm implements Comando {

	@Override
	public Contexto execute(Contexto requestContext) {		 
			ArrayList<Object> array = (ArrayList<Object>) requestContext.getDato();
			String comp = (String) array.get(0);
			String emp = (String) array.get(1);
			boolean ok = FactoriaSA.getInstance().generateSACompeticion().AniairEmpleadoCompeticion(Integer.parseInt(comp),Integer.parseInt(emp));
			if(ok)
				return new Contexto(CommList.ANIADIR_EMPLEADO_COMPETICION_OK,ok);
			else
				return new Contexto(CommList.ANIADIR_EMPLEADO_COMPETICION_KO,ok);
	}
}
