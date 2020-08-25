package Presentacion.Pase;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaEliminarDepartamento;


public class VistaEliminarPase extends JPanel implements Observer{
	
	private JLabel labelID;
	private JTextField textID;
	private JButton deleteButton;
	private JTextArea information;
	public VistaEliminarPase(){
		super();
		this.initGUI();
	}
	
	private void initGUI() {
		
		this.setLayout(new BorderLayout());
		
		JPanel extrapanel = new JPanel();
        extrapanel.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1, 0, 10));
		
		this.labelID = new JLabel("ID a eliminar:");
		this.textID = new JTextField(10);

		this.deleteButton = new JButton("Eliminar");
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textID.getText().isEmpty()) {
						Contexto ctx = new Contexto(CommList.ELIMINAR_PASE,Integer.parseInt(textID.getText()));
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						
						
					} else {
						JOptionPane.showMessageDialog(VistaEliminarPase.this, "Por favor, rellene el campo ID", "Borrar Pase",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(VistaEliminarPase.this, "Numero demasiado largo", "Borrar Pase",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.information = new JTextArea(5, 40);
		this.information.setEditable(false);
		this.information.setLineWrap(true);
		this.information.setBackground(Color.decode("#EEEEEE"));
		this.information.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
				"Information", TitledBorder.CENTER, TitledBorder.TOP));

		extrapanel.add(information);
		panel.add(labelID);
		panel.add(textID);
		panel.add(deleteButton);
		this.add(panel, BorderLayout.NORTH);
		this.add(information, BorderLayout.CENTER);
		
		
	}

	@Override
	public void update(Contexto c) {
		
		if (c.getEvento() == CommList.ELIMINAR_PASE_KO){
			JOptionPane.showMessageDialog(VistaEliminarPase.this, "El ID no existe, o ya esta eliminado", "Eliminar pase",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getEvento() == CommList.ELIMINAR_PASE_OK){
			JOptionPane.showMessageDialog(VistaEliminarPase.this, "El ID ha sido eliminado", "Eliminar pase",
					JOptionPane.INFORMATION_MESSAGE);
		}

		
	}
}
