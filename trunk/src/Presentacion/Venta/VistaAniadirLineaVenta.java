package Presentacion.Venta;

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

import Negocio.Pase.TPase;
import Negocio.Venta.TLinea_Venta;
import Negocio.Venta.TVenta;
import Presentacion.Observer;
import Presentacion.Comando.Comando;
import Presentacion.Comando.CommList;
import Presentacion.Comando.Contexto;
import Presentacion.Controlador.ControladorDeAplicacion;
import Presentacion.Empleado.VistaActualizarEmpleado;

public class VistaAniadirLineaVenta extends JPanel implements Observer{

	private JTextField ID_venta;
	private JTextField ID_pase;
	private JTextField cantidad;

	private JButton addButton;

	public VistaAniadirLineaVenta() {
		super();
		this.initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		LayoutManager layout = new GridLayout(1, 2);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));

		JPanel panelVentaID = new JPanel();
		panelVentaID.setLayout(layout);
		panelVentaID.add(new JLabel("ID venta: "));
		this.ID_venta = new JTextField(10);
		// just enter number
		this.ID_venta.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelVentaID.add(ID_venta);
		panelVentaID.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel panelProductID = new JPanel();
		panelProductID.setLayout(layout);
		panelProductID.add(new JLabel("ID pase: "));
		this.ID_pase = new JTextField(10);
		// just enter number
		this.ID_pase.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelProductID.add(ID_pase);
		panelProductID.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		JPanel panelQuantity = new JPanel();
		panelQuantity.setLayout(layout);
		panelQuantity.add(new JLabel("Cantidad: "));
		this.cantidad = new JTextField(10);
		// just enter number
		this.cantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (!(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) {
					e.consume();
				}
			}
		});
		panelQuantity.add(cantidad);
		panelQuantity.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		panel.add(panelVentaID);
		panel.add(panelProductID);
		panel.add(panelQuantity);

		this.add(panel, BorderLayout.CENTER);

		this.addButton = new JButton("A�adir linea venta");
		this.addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!hasEmpatyTextField()) {
					try {
						Contexto ctx = new Contexto();
						ctx.setEvento(CommList.ANIADIR_LINEA_VENTA);
						TLinea_Venta Lventa = new TLinea_Venta(Integer.parseInt(ID_venta.getText()),
								Integer.parseInt(ID_pase.getText()), Integer.parseInt(cantidad.getText()));
						ctx.setDato(Lventa);
						ControladorDeAplicacion.getInstancia().manejarPeticion(ctx);
						
						
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(VistaAniadirLineaVenta.this, "Formato incorrecto",
								"A�adir linea venta", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(VistaAniadirLineaVenta.this, "Rellene todos los campos",
							"A�adir linea venta", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.add(this.addButton, BorderLayout.PAGE_END);
	}

	private boolean hasEmpatyTextField() {
		return ID_pase.getText().isEmpty() || ID_pase.getText().equals("0") || cantidad.getText().isEmpty()
				|| cantidad.getText().equals("0");
	}

	@Override
	public void update(Contexto c) {
		if (c.getEvento() == CommList.ANIADIR_LINEA_VENTA_OK) {
			JOptionPane.showMessageDialog(VistaAniadirLineaVenta.this, "La linea de venta se ha a�dido", "A�adir Linea Venta",
				JOptionPane.INFORMATION_MESSAGE);
		}
		else if (c.getEvento() == CommList.ANIADIR_LINEA_VENTA_KO) {
			JOptionPane.showMessageDialog(VistaAniadirLineaVenta.this, "Error al a�adir la Linea de venta", "A�adir Linea Venta",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
