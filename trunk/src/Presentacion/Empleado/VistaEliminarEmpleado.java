package Presentacion.Empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Presentacion.Observer;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Departamento.VistaEliminarDepartamento;

public class VistaEliminarEmpleado extends JPanel implements Observer{

	private JLabel labelID;
	private JTextField textID;
	private JButton deleteButton;
	
	public VistaEliminarEmpleado(){
		super();
		this.initGUI();
	}

	private void initGUI(){
		this.labelID = new JLabel("ID: ");
		this.textID = new JTextField(10);
		this.textID.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		this.deleteButton = new JButton("eliminar");
		this.deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!textID.getText().isEmpty()) {
					try{
						Contexto ctx = new Contexto(CommList.ELIMINAR_EMPLEADO, textID.getText());
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						
					}catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(VistaEliminarEmpleado.this, "Numero demasiado largo", "Eliminar Empleado",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(VistaEliminarEmpleado.this, "Rellene el campo ID",
							"Eliminar Empleado", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.add(labelID);
		this.add(textID);
		this.add(deleteButton);
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_KO){
			JOptionPane.showMessageDialog(VistaEliminarEmpleado.this, "El ID no existe, o ya esta eliminado", "Eliminar empleado",
					JOptionPane.ERROR_MESSAGE);
		} else if (c.getEvento() == CommList.ELIMINAR_EMPLEADO_OK){
			JOptionPane.showMessageDialog(VistaEliminarEmpleado.this, "El ID ha sido eliminado", "Eliminar empleado",
					JOptionPane.INFORMATION_MESSAGE);
		}	
	}
}
