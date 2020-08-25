package Presentacion.Departamento;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
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

import Negocio.Empleado.TEmpleado;
import Negocio.Departamento.TDepartamento;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Competicion.VistaEliminarEmpleadoCompeticion;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaEliminarEmpleadoDepartamento extends JPanel implements Observer
{
	private JTextField id_Departamento;
	private JTextField id_Empleado;

	private JButton deleteButton;

	public VistaEliminarEmpleadoDepartamento() 
	{
		super();
		this.initGUI();
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());
		LayoutManager layout = new GridLayout(1, 2);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		JPanel panelDepartamentoID = new JPanel();
		panelDepartamentoID.setLayout(layout);
		panelDepartamentoID.add(new JLabel("ID Departamento: "));
		this.id_Departamento = new JTextField(10);
		this.id_Departamento.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelDepartamentoID.add(id_Departamento);
		panelDepartamentoID.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel panelEmpleado = new JPanel();
		panelEmpleado.setLayout(layout);
		panelEmpleado.add(new JLabel("ID Empleado: "));
		this.id_Empleado = new JTextField(10);
		this.id_Empleado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelEmpleado.add(id_Empleado);
		panelEmpleado.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		panel.add(panelDepartamentoID);
		panel.add(panelEmpleado);

		this.add(panel, BorderLayout.CENTER);

		this.deleteButton = new JButton("Eliminar Empleado Departamento");
		this.deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					TEmpleado emp = new TEmpleado();
					emp.setId(Integer.parseInt(id_Empleado.getText()));
					emp.setIdDep(Integer.parseInt(id_Departamento.getText()));
					Contexto ctx = new Contexto(CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO, emp);
					ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
					
				} catch (NumberFormatException exc) {
					JOptionPane.showMessageDialog(VistaEliminarEmpleadoDepartamento.this, "Formato incorrecto",
							"Eliminar empleado de departamento", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.add(this.deleteButton, BorderLayout.PAGE_END);
}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO_KO) {
			JOptionPane.showMessageDialog(VistaEliminarEmpleadoDepartamento.this,
					"Error al eliminar al empleado", "Añadir empleado a departamento",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_DEPARTAMENTO_OK) {
			JOptionPane.showMessageDialog(VistaEliminarEmpleadoDepartamento.this,
					"El empleado se ha eliminado", "Eliminar empleado de departamento",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
