package Presentacion.Proyeccion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Negocio.Proyeccion.TProyeccion;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaAniadirEmpleadoDepartamento;


public class VistaLeerProyeccion extends JPanel implements Observer
{
	private JLabel labelID;
	private JTextField textID;
	private JButton readButton;
	private JTextArea information;
	
	
	public VistaLeerProyeccion() {
		super();
		this.initGUI();
	}
		private void initGUI() {
			this.setLayout(new BorderLayout());
			JPanel panel = new JPanel();
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
			
			this.readButton = new JButton("Leer");
			this.readButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (!textID.getText().isEmpty()) {
							Contexto ctx = new Contexto(CommList.LEER_PROYECCION, Integer.parseInt(textID.getText()));
							ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						} else {
							JOptionPane.showMessageDialog(VistaLeerProyeccion.this, "Por favor, rellene el campo ID", "Leer proyeccion",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch ( Exception e2) {
						JOptionPane.showMessageDialog(VistaLeerProyeccion.this, "Entrada incorrecta", "Leer proyeccion",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			this.information = new JTextArea(5, 40);
			this.information.setEditable(false);
			this.information.setLineWrap(true);
			this.information.setBackground(Color.decode("#EEEEEE"));
			this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
					"Information", TitledBorder.CENTER, TitledBorder.TOP));

			panel.add(labelID);
			panel.add(textID);
			panel.add(readButton);
			this.add(panel, BorderLayout.NORTH);
			this.add(information, BorderLayout.CENTER);
		
		
		}
		@Override
		public void update(Contexto c) {
			if (c.getEvento() == CommList.LEER_PROYECCION_OK) {
				TProyeccion proyeccion = (TProyeccion) c.getDato();
				information.setText(proyeccion.toString());
			}
			else if (c.getEvento() == CommList.LEER_PROYECCION_KO) {
				JOptionPane.showMessageDialog(VistaLeerProyeccion.this,
						"Error al leer la proyeccion", "Leer Proyeccion",
						JOptionPane.ERROR_MESSAGE);
			}
		}
}
