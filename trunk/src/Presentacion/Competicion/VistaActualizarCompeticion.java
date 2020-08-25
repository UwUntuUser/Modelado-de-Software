package Presentacion.Competicion;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Negocio.Competicion.TCompeticion;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;


public class VistaActualizarCompeticion extends JPanel implements Observer {

	private JTextField ID;
	
	private JButton updateButton;
	
	private JTextField  recompensa;
	private JTextField  nombre;
	
	public VistaActualizarCompeticion(){
		super();
		this.initGUI();
	}
	public void initGUI(){
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel panelID = new JPanel();
		panelID.setLayout(new GridLayout(1, 3));
		panelID.add(new JLabel("ID competicion: "));
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
		
		JPanel panelType = new JPanel();
		panelType.setLayout(new GridLayout(1, 3));
		panelType.add(new JLabel("Recompensa: "));
		this.recompensa = new JTextField("");
		this.recompensa.setPreferredSize(new Dimension(75, 25));

		this.recompensa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		
		this.recompensa.setVisible(true);
		this.recompensa.setPreferredSize(new Dimension(100, 25));
		
		panelType.add(this.recompensa);
		panelType.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
	
		panel.add(panelID);
		panel.add(panelNombre);
		panel.add(panelType);
		
		this.add(panel, BorderLayout.CENTER);
		
		this.updateButton = new JButton("Actualizar Proyeccion");
		this.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nombre.getText().isEmpty() || !recompensa.getText().isEmpty()) {
					TCompeticion comp = new TCompeticion(Integer.parseInt(ID.getText()), nombre.getText());
						Contexto ctx_actualizar = new Contexto();
						ctx_actualizar.setEvento(CommList.ACTUALIZAR_COMPETICION);
						ctx_actualizar.setDato(comp);
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx_actualizar);
					}
				else{
					JOptionPane.showMessageDialog(VistaActualizarCompeticion.this, "Rellene todos los campos",
							"Actualizar Competicion", JOptionPane.ERROR_MESSAGE);
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
		if(c.getEvento() == CommList.ACTUALIZAR_COMPETICION_KO){
			JOptionPane.showMessageDialog(VistaActualizarCompeticion.this, "Error", "Actualizar Competicion",
					JOptionPane.ERROR_MESSAGE);
		}
		else if(c.getEvento() == CommList.ACTUALIZAR_COMPETICION_OK){
			JOptionPane.showMessageDialog(VistaActualizarCompeticion.this, "El ID ha sido actualizado", "Actualizar Competicion",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
