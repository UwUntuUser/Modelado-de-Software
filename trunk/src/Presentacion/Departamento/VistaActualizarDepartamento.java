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
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaActualizarDepartamento extends JPanel implements Observer
{
	private JTextField ID;
	private JTextField  nombre;
	
	private JButton updateButton;
	
	
	public VistaActualizarDepartamento()
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
		panelID.add(new JLabel("ID departamento: "));
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
		
		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new GridLayout(1, 2));
		panelNombre.add(new JLabel("Nombre: "));
		this.nombre = new JTextField("");
		this.nombre.setPreferredSize(new Dimension(100, 25));
		panelNombre.add(this.nombre);
		panelNombre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	
		panel.add(panelID);
		panel.add(panelNombre);
		
		this.add(panel, BorderLayout.CENTER);
		
		this.updateButton = new JButton("Actualizar Departamento");
		this.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nombre.getText().isEmpty()) {
						TDepartamento dep = new TDepartamento(Integer.parseInt(ID.getText()), nombre.getText());
						Contexto ctx_actualizar = new Contexto(CommList.ACTUALIZAR_DEPARTAMENTO, dep);
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx_actualizar);
					}
				else{
					JOptionPane.showMessageDialog(VistaActualizarDepartamento.this, "Rellene todos los campos",
							"Actualizar departamento", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		
		JPanel panelEnd = new JPanel();
		panelEnd.setLayout(new GridLayout(1, 2));
		panelEnd.add(this.updateButton);
		this.add(panelEnd, BorderLayout.PAGE_END);

	
	}
	
	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ACTUALIZAR_DEPARTAMENTO_OK) {
			JOptionPane.showMessageDialog(VistaActualizarDepartamento.this, "El departamento se ha actualizado correctamente", "Actualizar departamento",
				JOptionPane.INFORMATION_MESSAGE);
		}
		else if (c.getEvento() == CommList.ACTUALIZAR_DEPARTAMENTO_KO) {
			JOptionPane.showMessageDialog(VistaActualizarDepartamento.this, "Ha ocurrido algun problema al actualizar el departamento", "Actualizar Departamento",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
