package Presentacion.Departamento;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Competicion.VistaActualizarCompeticion;
import Presentacion.Competicion.VistaAniadirEmpleadoCompeticion;
import Presentacion.Competicion.VistaCrearCompeticion;
import Presentacion.Competicion.VistaEliminarCompeticion;
import Presentacion.Competicion.VistaEliminarEmpleadoCompeticion;
import Presentacion.Competicion.VistaLeerCompeticion;
import Presentacion.Competicion.VistaLeerTodosCompeticion;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;

public class VistaDepartamentoIMP extends VistaDepartamento implements Observer{
	private JTabbedPane pane;
	
	private VistaCrearDepartamento vcd;
	private VistaLeerDepartamento vld;
	private VistaAniadirEmpleadoDepartamento vaed;
	private VistaEliminarDepartamento ved;
	private VistaLeerTodosDepartamento vltd;
	private VistaActualizarDepartamento vad;
	private VistaEliminarEmpleadoDepartamento veed;
	private VistaCalcularNominaDepartamento vcnd;

	public VistaDepartamentoIMP(){
		this.setLayout(new BorderLayout());
	
		pane = new JTabbedPane();
	
		vcd = FactoriaPresentacion.getInstancia().generarVistaCrearDepartamento();
		vld = FactoriaPresentacion.getInstancia().generarVistaleerDepartamento();
		vaed = FactoriaPresentacion.getInstancia().generarVistaAniadirEmpleadoDepartamento();
		ved = FactoriaPresentacion.getInstancia().generarVistaEliminarDepartamento();
		vltd = FactoriaPresentacion.getInstancia().generarVistaleerTodosDepartamento();
		vad = FactoriaPresentacion.getInstancia().generarVistaActualizarDepartamento();
		veed = FactoriaPresentacion.getInstancia().generarVistaEliminarEmpleadoDepartamento();
		vcnd = FactoriaPresentacion.getInstancia().generarVistaCalcularNominaDepartamento();
		
		pane.add("Crear",vcd);
		pane.add("Leer",vld);
		pane.add("Aniadir empleado",vaed);
		pane.add("Calcular nomina", vcnd);
		pane.add("Eliminar",ved);
		pane.add("Leer todos",vltd);
		pane.add("Actualizar",vad);
		pane.add("Eliminar Empleado",veed);
		
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
		if (c.getEvento() == CommList.ACTUALIZAR_DEPARTAMENTO_OK || c.getEvento() == CommList.ACTUALIZAR_DEPARTAMENTO_KO) {
			vad.update(c);
		}
		else if (c.getEvento() == CommList.ANIADIR_EMPLEADO_DEPARTAMENTO_OK || c.getEvento() == CommList.ANIADIR_EMPLEADO_DEPARTAMENTO_KO) {
			vaed.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO_OK || c.getEvento() == CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO_KO) {
			veed.update(c);
		}
		else if (c.getEvento() == CommList.CREAR_DEPARTAMENTO_OK || c.getEvento() == CommList.CREAR_DEPARTAMENTO_KO) {
			vcd.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_DEPARTAMENTO_OK || c.getEvento() == CommList.ELIMINAR_DEPARTAMENTO_KO) {
			ved.update(c);
		}
		else if (c.getEvento() == CommList.LEER_DEPARTAMENTO_OK || c.getEvento() == CommList.LEER_DEPARTAMENTO_KO) {
			vld.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_DEPARTAMENTO_OK || c.getEvento() == CommList.LEER_TODOS_DEPARTAMENTO_KO) {
			vltd.update(c);
		}
		else if (c.getEvento() == CommList.CALCULAR_NOMINA_OK || c.getEvento() == CommList.CALCULAR_NOMINA_KO) {
			vcnd.update(c);
		}
	}
}
