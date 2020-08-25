package Presentacion;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Presentacion.Competicion.VistaCompeticion;
import Presentacion.Departamento.VistaDepartamento;
import Presentacion.Empleado.VistaEmpleado;
import Presentacion.Pase.VistaPase;
import Presentacion.Proyeccion.VistaProyeccion;
import Presentacion.Venta.VistaVentas;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTabbedPane tabbedPane;
	
	public static void main(String[] args){
		new Main();
	}
	
	private Main() {
		this.initGUI();
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void initGUI() {
		this.setTitle("E.M.E.S.E II");
		this.setPreferredSize(new Dimension(800, 650));
		this.tabbedPane = new JTabbedPane();
		tabbedPane.add("Pase", VistaPase.getInstance());
		tabbedPane.add("Proyeccion", VistaProyeccion.getInstance());
		tabbedPane.add("Venta", VistaVentas.getInstance());
		
		tabbedPane.add("Empleado", VistaEmpleado.getInstance());
		tabbedPane.add("Departamento", VistaDepartamento.getInstance());
		tabbedPane.add("Competicion", VistaCompeticion.getInstance());
		
		this.add(tabbedPane);
	
		this.setContentPane(tabbedPane);
		
		//tabbedPane.add("Ventas",vistaVentas);
	}
}
