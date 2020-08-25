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

import Negocio.Venta.TVenta;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaEliminarDepartamento;

public class VistaEliminarVenta extends JPanel implements Observer{
	private JLabel labelID;
	private JTextField textID;
	private JButton deleteButton;
	
	public VistaEliminarVenta(){
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

		this.deleteButton = new JButton("Eliminar");
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textID.getText().isEmpty()) {
						Contexto ctx = new Contexto();
						ctx.setEvento(CommList.ELIMINAR_VENTA);
						ctx.setDato(Integer.parseInt(textID.getText()));
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
					} else {
						JOptionPane.showMessageDialog(VistaEliminarVenta.this, "Por favor, rellene el campo ID", "Borrar Venta",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaEliminarVenta.this, "Numero demasiado largo", "Borrar Venta",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.add(labelID);
		this.add(textID);
		this.add(deleteButton);
		
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ELIMINAR_VENTA_KO){
			JOptionPane.showMessageDialog(VistaEliminarVenta.this, "El ID no existe, o ya esta eliminado", "Eliminar venta",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getEvento() == CommList.ELIMINAR_VENTA_OK){
			JOptionPane.showMessageDialog(VistaEliminarVenta.this, "El ID ha sido eliminado", "Eliminar venta",
					JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
