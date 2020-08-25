package Presentacion.Proyeccion;

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

import Negocio.Proyeccion.TDocumental;
import Negocio.Proyeccion.TPelicula;
import Negocio.Proyeccion.TProyeccion;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaAniadirEmpleadoDepartamento;

public class VistaCrearProyeccion extends JPanel implements Observer{
	
	private JButton crear;
	private JTextArea information;
	private JComboBox<String> proyeccion;
	private JLabel TypeLabel = new JLabel("Sinopsis: ");
	private JTextField Nombre, genero,  duracion, extra;
	private boolean is_Pelicula;
	
	public VistaCrearProyeccion() {
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
		this.Nombre = new JTextField("");
		this.Nombre.setPreferredSize(new Dimension(100, 25));
		panelNombre.add(this.Nombre);
		panelNombre.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JPanel panelgenero = new JPanel();
		panelgenero.setLayout(new GridLayout(1, 2));
		panelgenero.add(new JLabel("Genero: "));
		this.genero = new JTextField("");
		this.genero.setPreferredSize(new Dimension(100, 25));
		panelgenero.add(this.genero);
		panelgenero.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		JPanel panelduracion = new JPanel();
		panelduracion.setLayout(new GridLayout(1, 2));
		panelduracion.add(new JLabel("Duracion: "));
		this.duracion = new JTextField("");
		this.duracion.setPreferredSize(new Dimension(100, 25));
		panelduracion.add(this.duracion);
		panelduracion.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
		this.duracion.addKeyListener(new KeyAdapter() {
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
		this.proyeccion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (proyeccion.getSelectedIndex() == 0) {
					is_Pelicula = true;
					TypeLabel.setText("Sinopsis: ");
					extra.setText("");
				} else if (proyeccion.getSelectedIndex() == 1) {
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
		
		crear = new JButton("Crear proyeccion");
		crear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Contexto ctx = new Contexto();
				if(!hasEmpatyTextField() && Integer.parseInt(duracion.getText()) > 0){
					if(proyeccion.getSelectedIndex() == 0){
						TPelicula SD = new TPelicula(extra.getText(), Nombre.getText(), genero.getText(), Integer.parseInt(duracion.getText()), true);
						SD.setEs_Documental(false);
						ctx.setDato(SD);
					}
					else{
						TDocumental	DS= new TDocumental(extra.getText(), Nombre.getText(), genero.getText(), Integer.parseInt(duracion.getText()), true);
						DS.setEs_Documental(true);
						ctx.setDato(DS);

					}
				ctx.setEvento(CommList.CREAR_PROYECCION);
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Datos incorrectos", "Crear Proyeccion",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		this.information = new JTextArea(5, 40);
		this.information.setMinimumSize(new Dimension (350, 350));
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
					"Information", TitledBorder.CENTER, TitledBorder.TOP));
	
		
		
		panel.add(panelNombre);
		panel.add(panelgenero);
		panel.add(panelduracion);
		panel.add(panelType);
		panel.add(extraPanel);
		extrapanel.add(information);
		this.add(panel, BorderLayout.NORTH);
		this.add(extrapanel, BorderLayout.CENTER);
		this.add(crear, BorderLayout.SOUTH);
		
	}
	
	private boolean hasEmpatyTextField() {
		return this.Nombre.getText().isEmpty() || this.genero.getText().isEmpty() || this.duracion.getText().isEmpty()
				|| this.extra.getText().isEmpty();
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.CREAR_PROYECCION_OK) {
			information.setText(Integer.toString((int)c.getDato()));
			JOptionPane.showMessageDialog(VistaCrearProyeccion.this,
					"La proyeccion se ha creado correctamente", "Crear proyeccion",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else if (c.getEvento() == CommList.CREAR_PROYECCION_KO) {
			JOptionPane.showMessageDialog(VistaCrearProyeccion.this,
					"La proyeccion no se ha podido crear", "Crear proyeccion",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}

