package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Logico.Equipo;
import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;
import Logico.SerieNacional;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import Excepcion.CamposVaciosException;

public class RegistrarJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDir;
	private JTextField txtNac;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JComboBox CBRol;
	private JComboBox CBEquipo;
	private JSpinner spPeso;
	private JSpinner spAltura;
	private JSpinner spNumeroCami;
	private JComboBox CBLado;
	private JSpinner spFecha;
	private JPanel panel_3;
	private JLabel lblNewLabel_16;
	private JComboBox CBBateador;
	private JPanel panel_2;
	private JComboBox CBLanzador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarJugador dialog = new RegistrarJugador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarJugador() {
		setTitle("Registrar jugador");
		setBounds(100, 100, 607, 437);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				lblNewLabel = new JLabel("Id: ");
				lblNewLabel.setBounds(10, 11, 46, 14);
				panel.add(lblNewLabel);
			}
			{
				lblNewLabel_1 = new JLabel("Nombre: ");
				lblNewLabel_1.setBounds(10, 36, 70, 14);
				panel.add(lblNewLabel_1);
			}
			{
				lblNewLabel_2 = new JLabel("Apellido: ");
				lblNewLabel_2.setBounds(283, 36, 61, 14);
				panel.add(lblNewLabel_2);
			}
			{
				lblNewLabel_3 = new JLabel("Fecha de nacimiento: ");
				lblNewLabel_3.setBounds(10, 64, 142, 20);
				panel.add(lblNewLabel_3);
			}
			{
				lblNewLabel_4 = new JLabel("Telefono: ");
				lblNewLabel_4.setBounds(283, 67, 61, 14);
				panel.add(lblNewLabel_4);
			}
			{
				lblNewLabel_5 = new JLabel("Direccion: ");
				lblNewLabel_5.setBounds(10, 95, 70, 14);
				panel.add(lblNewLabel_5);
			}
			{
				lblNewLabel_6 = new JLabel("Correo electronico: ");
				lblNewLabel_6.setBounds(283, 95, 112, 14);
				panel.add(lblNewLabel_6);
			}
			{
				lblNewLabel_7 = new JLabel("Nacionalidad: ");
				lblNewLabel_7.setBounds(10, 120, 83, 31);
				panel.add(lblNewLabel_7);
			}
			{
				lblNewLabel_8 = new JLabel("Peso: ");
				lblNewLabel_8.setBounds(283, 128, 46, 14);
				panel.add(lblNewLabel_8);
			}
			{
				lblNewLabel_9 = new JLabel("Altura: ");
				lblNewLabel_9.setBounds(434, 128, 46, 14);
				panel.add(lblNewLabel_9);
			}
			{
				lblNewLabel_10 = new JLabel("# Camiseta: ");
				lblNewLabel_10.setBounds(10, 158, 70, 14);
				panel.add(lblNewLabel_10);
			}
			{
				lblNewLabel_11 = new JLabel("Lado dominante: ");
				lblNewLabel_11.setBounds(283, 158, 105, 14);
				panel.add(lblNewLabel_11);
			}
			{
				lblNewLabel_12 = new JLabel("Equipo:");
				lblNewLabel_12.setBounds(10, 187, 46, 14);
				panel.add(lblNewLabel_12);
			}
			{
				lblNewLabel_13 = new JLabel("Rol: ");
				lblNewLabel_13.setBounds(283, 187, 46, 14);
				panel.add(lblNewLabel_13);
			}
			{
				txtId = new JTextField();
				txtId.setEditable(false);
				txtId.setBounds(32, 8, 61, 20);
				panel.add(txtId);
				txtId.setColumns(10);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setBounds(66, 33, 196, 20);
				panel.add(txtNombre);
				txtNombre.setColumns(10);
			}
			
			spFecha = new JSpinner();
			spFecha.setModel(new SpinnerDateModel(new Date(1743220800000L), null, null, Calendar.DAY_OF_YEAR));
			spFecha.setBounds(133, 64, 129, 20);
			panel.add(spFecha);
			
			txtDir = new JTextField();
			txtDir.setBounds(76, 92, 186, 20);
			panel.add(txtDir);
			txtDir.setColumns(10);
			
			txtNac = new JTextField();
			txtNac.setBounds(96, 125, 166, 20);
			panel.add(txtNac);
			txtNac.setColumns(10);
			
			spNumeroCami = new JSpinner();
			spNumeroCami.setModel(new SpinnerNumberModel(0, 0, 99, 1));
			spNumeroCami.setBounds(86, 155, 46, 20);
			panel.add(spNumeroCami);
			
			CBEquipo = new JComboBox();
			CBEquipo.setBounds(73, 183, 129, 20);
			panel.add(CBEquipo);
			cargarEquipo();
			
			txtApellido = new JTextField();
			txtApellido.setBounds(346, 33, 196, 20);
			panel.add(txtApellido);
			txtApellido.setColumns(10);
			
			txtTelefono = new JTextField();
			txtTelefono.setBounds(346, 64, 196, 20);
			panel.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			txtCorreo = new JTextField();
			txtCorreo.setBounds(400, 92, 142, 20);
			panel.add(txtCorreo);
			txtCorreo.setColumns(10);
			
			spPeso = new JSpinner();
			spPeso.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
			spPeso.setBounds(324, 125, 55, 20);
			panel.add(spPeso);
			
			spAltura = new JSpinner();
			spAltura.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spAltura.setBounds(481, 125, 61, 20);
			panel.add(spAltura);
			
			CBLado = new JComboBox();
			CBLado.setModel(new DefaultComboBoxModel(new String[] {"<Selecciones>", "Diestro", "Zurdo", "Ambidiestro"}));
			CBLado.setBounds(386, 155, 156, 20);
			panel.add(CBLado);
			{
				CBRol = new JComboBox();
				CBRol.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(CBRol.getSelectedIndex() == 1) {
							panel_2.setVisible(true);
							panel_3.setVisible(false);
							
						}
						
						if(CBRol.getSelectedIndex() == 2) {
							panel_2.setVisible(false);
							panel_3.setVisible(true);
							
						}
						
					}
				});
				CBRol.setModel(new DefaultComboBoxModel(new String[] {"<Selecciones>", "Lanzador", "Bateador"}));
				CBRol.setBounds(327, 184, 215, 20);
				panel.add(CBRol);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 226, 532, 101);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblNewLabel_14 = new JLabel("Datos especificos");
			lblNewLabel_14.setBounds(10, 11, 104, 14);
			panel_1.add(lblNewLabel_14);
			
			panel_2 = new JPanel();
			panel_2.setBorder(new EmptyBorder(0, 0, 0, 0));
			panel_2.setBounds(10, 36, 512, 46);
			panel_1.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_15 = new JLabel("Rol lanzador: ");
			lblNewLabel_15.setBounds(10, 11, 89, 14);
			panel_2.add(lblNewLabel_15);
			
			CBLanzador = new JComboBox();
			CBLanzador.setBounds(88, 8, 121, 20);
			panel_2.add(CBLanzador);
			CBLanzador.setModel(new DefaultComboBoxModel(new String[] {"<Selecciones>", "Abridor", "Cerrador", "Relevista"}));
			{
				panel_3 = new JPanel();
				panel_3.setBounds(10, 36, 512, 46);
				panel_1.add(panel_3);
				panel_3.setLayout(null);
				{
					lblNewLabel_16 = new JLabel("Rol jugador posicion: ");
					lblNewLabel_16.setBounds(10, 11, 123, 14);
					panel_3.add(lblNewLabel_16);
				}
				{
					CBBateador = new JComboBox();
					CBBateador.setModel(new DefaultComboBoxModel(new String[] {"<Selecciones>", "1B", "2B", "3B", "SS", "C", "RF", "CF", "LF"}));
					CBBateador.setBounds(133, 8, 136, 20);
					panel_3.add(CBBateador);
				}
			}
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 212, 581, 8);
			panel.add(separator);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							validarCampos();
							
							if(CBRol.getSelectedIndex() == 1) {
								
								Lanzador lanzador = new Lanzador(txtId.getText(), txtNombre.getText(), txtApellido.getText(), (Date)spFecha.getValue() , txtTelefono.getText(), txtDir.getText(), txtNac.getText(), txtCorreo.getText(), Integer.valueOf(spNumeroCami.getValue().toString()), (Equipo)CBEquipo.getSelectedItem(),Float.valueOf(spPeso.getValue().toString()) , Float.valueOf(spAltura.getValue().toString()), CBLado.getSelectedItem().toString(),CBLanzador.getSelectedItem().toString());
								SerieNacional.getInstance().ingresarLanzador(lanzador);
								JOptionPane.showMessageDialog(null, "El jugador ha sido registrado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							} 
							
							if(CBRol.getSelectedIndex() == 2) {
								JugadorPosicion jugadorPosicion = new JugadorPosicion(txtId.getText(), txtNombre.getText(), txtApellido.getText(), (Date)spFecha.getValue() , txtTelefono.getText(), txtDir.getText(), txtNac.getText(), txtCorreo.getText(), Integer.valueOf(spNumeroCami.getValue().toString()), (Equipo)CBEquipo.getSelectedItem(),Float.valueOf(spPeso.getValue().toString()) , Float.valueOf(spAltura.getValue().toString()), CBLado.getSelectedItem().toString(),CBBateador.getSelectedItem().toString());
								SerieNacional.getInstance().ingresarJugadorPosicion(jugadorPosicion);
								JOptionPane.showMessageDialog(null, "El jugador ha sido registrado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							}						
							clean();
							
						} catch (CamposVaciosException ex) {
							// TODO: handle exception
							
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
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
		
		clean();
	}
	
	private void validarCampos() throws CamposVaciosException {
		if (txtNombre.getText().isEmpty())
		{
			throw new CamposVaciosException("El nombre es obligatorio.");
		}
		if (txtApellido.getText().isEmpty()) {
			throw new CamposVaciosException("El apellido es obligatorio");
		}
		if (txtDir.getText().isEmpty()) {
			throw new CamposVaciosException("La dirección es obligatoria.");
		}
		if (txtTelefono.getText().isEmpty()) {
			throw new CamposVaciosException("El teléfono es obligatorio.");
		}
		if (txtCorreo.getText().isEmpty()) {
			throw new CamposVaciosException("El correo es obligatorio.");
		}
		if(CBEquipo.getSelectedIndex() == 0) {
			throw new CamposVaciosException("Debe seleccionar un equipo.");
		}
		if (CBRol.getSelectedIndex() == 0) {
			throw new CamposVaciosException("Debe seleccionar un rol.");
		}
		if (CBLado.getSelectedIndex() == 0) {
			throw new CamposVaciosException("El lado dominante es obligatorio.");
		}
		if (spFecha.getValue() == null) {
			throw new CamposVaciosException("Seleccione una fecha de nacimiento.");
		}
		
	}

	private void clean() {
		txtId.setText("J-"+SerieNacional.getGeneradorJugadores());
		txtNombre.setText("");
		txtApellido.setText("");
		txtDir.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		txtNac.setText("");
		spNumeroCami.setValue(0);
		spPeso.setValue(0);
		spAltura.setValue(0);
		CBLado.setSelectedIndex(0);
		CBRol.setSelectedIndex(0);
		
			
	}

	private void cargarEquipo() {
		for(Equipo equipo : SerieNacional.getInstance().getMisEquipos()) {
			CBEquipo.addItem(equipo);
		}
		
	}
}
