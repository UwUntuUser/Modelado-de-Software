package Presentacion.Proyeccion;

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

import Negocio.Proyeccion.TDocumental;
import Negocio.Proyeccion.TPelicula;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;


public class VistaActualizarProyeccion extends JPanel implements Observer
{
	
	private JTextField ID;
	private JTextField Nombre;
	private JTextField Genero;
	private JTextField Duracion;
	private JTextField extra;
	
	private JLabel TypeLabel = new JLabel("Sinopsis: ");
	
	
	private JButton updateButton;
	private JComboBox<String> proyeccion;
	private boolean is_Pelicula;
	
	public VistaActualizarProyeccion() {
		super();
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		
		JPanel panelID = new JPanel();
		panelID.setLayout(new GridLayout(1, 3));
		panelID.add(new JLabel("ID proyeccion: "));
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
		this.Nombre = new JTextField("");
		this.Nombre.setPreferredSize(new Dimension(100, 25));
		panelNombre.add(this.Nombre);
		panelNombre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JPanel panelGenero = new JPanel();
		panelGenero.setLayout(new GridLayout(1, 2));
		panelGenero.add(new JLabel("Genero: "));
		this.Genero = new JTextField("");
		this.Genero.setPreferredSize(new Dimension(100, 25));
		panelGenero.add(this.Genero);
		panelGenero.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JPanel panelDuracion = new JPanel();
		panelDuracion.setLayout(new GridLayout(1, 3));
		panelDuracion.add(new JLabel("Duracion: "));
		this.Duracion = new JTextField("");
		this.Duracion.setPreferredSize(new Dimension(75, 25));
		panelDuracion.add(this.Duracion);
		panelDuracion.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		this.Duracion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		
		JPanel panelType = new JPanel();
		panelType.setLayout(new GridLayout(1, 2));
		panelType.add(new JLabel("Type: "));
		String[] typeStrings = {"Pelicula", "Documental"};
		this.proyeccion = new JComboBox<String>(typeStrings);
		this.proyeccion.setSelectedIndex(0);
		this.proyeccion.setVisible(true);
		this.proyeccion.setPreferredSize(new Dimension(100, 25));
		this.is_Pelicula = true;
		this.proyeccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (proyeccion.getSelectedIndex() == 0) {
					is_Pelicula = true;
					TypeLabel.setText("Sinopsis: ");
					extra.setText("");
				} else {
					is_Pelicula = false;
					TypeLabel.setText("Narrador: ");
					extra.setText("");
				}
			}
		});
		panelType.add(this.proyeccion);
		panelType.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JPanel extraPanel = new JPanel();
		extraPanel.setLayout(new GridLayout(1, 2));
		extraPanel.add(this.TypeLabel);
		this.extra = new JTextField("");
		this.extra.setPreferredSize(new Dimension(100, 25));
		extraPanel.add(this.extra);
		extraPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		panel.add(panelID);
		panel.add(panelNombre);
		panel.add(panelGenero);
		panel.add(panelDuracion);
		panel.add(panelType);
		panel.add(extraPanel);
		this.add(panel, BorderLayout.CENTER);
		
		this.updateButton = new JButton("Update Product");
		this.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Nombre.getText().length() > 44) {
					JOptionPane.showMessageDialog(VistaActualizarProyeccion.this, "Nombre demasiado largo",
							"Actualizar Proyeccion", JOptionPane.ERROR_MESSAGE);
				} else {
					TProyeccion proy;
					
					if(!hasEmpatyTextField() && Integer.parseInt(Duracion.getText()) > 0){
						if(is_Pelicula){
							proy = new TPelicula(extra.getText(), Nombre.getText(), Genero.getText(), Integer.parseInt(Duracion.getText()), true);
						}
						else{
							proy = new TDocumental(extra.getText(), Nombre.getText(), Genero.getText(), Integer.parseInt(Duracion.getText()), true);
						}
						proy.setID_Proyeccion(Integer.parseInt(ID.getText()));
						Contexto ctx = new Contexto(CommList.ACTUALIZAR_PROYECCION, proy);
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
				}
			}
			}
		});

	
		
		JPanel panelEnd = new JPanel();
		panelEnd.setLayout(new GridLayout(1, 2));
		panelEnd.add(this.updateButton);
		this.add(panelEnd, BorderLayout.PAGE_END);


	}
	
	private boolean hasEmpatyTextField() {
		return this.ID.getText().isEmpty() || this.Nombre.getText().isEmpty() || this.Duracion.getText().isEmpty() || this.Genero.getText().isEmpty() || this.extra.getText().isEmpty();
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ACTUALIZAR_PROYECCION_KO){
			JOptionPane.showMessageDialog(VistaActualizarProyeccion.this, "La proyeccion no se ha podido actualizar", "Actualizar Proyeccion",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (c.getEvento() == CommList.ACTUALIZAR_PROYECCION_OK){
			JOptionPane.showMessageDialog(VistaActualizarProyeccion.this, "La proyeccion ha sido actualizada con exito", "Actualizar Proyeccion",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
