
package Presentacion.Pase;

import java.awt.BorderLayout;
import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.FactoriaPresentacion.FactoriaPresentacion;




public class VistaPaseIMP extends VistaPase implements Observer {
	
	private JTabbedPane pane;
	
	VistaCrearPase vp;
	VistaEliminarPase vep;
	VistaActualizarPase vap;
	VistaLeerPase vlp;
	VistaLeerTodosPase vltp;

	
	private ArrayList<Observer> observadores;
	
	public VistaPaseIMP(){
		this.setLayout(new BorderLayout());
		
		pane = new JTabbedPane();
		observadores = new ArrayList<>();
				
		vp = FactoriaPresentacion.getInstancia().generarVistaCrearPase();
		vep = FactoriaPresentacion.getInstancia().generarVistaEliminarPase();
		vap = FactoriaPresentacion.getInstancia().generarVistaActualizarPase();
		vlp = FactoriaPresentacion.getInstancia().generarVistaLeerPase();
		vltp = FactoriaPresentacion.getInstancia().generarVistaLeerTodosPase();

		
		pane.add("Crear",vp);
		pane.add("Leer",vlp);
		pane.add("Leer Todos",vltp);
		pane.add("Actualizar",vap);
		pane.add("Eliminar",vep);
		
		observadores.add(vp);
		observadores.add(vep);
		observadores.add(vap);
		observadores.add(vlp);
		observadores.add(vltp);
		
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
		if (c.getEvento() == CommList.ACTUALIZAR_PASE_OK || c.getEvento() == CommList.ACTUALIZAR_PASE_KO) {
			vap.update(c);
		}

		else if (c.getEvento() == CommList.ELIMINAR_PASE_OK || c.getEvento() == CommList.ELIMINAR_PASE_KO) {
			vep.update(c);
		}
		else if (c.getEvento() == CommList.CREAR_PASE_OK || c.getEvento() == CommList.CREAR_PASE_KO) {
			vp.update(c);
		}

		else if (c.getEvento() == CommList.LEER_PASE_OK || c.getEvento() == CommList.LEER_PASE_KO) {
			vlp.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_PASE_OK || c.getEvento() == CommList.LEER_TODOS_PASE_KO) {
			vltp.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_PASE_HORA_OK || c.getEvento() == CommList.LEER_TODOS_PASE_HORA_KO) {
            vltp.update(c);
        }
		else if (c.getEvento() == CommList.QUERY_LEER_PELIS_POR_GENERO_OK || c.getEvento() == CommList.QUERY_LEER_PELIS_POR_GENERO_KO) {
            vltp.update(c);
        }
		else if (c.getEvento() == CommList.QUERY_LEER_PELIS_POR_DURACION_OK || c.getEvento() == CommList.QUERY_LEER_PELIS_POR_DURACION_KO) {
            vltp.update(c);
        }

	}
}