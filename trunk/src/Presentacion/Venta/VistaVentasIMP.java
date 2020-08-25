package Presentacion.Venta;

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
import Presentacion.FactoriaPresentacion.FactoriaPresentacionImp;

public class VistaVentasIMP  extends VistaVentas implements Observer {
	private JTabbedPane pane;
	
	private VistaCrearVenta vcv;
	private VistaLeerVenta vlv;
	private VistaLeerTodosVenta vltv;
	private VistaAniadirLineaVenta valv;
	private VistaCerrarVenta vcerrv;
	private VistaEliminarVenta vev;

	public VistaVentasIMP() {
		this.setLayout(new BorderLayout());
		
		pane = new JTabbedPane();
		
		vcv = FactoriaPresentacion.getInstancia().generarVistaCrearVenta();
		vlv =  FactoriaPresentacion.getInstancia().generarVistaleerVenta();
		vltv = FactoriaPresentacion.getInstancia().generarVistaleerTodosVenta();
		valv = FactoriaPresentacion.getInstancia().generarVistaAniadirLineaVenta();
		vcerrv = FactoriaPresentacion.getInstancia().generarVistaCerrarVenta();
		vev = FactoriaPresentacion.getInstancia().generarVistaeliEliminarVenta();

		pane.add("Crear",vcv);
		pane.add("Leer",vlv);
		pane.add("Leer Todos",vltv);
		pane.add("Aniadir linea",valv);
		pane.add("Cerrar",vcerrv);
		pane.add("Eliminar",vev);
		
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
		if (c.getEvento() == CommList.ANIADIR_LINEA_VENTA_KO || c.getEvento() == CommList.ANIADIR_LINEA_VENTA_OK) {
			valv.update(c);
		}
		else if (c.getEvento() == CommList.CERRAR_VENTA_OK || c.getEvento() == CommList.CERRAR_VENTA_KO) {
			vcerrv.update(c);
		}
		else if (c.getEvento() == CommList.CREAR_VENTA_OK || c.getEvento() == CommList.CREAR_VENTA_KO) {
			vcv.update(c);
		}
		else if (c.getEvento() == CommList.ELIMINAR_VENTA_OK || c.getEvento() == CommList.ELIMINAR_VENTA_KO) {
			vev.update(c);
		}
		else if (c.getEvento() == CommList.LEER_TODOS_VENTA_OK || c.getEvento() == CommList.LEER_TODOS_VENTA_KO) {
			vltv.update(c);
		}
		else if (c.getEvento() == CommList.LEER_VENTA_OK || c.getEvento() == CommList.LEER_VENTA_KO) {
			vlv.update(c);
		}

	}
}