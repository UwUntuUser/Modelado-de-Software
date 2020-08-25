package Presentacion.Proyeccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaEliminarProyeccion extends JPanel implements Observer{
	private JLabel labelID;
	private JTextField textID;
	private JButton deleteButton;
	
	public VistaEliminarProyeccion() {
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
						Contexto ctx = new Contexto(CommList.ELIMINAR_PROYECCION, Integer.parseInt(textID.getText()));
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
					} else {
						JOptionPane.showMessageDialog(VistaEliminarProyeccion.this, "Por favor, rellene el campo ID", "Borrar Proyeccion",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaEliminarProyeccion.this, "Numero demasiado largo", "Borrar Proyeccion",
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
		if (c.getEvento() == CommList.ELIMINAR_COMPETICION_KO){
			JOptionPane.showMessageDialog(VistaEliminarProyeccion.this, "El ID no existe, ya esta eliminado o contiene pases activos", "Borrar Proyeccion",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (c.getEvento() == CommList.ELIMINAR_PROYECCION_OK){
			JOptionPane.showMessageDialog(VistaEliminarProyeccion.this, "El ID ha sido eliminado", "Borrar Proyeccion",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
