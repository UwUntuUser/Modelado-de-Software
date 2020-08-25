package Presentacion.Empleado;

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

public class VistaEmpleadoIMP extends VistaEmpleado implements Observer {

	private JTabbedPane pane;
	
	private VistaCrearEmpleado vce;
	private VistaLeerEmpleado vle;
	private VistaEliminarEmpleado vee;
	private VistaLeerTodosEmpleado vlte;
	private VistaActualizarEmpleado vae;

	public VistaEmpleadoIMP(){
		this.setLayout(new BorderLayout());
	
		pane = new JTabbedPane();
		
		vce = FactoriaPresentacion.getInstancia().generarVistaCrearEmpleado();
		vle = FactoriaPresentacion.getInstancia().generarVistaleerEmpleado();
		vee = FactoriaPresentacion.getInstancia().generarVistaEliminarEmpleado();
		vlte = FactoriaPresentacion.getInstancia().generarVistaleerTodosEmpleado();
		vae = FactoriaPresentacion.getInstancia().generarVistaActualizarEmpleado();
		
		pane.add("Crear",vce);
		pane.add("Leer",vle);
		pane.add("Eliminar",vee);
		pane.add("Leer todos",vlte);
		pane.add("Actualizar",vae);
		
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
		if (c.getEvento() == CommList.ACTUALIZAR_EMPLEADO_OK || c.getEvento() == CommList.ACTUALIZAR_EMPLEADO_KO) {
			vae.update(c);
		}
		else if (c.getEvento() == CommList.CREAR_EMPLEADO_OK || c.getEvento() == CommList.CREAR_EMPLEADO_KO) {
			vce.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_OK || c.getEvento() == CommList.ELIMINAR_EMPLEADO_KO) {
			vee.update(c);
		}

		else if (c.getEvento() == CommList.LEER_EMPLEADO_OK || c.getEvento() == CommList.LEER_EMPLEADO_KO) {
			vle.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_EMPLEADO_OK || c.getEvento() == CommList.LEER_TODOS_EMPLEADO_KO) {
			vlte.update(c);
		}
		
	}
}
