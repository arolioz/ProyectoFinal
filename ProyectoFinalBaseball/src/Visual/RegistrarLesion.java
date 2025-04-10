package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Juego;
import Logico.Jugador;
import Logico.Lesion;
import Logico.SerieNacional;

import javax.swing.border.BevelBorder;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class RegistrarLesion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JButton okButton;
	private JButton cancelButton;
	private Label idLabel;
	private JTextField txtId;
	private JComboBox cbxJugador;
	private JComboBox cbxJuego;
	private JLabel lblJugador;
	private JLabel lblJuego;
	private JLabel lblTipo;
	private JLabel lblReincorporacion;
	private JLabel lblDescripcion;
	private JTextField txtDescripcion;
	private JSpinner spnReincorporacion;
	private JComboBox cmbLesiones;

	/*
	public static void main(String[] args) {
		try {
			RegistrarLesion dialog = new RegistrarLesion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 */
	public RegistrarLesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarLesion.class.getResource("/Image/Logo.png")));
		setTitle("Registrar lesión");
		setBounds(100, 100, 483, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			idLabel = new Label("Id: ");
			idLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			idLabel.setBounds(21, 20, 62, 22);
			panel.add(idLabel);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setBounds(89, 20, 122, 20);
			panel.add(txtId);
			txtId.setColumns(10);
			
			lblJugador = new JLabel("Jugador:");
			lblJugador.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblJugador.setBounds(21, 61, 62, 14);
			panel.add(lblJugador);
			
			cbxJugador = new JComboBox();
			cbxJugador.setBounds(89, 59, 122, 20);
			panel.add(cbxJugador);
			cargarJugador();
			
			lblJuego = new JLabel("Juego:");
			lblJuego.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblJuego.setBounds(21, 103, 46, 14);
			panel.add(lblJuego);
			
			cbxJuego = new JComboBox();
			cbxJuego.setBounds(90, 101, 121, 20);
			panel.add(cbxJuego);
			cargarJuego();
			
			lblTipo = new JLabel("Tipo de lesi\u00F3n:");
			lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblTipo.setBounds(225, 61, 86, 14);
			panel.add(lblTipo);
			
			lblReincorporacion = new JLabel("Reincorporaci\u00F3n:");
			lblReincorporacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblReincorporacion.setBounds(225, 103, 102, 14);
			panel.add(lblReincorporacion);
			
			lblDescripcion = new JLabel("Descripci\u00F3n:");
			lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblDescripcion.setBounds(21, 151, 86, 14);
			panel.add(lblDescripcion);
			
			txtDescripcion = new JTextField();
			txtDescripcion.setBounds(90, 149, 357, 20);
			panel.add(txtDescripcion);
			txtDescripcion.setColumns(10);
			
			spnReincorporacion = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor fecha = new JSpinner.DateEditor(spnReincorporacion, "dd/MM/yyyy");
			spnReincorporacion.setEditor(fecha);
			spnReincorporacion.setBounds(321, 101, 126, 20);
			panel.add(spnReincorporacion);
			
			cmbLesiones = new JComboBox();
			cmbLesiones.setModel(new DefaultComboBoxModel(new String[] {"<Seleccionar>", "Codo de lanzador", "Tendinitis del manguito rotador", "Desgarro del labrum", "Esguinces de hombro", "Epicondilitis lateral", "Tendinitis del b\u00EDceps", "Distensiones musculares", "Fracturas por estr\u00E9s", "Lesiones en los dedos", "Conmociones cerebrales"}));
			cmbLesiones.setBounds(321, 59, 126, 20);
			panel.add(cmbLesiones);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						if (cmbLesiones.getSelectedIndex() > 0) {
							Lesion lesion = new Lesion(txtId.getText(), cmbLesiones.getSelectedItem().toString(), (Juego) cbxJuego.getSelectedItem(), (Jugador) cbxJugador.getSelectedItem(), 0, new Date(), (Date) spnReincorporacion.getValue(), txtDescripcion.getText() );
							SerieNacional.getInstance().ingresarLesion(lesion);
							JOptionPane.showMessageDialog(null, "La lesión ha sido registrada satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							clean(); 
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de lesion", null, JOptionPane.WARNING_MESSAGE, null);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	clear();
	}
	
	private void clean() {
		txtId.setText("L-"+SerieNacional.getInstance().getGeneradorLesiones());
		cbxJugador.setSelectedIndex(0);
		cbxJuego.setSelectedIndex(0);
		cmbLesiones.setSelectedIndex(0);
		spnReincorporacion.setValue(new Date());
		txtDescripcion.setText("");
		
		
	}
	
	private void clear() {
		txtId.setText("L-"+SerieNacional.getInstance().getGeneradorLesiones());
		
	}
	private void cargarJugador() 
	{
		ArrayList<Jugador> listJugador = SerieNacional.getInstance().getMisJugadores();
		for (Jugador jugador : listJugador) {
			cbxJugador.addItem(jugador);
		}
	}
	
	private void cargarJuego() 
	{
		ArrayList<Juego> listJuego = SerieNacional.getInstance().getMisJuegos();
		for(Juego juego : listJuego) {
			cbxJuego.addItem(juego);
		}
	}
}
