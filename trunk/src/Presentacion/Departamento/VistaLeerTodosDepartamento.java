package Presentacion.Departamento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import Negocio.Departamento.TDepartamento;
import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;

public class VistaLeerTodosDepartamento extends JPanel implements Observer{
	private JTextArea information;
	private JButton readAllButton;

	
	public VistaLeerTodosDepartamento() {
		super();
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		this.readAllButton = new JButton("Leer todos");
		this.readAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contexto ctx1 = new Contexto(CommList.LEER_TODOS_DEPARTAMENTO, null);
				ControladorDeAplicacion.getInstancia().manejarPeticion(ctx1);
			}
		});

		this.information = new JTextArea(20, 40);
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));

		JScrollPane sP = new JScrollPane(this.information);
		sP.setPreferredSize(new Dimension(400, 400));
		sP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel.add(readAllButton);
		
		this.add(panel, BorderLayout.NORTH);
		this.add(sP, BorderLayout.CENTER);

		
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.LEER_TODOS_DEPARTAMENTO_OK) {
			ArrayList<TDepartamento> lista= (ArrayList<TDepartamento>) c.getDato();
			information.setText("");
			for(TDepartamento d : lista){
				information.append(d.toString() + "----------" + System.lineSeparator());
			}
		}
		else if (c.getEvento() == CommList.LEER_TODOS_DEPARTAMENTO_KO) {
			JOptionPane.showMessageDialog(VistaLeerTodosDepartamento.this, "Ha ocurrido un error al leer todos los departamentos", "Leer todos los departamentos",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
