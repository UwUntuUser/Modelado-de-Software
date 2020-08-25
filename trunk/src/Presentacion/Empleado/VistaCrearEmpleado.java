package Presentacion.Empleado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoFijo;
import Negocio.Empleado.TEmpleadoParcial;
import Negocio.Pase.TPase;
import Negocio.Proyeccion.TDocumental;
import Negocio.Proyeccion.TPelicula;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaCrearDepartamento;
import Presentacion.Pase.VistaCrearPase;

public class VistaCrearEmpleado extends JPanel implements Observer{
	private JButton crearEmpleado;
	private JTextField nombreEmpleado;
	private JTextField apellidoEmpleado;
	private JTextField numSSEmpleado;
	private JTextField dni;
	private JTextField horas;
	private JTextField sueldo;
	private JTextField departamento;
	private JTextArea information;
	private Boolean esParcial;
	private JComboBox<String> empleado;
	private JLabel TypeLabel1 = new JLabel("Horas: ");
	private JLabel TypeLabel2 = new JLabel("Sueldo Mensual: ");

	public VistaCrearEmpleado() {
		super();
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 0, 10));

		JPanel extrapanel = new JPanel();
		extrapanel.setLayout(new BorderLayout());

		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new GridLayout(1, 2));
		panelNombre.add(new JLabel("Nombre: "));
		this.nombreEmpleado = new JTextField("");
		this.nombreEmpleado.setPreferredSize(new Dimension(100, 25));
		panelNombre.add(this.nombreEmpleado);
		panelNombre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel panelapellido = new JPanel();
		panelapellido.setLayout(new GridLayout(1, 2));
		panelapellido.add(new JLabel("Apellido: "));
		this.apellidoEmpleado = new JTextField("");
		this.apellidoEmpleado.setPreferredSize(new Dimension(100, 25));
		panelapellido.add(this.apellidoEmpleado);
		panelapellido.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel paneldni = new JPanel();
		paneldni.setLayout(new GridLayout(1, 2));
		paneldni.add(new JLabel("DNI: "));
		this.dni = new JTextField("");
		this.dni.setPreferredSize(new Dimension(100, 25));
		paneldni.add(this.dni);
		paneldni.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel panelnumSS = new JPanel();
		panelnumSS.setLayout(new GridLayout(1, 2));
		panelnumSS.add(new JLabel("Numero de la seguridad social: "));
		this.numSSEmpleado = new JTextField("");
		this.numSSEmpleado.setPreferredSize(new Dimension(100, 25));
		this.numSSEmpleado.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelnumSS.add(this.numSSEmpleado);
		panelnumSS.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel panelType = new JPanel();
		panelType.setLayout(new GridLayout(1, 2));
		panelType.add(new JLabel("Tipo empleado: "));
		String[] typeStrings = { "Tiempo Parcial", "Tiempo Completo" };
		this.empleado = new JComboBox<String>(typeStrings);
		this.empleado.setSelectedIndex(0);
		this.empleado.setVisible(true);
		this.empleado.setPreferredSize(new Dimension(100, 25));
		this.empleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (empleado.getSelectedIndex() == 0) {
					esParcial = true;
					TypeLabel1.setText("Horas: ");
					TypeLabel2.setText("Sueldo por hora: ");
				} else if (empleado.getSelectedIndex() == 1) {
					esParcial = false;
					TypeLabel1.setText("Sueldo mensual: ");
					TypeLabel2.setText("Antiguedad: ");
				}
			}
		});
		panelType.add(this.empleado);
		panelType.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel extraPanel = new JPanel();
		extraPanel.setLayout(new GridLayout(1, 2));
		this.horas = new JTextField("");
		this.horas.setPreferredSize(new Dimension(100, 25));
		this.sueldo = new JTextField("");
		this.sueldo.setPreferredSize(new Dimension(100, 25));
		extraPanel.add(this.TypeLabel1);
		extraPanel.add(this.horas);
		this.horas.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		extraPanel.add(this.TypeLabel2);
		extraPanel.add(this.sueldo);
		this.sueldo.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		extraPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		JPanel panelDepartamento = new JPanel();
		panelDepartamento.setLayout(new GridLayout(1, 2));
		this.departamento = new JTextField();
		panelDepartamento.add(new JLabel("Id Departamento: "));
		panelDepartamento.add(this.departamento);
		crearEmpleado = new JButton("Crear empleado");
		crearEmpleado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!hasEmpatyTextField()) {
					TEmpleado TP = null;
					if (empleado.getSelectedIndex() == 0) {
						esParcial = true;
					} else if (empleado.getSelectedIndex() == 1) {
						esParcial = false;
					}
					
						if (esParcial) {
							 TP = new TEmpleadoParcial(dni.getText(), nombreEmpleado.getText(),
									apellidoEmpleado.getText(), Integer.parseInt(numSSEmpleado.getText()), true,
									Double.parseDouble(horas.getText()), Double.parseDouble(sueldo.getText()), Integer.parseInt(departamento.getText()));
							TP.setEsParcial(true);
							;
						} else {
							 TP = new TEmpleadoFijo(dni.getText(), nombreEmpleado.getText(),
									apellidoEmpleado.getText(), Integer.parseInt(numSSEmpleado.getText()),
									Double.parseDouble(sueldo.getText()), Double.parseDouble(horas.getText()), true, Integer.parseInt(departamento.getText()));
							TP.setEsParcial(false);

					}
						Contexto ctx = new Contexto(CommList.CREAR_EMPLEADO, TP);
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
					
				} else {
					JOptionPane.showMessageDialog(null, "Datos incorrectos", "Crear Empleado",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.information = new JTextArea(5, 40);
		this.information.setMinimumSize(new Dimension(350, 350));
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));

		panel.add(panelNombre);
		panel.add(panelapellido);
		panel.add(paneldni);
		panel.add(panelnumSS);
		panel.add(panelType);
		panel.add(extraPanel);
		panel.add(panelDepartamento);
		extrapanel.add(information);
		this.add(panel, BorderLayout.NORTH);
		this.add(extrapanel, BorderLayout.CENTER);
		this.add(crearEmpleado, BorderLayout.SOUTH);

	}

	private boolean hasEmpatyTextField() {
		return this.nombreEmpleado.getText().isEmpty() || this.apellidoEmpleado.getText().isEmpty()
				|| this.dni.getText().isEmpty() || this.numSSEmpleado.getText().isEmpty()
				|| this.horas.getText().isEmpty() || this.sueldo.getText().isEmpty() || this.departamento.getText().isEmpty();
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.CREAR_EMPLEADO_OK) {
			information.setText(Integer.toString((int) c.getDato()));
			JOptionPane.showMessageDialog(VistaCrearEmpleado.this, "El empleado ha sido creado", "Crear empleado",
			JOptionPane.INFORMATION_MESSAGE);
		}
		if (c.getEvento() == CommList.CREAR_EMPLEADO_KO) {
			JOptionPane.showMessageDialog(VistaCrearEmpleado.this, "El empleado no se ha creado correctamente", "Crear empleado",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
