package Presentacion.Empleado;

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

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoFijo;
import Negocio.Empleado.TEmpleadoParcial;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaLeerDepartamento;

public class VistaLeerEmpleado extends JPanel implements Observer{

	private JLabel labelID;
	private JTextField textID;
	private JButton readButton;
	private JTextArea information;
	
	public VistaLeerEmpleado() {
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
						Contexto ctx = new Contexto(CommList.LEER_EMPLEADO, textID.getText());
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						
					} else {
						JOptionPane.showMessageDialog(VistaLeerEmpleado .this, "Por favor, rellene el campo ID", "Leer empleado",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaLeerEmpleado .this, "Numero demasiado largo", "Leer empleado",
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
		if (c.getEvento() == CommList.LEER_EMPLEADO_OK) {
			TEmpleado t = (TEmpleado) c.getDato();
			information.setText(t.toString());
		}
		else if (c.getEvento() == CommList.LEER_EMPLEADO_KO) {
			JOptionPane.showMessageDialog(VistaLeerEmpleado.this, "Ha ocurrido algun problema al leer el empleado", "Leer empleado",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
