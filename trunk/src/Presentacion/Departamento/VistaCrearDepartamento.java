package Presentacion.Departamento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Negocio.Departamento.TDepartamento;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaCrearDepartamento extends JPanel implements Observer
{
	private JButton crearDepartamento;
	private JTextField NombreDepartamento;
	private JTextArea info;

	public VistaCrearDepartamento() 
	{
		super();
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		crearDepartamento = new JButton("Crear Departamento");
		NombreDepartamento = new JTextField(10);
		JPanel panel = new JPanel();

		crearDepartamento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (NombreDepartamento.getText().isEmpty()) 
				{
					JOptionPane.showMessageDialog(VistaCrearDepartamento.this, "Rellene todos los campos",
							"Crear Departamento", JOptionPane.ERROR_MESSAGE);
				} 
				else 
				{
					TDepartamento dep = new TDepartamento(NombreDepartamento.getText(), true);
					Contexto ctx = new Contexto(CommList.CREAR_DEPARTAMENTO, dep);
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

		panel.add(new JLabel("Nombre departamento: "));
		panel.add(NombreDepartamento);
		panel.add(crearDepartamento);
		panel.add(info);
		this.add(panel, BorderLayout.NORTH);
		this.add(info, BorderLayout.CENTER);
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.CREAR_DEPARTAMENTO_OK) {
			info.setText(Integer.toString((int) c.getDato()));
		}
		else if (c.getEvento() == CommList.CREAR_DEPARTAMENTO_KO) {
			JOptionPane.showMessageDialog(VistaCrearDepartamento.this,
					"Error al crear el departamento", "Crear departamento",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
