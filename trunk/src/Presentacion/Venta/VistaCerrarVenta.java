package Presentacion.Venta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Venta.TLinea_Venta;
import Negocio.Venta.TVenta;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaCerrarVenta extends JPanel implements Observer{
	private JLabel labelID;
	private JTextField textID;
	private JButton closeButton;
	
	public VistaCerrarVenta(){
		super();
		this.initGUI();
	}
	
	private void initGUI() {
		this.labelID = new JLabel("ID :");
		this.textID = new JTextField(10);
		// just enter number
		this.textID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});

		this.closeButton = new JButton("Cerrar Venta");
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textID.getText().isEmpty()) {
						Contexto ctx =  new Contexto();
						ctx.setEvento(CommList.CERRAR_VENTA);
						ctx.setDato(Integer.parseInt(textID.getText()));
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
							
						
					} else {
						JOptionPane.showMessageDialog(VistaCerrarVenta.this, "Por favor, rellene el campo ID", "Cerrar Venta",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaCerrarVenta.this, "Numero demasiado largo", "Cerrar Venta",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.add(labelID);
		this.add(textID);
		this.add(closeButton);
		
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.CERRAR_VENTA_OK) {
			JOptionPane.showMessageDialog(VistaCerrarVenta.this, "La venta se ha cerrado, precio de venta: " + c.getDato(), "Cerrar Venta",
				JOptionPane.INFORMATION_MESSAGE);
		}
		else if (c.getEvento() == CommList.CERRAR_VENTA_KO) {
			JOptionPane.showMessageDialog(VistaCerrarVenta.this, "Error al cerrar la venta", "Cerrar Venta",
					JOptionPane.ERROR_MESSAGE);
		}		
	}
}
