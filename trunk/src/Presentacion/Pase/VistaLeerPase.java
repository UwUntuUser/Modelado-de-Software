package Presentacion.Pase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
import Negocio.Pase.TPase;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaLeerDepartamento;

public class VistaLeerPase extends JPanel implements Observer{
	
	private JLabel labelID;
	private JTextField textID;
	private JButton readButton;
	private JTextArea information;
	
	public VistaLeerPase() {
		super();
		this.initGUI();
	}
	
	private void initGUI() {
		
		this.setLayout(new BorderLayout());
		
		JPanel extrapanel = new JPanel();
        extrapanel.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 0, 10));
		
		this.labelID = new JLabel("ID :");
		this.textID = new JTextField(10);
		
		panel.add(labelID);
		panel.add(textID);

		this.readButton = new JButton("Leer");
		this.readButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textID.getText().isEmpty()) {
						Contexto ctx = new Contexto(CommList.LEER_PASE,Integer.parseInt(textID.getText()));
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						
					} else {
						JOptionPane.showMessageDialog(VistaLeerPase .this, "Por favor, rellene el campo ID", "Leer pase",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaLeerPase .this, "Numero demasiado largo", "Leer pase",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		panel.add(readButton);
		this.add(panel, BorderLayout.NORTH);
		
		this.information = new JTextArea(5, 40);
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));
		
		extrapanel.add(information);
		this.add(panel, BorderLayout.NORTH);
		this.add(extrapanel);
		this.add(readButton, BorderLayout.SOUTH);
		
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.LEER_PASE_OK) {
			information.setText(((TPase)c.getDato()).toString());
		}
		else if (c.getEvento() == CommList.LEER_PASE_KO) {
			JOptionPane.showMessageDialog(VistaLeerPase.this, "Ha ocurrido algun problema al leer el pase", "Leer Pase",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
