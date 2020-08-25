package Presentacion.Competicion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Negocio.Competicion.TCompeticion;
import Negocio.Empleado.TEmpleado;
import Negocio.Empleado.TEmpleadoCompeticionId;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaEliminarEmpleadoCompeticion extends JPanel implements Observer{
	private JTextField id_Competicion;
	private JTextField id_Empleado;

	private JButton addButton;

	public VistaEliminarEmpleadoCompeticion() {
		super();
		this.initGUI();
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());
		LayoutManager layout = new GridLayout(1, 2);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		JPanel panelCompeticionID = new JPanel();
		panelCompeticionID.setLayout(layout);
		panelCompeticionID.add(new JLabel("ID Competicion: "));
		this.id_Competicion = new JTextField(10);
		this.id_Competicion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelCompeticionID.add(id_Competicion);
		panelCompeticionID.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

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
		panel.add(panelCompeticionID);
		panel.add(panelEmpleado);

		this.add(panel, BorderLayout.CENTER);

		this.addButton = new JButton("Eliminar Empleado competicion");
		this.addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					TEmpleadoCompeticionId tec = new TEmpleadoCompeticionId(Integer.parseInt(id_Empleado.getText()), Integer.parseInt(id_Competicion.getText()));
					Contexto ctxContexto = new Contexto(CommList.ELIMINAR_EMPLEADO_COMPETICION, tec);
					ControladorDeAplicacion.getInstancia().manejarPeticion(ctxContexto);
				}
				 catch (NumberFormatException exc) {
					JOptionPane.showMessageDialog(VistaEliminarEmpleadoCompeticion.this, "Formato incorrecto",
							"Eliminar Empleado competicion", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		this.add(this.addButton, BorderLayout.PAGE_END);
}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_COMPETICION_KO) {
			JOptionPane.showMessageDialog(VistaEliminarEmpleadoCompeticion.this,
					"Error al eliminar el empleado", "Eliminar Empleado competicion",
					JOptionPane.ERROR_MESSAGE);
		} else if(c.getEvento() == CommList.ELIMINAR_EMPLEADO_COMPETICION_OK) {
			JOptionPane.showMessageDialog(VistaEliminarEmpleadoCompeticion.this,
					"El empleado se ha eliminado", "Eliminar Empleado competicion",
					JOptionPane.INFORMATION_MESSAGE);
		}		
	}
}
