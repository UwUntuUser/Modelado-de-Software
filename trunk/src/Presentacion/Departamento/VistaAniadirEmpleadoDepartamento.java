package Presentacion.Departamento;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaAniadirEmpleadoDepartamento extends JPanel implements Observer 
{
	private JTextField IdDep;
	private JTextField IdEmp;
	
	private JButton addEmpButton;
	
	public VistaAniadirEmpleadoDepartamento()
	{
		super();
		this.initGUI();
	}
	
	public void initGUI()
	{
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel panelIdDep = new JPanel();
		panelIdDep.setLayout(new GridLayout(1, 2));
		panelIdDep.add(new JLabel("ID departamento: "));
		this.IdDep = new JTextField("");
		this.IdDep.setPreferredSize(new Dimension(100, 20));
		panelIdDep.add(this.IdDep);
		panelIdDep.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		this.IdDep.addKeyListener(new KeyAdapter() 
		{
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		
		JPanel panelIdEmp = new JPanel();
		panelIdEmp.setLayout(new GridLayout(1, 2));
		panelIdEmp.add(new JLabel("ID empleado: "));
		this.IdEmp = new JTextField("");
		this.IdEmp.setPreferredSize(new Dimension(100, 25));
		panelIdEmp.add(this.IdEmp);
		panelIdEmp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		this.IdEmp.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		
	
		panel.add(panelIdDep);
		panel.add(panelIdEmp);
		
		this.add(panel, BorderLayout.CENTER);
		
		this.addEmpButton = new JButton("Añadir empleado");
		this.addEmpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					TEmpleado emp = new TEmpleado();
					emp.setId(Integer.parseInt(IdEmp.getText()));
					emp.setIdDep(Integer.parseInt(IdDep.getText()));
					Contexto ctx = new Contexto(CommList.ANIADIR_EMPLEADO_DEPARTAMENTO, emp);
					ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
				}
				 catch (NumberFormatException exc) {
					JOptionPane.showMessageDialog(VistaAniadirEmpleadoDepartamento.this, "Formato incorrecto",
					"Añadir empleado a departamento", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		this.add(this.addEmpButton, BorderLayout.PAGE_END);
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ANIADIR_EMPLEADO_DEPARTAMENTO_KO) {
			JOptionPane.showMessageDialog(VistaAniadirEmpleadoDepartamento.this,
					"Error al añadir el empleado", "Añadir empleado a departamento",
					JOptionPane.ERROR_MESSAGE);
		} 
		else if (c.getEvento() == CommList.ANIADIR_EMPLEADO_DEPARTAMENTO_OK) {
			JOptionPane.showMessageDialog(VistaAniadirEmpleadoDepartamento.this,
				"El empleado se ha añadido", "Añadir empleado a departamento",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}	
}
