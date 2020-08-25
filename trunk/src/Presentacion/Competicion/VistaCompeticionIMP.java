package Presentacion.Competicion;

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

public class VistaCompeticionIMP extends VistaCompeticion implements Observer{
	private JTabbedPane pane;
	
	private VistaCrearCompeticion vcc;
	private VistaLeerCompeticion vlc;
	private VistaAniadirEmpleadoCompeticion vaec;
	private VistaEliminarCompeticion vec;
	private VistaLeerTodosCompeticion vltc;
	private VistaActualizarCompeticion vac;
	private VistaEliminarEmpleadoCompeticion veec;

	public VistaCompeticionIMP(){
		this.setLayout(new BorderLayout());
	
		pane = new JTabbedPane();

		vcc = FactoriaPresentacion.getInstancia().generarVistaCrearCompeticion();
		vlc = FactoriaPresentacion.getInstancia().generarVistaleerCompeticion();
		vaec =FactoriaPresentacion.getInstancia().generarVistaAniadirEmpleadoCompeticion();
		vec = FactoriaPresentacion.getInstancia().generarVistaEliminarCompeticion();
		vltc =FactoriaPresentacion.getInstancia().generarVistaleerTodosCompeticion();
		vac = FactoriaPresentacion.getInstancia().generarVistaActualizarCompeticion();
		veec =FactoriaPresentacion.getInstancia().generarVistaEliminarEmpleadoCompeticion();
		
		pane.add("Crear",vcc);
		pane.add("Leer",vlc);
		pane.add("Aniadir empleado",vaec);
		pane.add("Eliminar",vec);
		pane.add("Leer todos",vltc);
		pane.add("Actualizar",vac);
		pane.add("Eliminar Empleado",veec);
		
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
		if (c.getEvento() == CommList.ACTUALIZAR_COMPETICION_OK || c.getEvento() == CommList.ACTUALIZAR_COMPETICION_KO) {
			vac.update(c);
		}
		else if (c.getEvento() == CommList.ANIADIR_EMPLEADO_COMPETICION_OK || c.getEvento() == CommList.ANIADIR_EMPLEADO_COMPETICION_KO) {
			vaec.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_COMPETICION_OK || c.getEvento() == CommList.ELIMINAR_EMPLEADO_COMPETICION_KO) {
			veec.update(c);
		}
		else if (c.getEvento() == CommList.CREAR_COMPETICION_OK || c.getEvento() == CommList.CREAR_COMPETICION_KO) {
			vcc.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_COMPETICION_OK || c.getEvento() == CommList.ELIMINAR_COMPETICION_KO) {
			vec.update(c);
		}
		else if (c.getEvento() == CommList.LEER_COMPETICION_OK || c.getEvento() == CommList.LEER_COMPETICION_KO) {
			vlc.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_COMPETICION_OK || c.getEvento() == CommList.LEER_TODOS_COMPETICION_KO) {
			vltc.update(c);
		}
	}
}
