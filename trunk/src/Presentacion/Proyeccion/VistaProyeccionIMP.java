package Presentacion.Proyeccion;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;
import Presentacion.Venta.VistaAniadirLineaVenta;
import Presentacion.Venta.VistaCerrarVenta;
import Presentacion.Venta.VistaCrearVenta;
import Presentacion.Venta.VistaEliminarVenta;
import Presentacion.Venta.VistaLeerTodosVenta;
import Presentacion.Venta.VistaLeerVenta;

public class VistaProyeccionIMP  extends VistaProyeccion implements Observer{
	private JTabbedPane pane;
	
	private VistaCrearProyeccion vcp;
	private VistaLeerProyeccion vlp;
	private VistaLeerTodosProyeccion vltp;
	private VistaActualizarProyeccion vap;
	private VistaEliminarProyeccion vep;

	public VistaProyeccionIMP(){
		this.setLayout(new BorderLayout());
		
		pane = new JTabbedPane();

		vcp = FactoriaPresentacion.getInstancia().generarVistaCrearProyeccion();
		vlp = FactoriaPresentacion.getInstancia().generarVistaleerProyeccion();
		vltp = FactoriaPresentacion.getInstancia().generarVistaleerTodosProyeccion();
		vap = FactoriaPresentacion.getInstancia().generarVistaActualizarProyeccion();
		vep = FactoriaPresentacion.getInstancia().generarVistaeliEliminarProyeccion();
		
		pane.add("Crear",vcp);
		pane.add("Leer",vlp);
		pane.add("Leer Todos",vltp);
		pane.add("Actualizar",vap);
		pane.add("Eliminar",vep);
		
		
		pane.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
					switch (((JTabbedPane) e.getSource()).getSelectedIndex()) {
					default:						
						pane.repaint();
						break;

					}
				}
			}
		});
		
		this.add(pane,BorderLayout.CENTER);	

	}


	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ACTUALIZAR_PROYECCION_OK || c.getEvento() == CommList.ACTUALIZAR_PROYECCION_KO) {
			vap.update(c);
		}
		else if (c.getEvento() == CommList.CREAR_PROYECCION_OK || c.getEvento() == CommList.CREAR_PROYECCION_KO) {
			vcp.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_PROYECCION_OK || c.getEvento() == CommList.ELIMINAR_PROYECCION_KO) {
			vep.update(c);
		}
		else if (c.getEvento() == CommList.LEER_PROYECCION_OK || c.getEvento() == CommList.LEER_PROYECCION_KO) {
			vlp.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_PROYECCION_OK || c.getEvento() == CommList.LEER_TODOS_PROYECCION_KO) {
			vltp.update(c);
		}
	}
}
