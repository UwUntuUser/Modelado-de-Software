package Presentacion.Pase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import Negocio.Pase.TPase;

import Negocio.Proyeccion.TProyeccion;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaCrearDepartamento;

public class VistaCrearPase extends JPanel implements Observer {

	private JButton crear;
	private JTextField proyeccion;
	private JComboBox<String> horaComboBox;
	private int h;
	private JTextArea information;
	public VistaCrearPase() {
		super();
		this.initGUI();
	}

	private void initGUI() {

		this.setLayout(new BorderLayout());
		
		JPanel extrapanel = new JPanel();
        extrapanel.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 0, 10));
		
		proyeccion = new JTextField(20);
		String[] hourStrings = { "15:00", "18:00", "21:00", "24:00", "03:00" };
        horaComboBox = new JComboBox<String>(hourStrings);
        horaComboBox.setSelectedIndex(0);
        horaComboBox.setVisible(true);
        horaComboBox.setPreferredSize(new Dimension(100, 25));
		h = 1500;

        horaComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (horaComboBox.getSelectedIndex()) {
				case 0:
					h = 1500;
					break;
				case 1:
					h = 1800;
					break;
				case 2:
					h = 2100;
					break;
				case 3:
					h = 2400;
					break;
				case 4:
					h = 300;
					break;
				default:
					h = 1200;
					break;
				}
			}
		});
		
		
		panel.add(new JLabel("IDProyeccion:"));
		panel.add(proyeccion);
		panel.add(new JLabel("Hora:"));
		panel.add(horaComboBox);
		crear = new JButton("Crear pase");
		crear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO si en proyeccion no metes un numero no funciona, hay que arreglarlo
				TPase pase = new TPase(Integer.parseInt(proyeccion.getText()), h);
				Contexto context = new Contexto(CommList.CREAR_PASE, pase);
				ControladorDeAplicacion.getInstancia().manejarPeticion(context);
			}
		});
		this.information = new JTextArea(5, 40);
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));

		extrapanel.add(information);
		this.add(panel, BorderLayout.NORTH);
		this.add(extrapanel);
		this.add(crear, BorderLayout.SOUTH);
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.CREAR_PASE_OK) {
			information.setText(Integer.toString((int) c.getDato()));

			JOptionPane.showMessageDialog(VistaCrearPase.this, "El pase ha sido creado", "Crear pase",
			JOptionPane.INFORMATION_MESSAGE);
		}
		if (c.getEvento() == CommList.CREAR_PASE_KO) {
			JOptionPane.showMessageDialog(VistaCrearPase.this, "El pase no se ha creado correctamente", "Crear pase",
				JOptionPane.ERROR_MESSAGE);
		}	 

		
	}

}
