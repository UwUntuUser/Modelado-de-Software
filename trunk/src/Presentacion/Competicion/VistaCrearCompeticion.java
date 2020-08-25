package Presentacion.Competicion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import Negocio.Competicion.TCompeticion;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaCrearDepartamento;

public class VistaCrearCompeticion extends JPanel implements Observer{
	private JButton crearCompeticion;
	private JTextField NombreCompeticion;
	private JTextArea info;

	public VistaCrearCompeticion() {
		super();
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		
		JPanel panelnombre = new JPanel();
		panelnombre.setLayout(new GridLayout(1, 3));
		panelnombre.add(new JLabel("Nombre competicion: "));
		this.NombreCompeticion = new JTextField("");
		this.NombreCompeticion.setPreferredSize(new Dimension(75, 25));
		panelnombre.add(this.NombreCompeticion);
		panelnombre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		crearCompeticion = new JButton("Crear Competicion");
		
		JPanel panel = new JPanel();

		crearCompeticion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (NombreCompeticion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(VistaCrearCompeticion.this, "Rellene todos los campos",
							"Crear Competicion", JOptionPane.ERROR_MESSAGE);
				} else {
					TCompeticion comp = new TCompeticion(NombreCompeticion.getText());
					Contexto ctx = new Contexto();
					ctx.setEvento(CommList.CREAR_COMPETICION);
					ctx.setDato(comp);
					ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
					
				}
			}
		});

		this.info = new JTextArea(5, 40);
		this.info.setEditable(false);
		this.info.setLineWrap(true);
		this.info.setBackground(Color.decode("#EEEEEE"));
		this.info.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));

	
		
		panel.add(panelnombre);
		panel.add(crearCompeticion);
		panel.add(info);
		this.add(panel, BorderLayout.NORTH);
		this.add(info, BorderLayout.CENTER);

	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.CREAR_COMPETICION_OK) {
			info.setText(Integer.toString((int) c.getDato()));
		}
		else if (c.getEvento() == CommList.CREAR_COMPETICION_KO) {
			JOptionPane.showMessageDialog(VistaCrearCompeticion.this,
					"Error al crear la competicion", "Crear competicion",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
