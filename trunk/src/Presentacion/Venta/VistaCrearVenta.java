package Presentacion.Venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Negocio.Venta.TVenta;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaCrearDepartamento;

public class VistaCrearVenta extends JPanel implements Observer{
	private JButton crearVenta;
	private JTextArea information;
	
	public VistaCrearVenta(){
		super();
		this.initGUI();
	}
		
	private void initGUI() {
		 this.setLayout(new BorderLayout());
		 
		 crearVenta = new JButton("Crear venta");
		 crearVenta.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(ActionEvent e) {
				Contexto ctx = new Contexto();
				ctx.setEvento(CommList.CREAR_VENTA);
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
			}
		});
		 
		this.information = new JTextArea(5, 40);
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
					"Information", TitledBorder.CENTER, TitledBorder.TOP));

		 
		 this.add(crearVenta, BorderLayout.NORTH);
		 this.add(information, BorderLayout.CENTER);
	}

	@Override
	public void update(Contexto c) {

		if (c.getEvento() == CommList.CREAR_VENTA_OK) {
			information.setText(Integer.toString((int) c.getDato()));
		}
		else if (c.getEvento() == CommList.CREAR_VENTA_KO) {
			JOptionPane.showMessageDialog(VistaCrearVenta.this,
					"Error al crear la venta", "Crear venta",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
