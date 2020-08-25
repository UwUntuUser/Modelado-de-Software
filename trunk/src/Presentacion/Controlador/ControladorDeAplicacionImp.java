package Presentacion.Controlador;

import java.util.Observer;

import Presentacion.Dispatcher;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Comando.FactoriaComando;
import Presentacion.Competicion.VistaCompeticion;
import Presentacion.Departamento.VistaDepartamento;
import Presentacion.Empleado.VistaEmpleado;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.Pase.VistaPase;
import Presentacion.Proyeccion.VistaProyeccion;
import Presentacion.Venta.VistaVentas;

public class ControladorDeAplicacionImp extends ControladorDeAplicacion {

	@Override
	public void manejarPeticion(Contexto context) {
		
		Comando command=  FactoriaComando.getInstancia().getComando(context.getEvento());
		
		if(command!=null){
			Contexto respuesta=command.execute(context);
			manejarRespuesta(respuesta);
		}
		
		
	}

	@Override
	public void manejarRespuesta(Contexto context) {
		
		Dispatcher.getInstancia().actualizaVistas(context);
		
	}
		
}

