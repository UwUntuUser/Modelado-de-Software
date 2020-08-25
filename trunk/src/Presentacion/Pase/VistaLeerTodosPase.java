package Presentacion.Pase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import Negocio.Departamento.TDepartamento;
import Negocio.Empleado.TEmpleado;
import Negocio.Pase.TPase;
import Negocio.Proyeccion.TPelicula;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Empleado.VistaLeerTodosEmpleado;

public class VistaLeerTodosPase extends JPanel implements Observer{
	
	private JTextArea information;
	private JButton readAllButton;
	private JButton readAllButtonbyHour;
	private JButton queryPorHoraButton;
	private JButton queryPorGeneroButton;
	private JLabel labelQuerys;
	private JTextField querydata;
	public VistaLeerTodosPase() {
		super();
		this.initGUI();
	}

	private void initGUI() {
		
		this.setLayout(new BorderLayout());
		
		JPanel extrapanel = new JPanel();
        extrapanel.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 0, 10));
		
		this.readAllButton = new JButton("Leer todos");
		this.readAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto ctx1 = new Contexto();
				ctx1.setEvento(CommList.LEER_TODOS_PASE);
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx1);
			}
		});
		
		this.readAllButtonbyHour = new JButton("Leer ordenados por hora");
		this.readAllButtonbyHour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto ctx2 = new Contexto();
				ctx2.setEvento(CommList.LEER_TODOS_PASE_HORA);
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx2);
			}
		});
		
		this.queryPorGeneroButton = new JButton("Query leer peliculas ordenadas por genero");
		this.queryPorGeneroButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto ctx3 = new Contexto();
				ctx3.setEvento(CommList.QUERY_LEER_PELIS_POR_GENERO);
				ctx3.setDato(querydata.getText());
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx3);
			}
		});
		
		this.queryPorHoraButton = new JButton("Query leer peliculas ordenadas por duracion");
		this.queryPorHoraButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto ctx4 = new Contexto();
				ctx4.setEvento(CommList.QUERY_LEER_PELIS_POR_DURACION);
				ctx4.setDato(querydata.getText());
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx4);
			}
		});
		this.querydata = new JTextField();
		this.labelQuerys = new JLabel("Introduce aqui los datos de las query :");

		panel.add(readAllButton);
		panel.add(readAllButtonbyHour);
		panel.add(queryPorGeneroButton);
		panel.add(queryPorHoraButton);
		panel.add(labelQuerys);
		panel.add(querydata);
		
		this.information = new JTextArea(20, 40);
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));

		JScrollPane sP = new JScrollPane(this.information);
		sP.setPreferredSize(new Dimension(400, 400));
		sP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		
		extrapanel.add(information);
		this.add(panel, BorderLayout.NORTH);
		this.add(sP, BorderLayout.CENTER);
		this.add(extrapanel);


		
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.LEER_TODOS_PASE_OK) {
			ArrayList<TPase> lista= (ArrayList<TPase>) c.getDato();
			information.setText("");
			for(TPase d : lista){
				information.append(d.toString() + "----------" + System.lineSeparator());
			}
		}
		else if (c.getEvento() == CommList.LEER_TODOS_PASE_KO) {
			JOptionPane.showMessageDialog(VistaLeerTodosPase.this, "Ha ocurrido un error al leer todos los pases", "Leer todos los pases",
					JOptionPane.ERROR_MESSAGE);
		}	
		else if (c.getEvento() == CommList.LEER_TODOS_PASE_HORA_OK) {
            ArrayList<TPase> lista= (ArrayList<TPase>) c.getDato();
            information.setText("");
            for(TPase p : lista){
                information.append(p.toString() + "----------" + System.lineSeparator());
            }
        }
        else if (c.getEvento() == CommList.LEER_TODOS_PASE_HORA_KO) {
            JOptionPane.showMessageDialog(VistaLeerTodosPase.this, "Ha ocurrido un error al leer todos los pases por hora", "Leer todos los pases por hora",
                    JOptionPane.ERROR_MESSAGE);
        }
		else if (c.getEvento() == CommList.QUERY_LEER_PELIS_POR_GENERO_OK) {
            ArrayList<TPelicula> lista= (ArrayList<TPelicula>) c.getDato();
            information.setText("");
            for(TPelicula p : lista){
                information.append(p.toString() + "----------" + System.lineSeparator());
            }
        }
        else if (c.getEvento() == CommList.QUERY_LEER_PELIS_POR_GENERO_KO) {
            JOptionPane.showMessageDialog(VistaLeerTodosPase.this, "Ha ocurrido un error en la query de leer todas las peliculas por genero", "Query leer todas las pelis por genero",
                    JOptionPane.ERROR_MESSAGE);
        }
		else if (c.getEvento() == CommList.QUERY_LEER_PELIS_POR_DURACION_OK) {
            ArrayList<TPelicula> lista= (ArrayList<TPelicula>) c.getDato();
            information.setText("");
            for(TPelicula p : lista){
                information.append(p.toString() + "----------" + System.lineSeparator());
            }
        }
        else if (c.getEvento() == CommList.QUERY_LEER_PELIS_POR_DURACION_KO) {
            JOptionPane.showMessageDialog(VistaLeerTodosPase.this, "Ha ocurrido un error en la query de leer todas las peliculas por hora", "Query leer todas las pelis por hora",
                    JOptionPane.ERROR_MESSAGE);
        }
		
	}
}
