package Presentacion.Competicion;

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

import Negocio.Competicion.TCompeticion;
import Negocio.Departamento.TDepartamento;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaLeerDepartamento;

public class VistaLeerCompeticion extends JPanel implements Observer{

	private JLabel labelID;
	private JTextField textID;
	private JButton readButton;
	private JTextArea information;
	
	public VistaLeerCompeticion() {
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
						Contexto ctx = new Contexto();
						ctx.setEvento(CommList.LEER_COMPETICION);
						ctx.setDato(Integer.parseInt(textID.getText()));
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						
					} else {
						JOptionPane.showMessageDialog(VistaLeerCompeticion .this, "Por favor, rellene el campo ID", "Leer competicion",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaLeerCompeticion .this, "Numero demasiado largo", "Leer competicion",
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
		if (c.getEvento() == CommList.LEER_COMPETICION_OK) {
			information.setText(((TCompeticion)c.getDato()).toString());
		}
		else if (c.getEvento() == CommList.LEER_COMPETICION_KO) {
			JOptionPane.showMessageDialog(VistaLeerCompeticion.this, "Ha ocurrido algun problema al leer la competicion", "Leer competicion",
					JOptionPane.ERROR_MESSAGE);
		}	
	}
}
