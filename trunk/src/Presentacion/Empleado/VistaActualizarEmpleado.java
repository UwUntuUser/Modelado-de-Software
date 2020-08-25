package Presentacion.Empleado;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoFijo;
import Negocio.Empleado.TEmpleadoParcial;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaActualizarDepartamento;
import Presentacion.Departamento.VistaCrearDepartamento;

public class VistaActualizarEmpleado extends JPanel implements Observer{
	
	private JTextField ID;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField dni;
	private JTextField NumSS;
	private JComboBox<String> tipo_empleado;
	private Boolean es_parcial = false;
	private JTextField extra1;
	private JTextField extra2;
	
	private JLabel TypeLabel_1 = new JLabel("Antiguedad: ");
	private JLabel TypeLabel_2 = new JLabel("Sueldo mensual: ");
	
	private JButton updateButton;
	
	
	public VistaActualizarEmpleado()
	{
		super();
		this.initGUI();
	}
	
	public void initGUI()
	{
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel panelID = new JPanel();
		panelID.setLayout(new GridLayout(1, 3));
		panelID.add(new JLabel("ID empleado: "));
		this.ID = new JTextField("");
		this.ID.setPreferredSize(new Dimension(75, 25));
		panelID.add(this.ID);
		panelID.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		this.ID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		
		JPanel panelDatos = new JPanel();
		JPanel panelTipo = new JPanel();
		panelDatos.setLayout(new GridLayout(1, 2));
		panelDatos.add(new JLabel("Nombre: "));
		this.nombre = new JTextField("");
		this.nombre.setPreferredSize(new Dimension(100, 25));
		panelDatos.add(this.nombre);
		panelDatos.add(new JLabel("Apellido: "));
		this.apellido = new JTextField("");
		this.apellido.setPreferredSize(new Dimension(100, 25));
		panelDatos.add(this.apellido);
		panelDatos.add(new JLabel("DNI: "));
		this.dni = new JTextField("");
		this.dni.setPreferredSize(new Dimension(100, 25));
		panelDatos.add(this.dni);
		panelDatos.add(new JLabel("NumSS: "));
		this.NumSS = new JTextField("");
		this.NumSS.setPreferredSize(new Dimension(100, 25));
		panelDatos.add(this.NumSS);
		panelDatos.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	
		panelTipo.setLayout(new GridLayout(1, 2));
		panelTipo.add(new JLabel("Tipo: "));
		String[] typeStrings = {"Tiempo completo", "Tiempo parcial"};
		this.tipo_empleado = new JComboBox<String>(typeStrings);
		this.tipo_empleado.setSelectedIndex(0);
		this.tipo_empleado.setVisible(true);
		this.tipo_empleado.setPreferredSize(new Dimension(100, 25));
		this.tipo_empleado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tipo_empleado.getSelectedIndex() == 0) {
					es_parcial = false;
					TypeLabel_1.setText("Antiguedad: ");
					TypeLabel_2.setText("Sueldo mensual: ");
					extra1.setText("");
					extra2.setText("");
				}
				else {
					es_parcial = true;
					TypeLabel_1.setText("Horas jornada: ");
					TypeLabel_2.setText("Sueldo hora: ");
					extra1.setText("");
					extra2.setText("");
				}
			}
			
		});
		
		panelTipo.add(this.tipo_empleado);
		panelTipo.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JPanel extraPanel = new JPanel();
		extraPanel.setLayout(new GridLayout(1, 2));
		extraPanel.add(this.TypeLabel_1);
		this.extra1 = new JTextField("");
		extraPanel.add(this.extra1);
		extraPanel.add(this.TypeLabel_2);
		this.extra2 = new JTextField("");
		extraPanel.add(this.extra2);
		extraPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		this.extra1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		
		this.extra2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
	
		panel.add(panelID);
		panel.add(panelDatos);
		panel.add(panelTipo);
		panel.add(extraPanel);
		
		this.add(panel, BorderLayout.CENTER);
		
		this.updateButton = new JButton("Actualizar empleado");
		this.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!hasEmptyTextField()) {
					TEmpleado emp;
					if (es_parcial) {
						emp = new TEmpleadoParcial(Integer.parseInt(ID.getText()), dni.getText(), nombre.getText(), apellido.getText(), Integer.parseInt(NumSS.getText()), true, Integer.parseInt(extra1.getText()), Integer.parseInt(extra2.getText()));
						emp.setEsParcial(es_parcial);
					}
					else {
						emp = new TEmpleadoFijo(Integer.parseInt(ID.getText()), dni.getText(), nombre.getText(), apellido.getText(), Integer.parseInt(NumSS.getText()), Integer.parseInt(extra1.getText()), Integer.parseInt(extra2.getText()), true);
						emp.setEsParcial(es_parcial);
					}
					Contexto ctx_actualizar = new Contexto(CommList.ACTUALIZAR_EMPLEADO, emp);
					ControladorDeAplicacion.getInstancia().manejarPeticion(ctx_actualizar);
				}
				else{
					JOptionPane.showMessageDialog(VistaActualizarEmpleado.this, "Rellene todos los campos",
							"Actualizar empleado", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	
		
		JPanel panelEnd = new JPanel();
		panelEnd.setLayout(new GridLayout(1, 2));
		panelEnd.add(this.updateButton);
		this.add(panelEnd, BorderLayout.PAGE_END);

	}
	
	private boolean hasEmptyTextField() {
		return this.ID.getText().isEmpty() || this.nombre.getText().isEmpty() || this.apellido.getText().isEmpty()
				|| this.dni.getText().isEmpty() || this.NumSS.getText().isEmpty() || this.extra1.getText().isEmpty() || this.extra2.getText().isEmpty();
	}
	
	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ACTUALIZAR_EMPLEADO_OK) {
			JOptionPane.showMessageDialog(VistaActualizarEmpleado.this, "El empleado se ha actualizado correctamente", "Actualizar empleado",
				JOptionPane.INFORMATION_MESSAGE);
		}
		else if (c.getEvento() == CommList.ACTUALIZAR_EMPLEADO_KO) {
			JOptionPane.showMessageDialog(VistaActualizarEmpleado.this, "Ha ocurrido algun problema al actualizar el empleado", "Actualizar empleado",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
