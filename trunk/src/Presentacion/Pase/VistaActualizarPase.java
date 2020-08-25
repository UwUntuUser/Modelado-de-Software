package Presentacion.Pase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import Presentacion.Departamento.VistaActualizarDepartamento;
import Presentacion.Departamento.VistaCrearDepartamento;
import Presentacion.Empleado.VistaActualizarEmpleado;


public class VistaActualizarPase extends JPanel implements Observer{
	
	private JTextField ID;
	private JTextField IDProyeccion;
	private JButton botonActualizar;
	private int h;
	private JTextArea information;

	private JComboBox<String> horaComboBox;
	
	public VistaActualizarPase(){
		super();
		this.initGUI();
	}
	public void initGUI(){
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel extrapanel = new JPanel();
        extrapanel.setLayout(new BorderLayout());

		IDProyeccion = new JTextField();
		ID = new JTextField();
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
		
		
		panel.add(new JLabel("ID del pase a modificar: "));
		panel.add(ID);
		panel.add(new JLabel("Nuevo IDProyeccion: "));
		panel.add(IDProyeccion);
		panel.add(new JLabel("Nueva hora: "));
		panel.add(horaComboBox);
		botonActualizar = new JButton("Actualizar pase");
		botonActualizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TPase tpase = new TPase(Integer.parseInt(ID.getText()), Integer.parseInt(IDProyeccion.getText()), h, true);
				Contexto context = new Contexto(CommList.ACTUALIZAR_PASE,tpase);
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
		this.add(information, BorderLayout.CENTER);
		this.add(botonActualizar, BorderLayout.SOUTH);
		
	}
	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ACTUALIZAR_PASE_OK) {
			JOptionPane.showMessageDialog(VistaActualizarPase.this, "El ID ha sido actualizado", "Actualizar pase",
			JOptionPane.INFORMATION_MESSAGE);
		}
		if (c.getEvento() == CommList.ACTUALIZAR_PASE_KO) {
			JOptionPane.showMessageDialog(VistaActualizarPase.this, "El pase no se ha actualizado correctamente", "Actualizar pase",
				JOptionPane.ERROR_MESSAGE);
		}	
	}
	
}
