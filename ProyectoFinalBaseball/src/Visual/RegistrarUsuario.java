package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Control;
import Logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtContrasena;
	private JTextField txtConfirContra;
	private JComboBox CBTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarUsuario dialog = new RegistrarUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarUsuario() {
		setBounds(100, 100, 450, 228);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setBounds(20, 26, 97, 14);
		contentPanel.add(lblNombreUsuario);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(20, 49, 127, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		CBTipo = new JComboBox();
		CBTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administrador", "Comercial"}));
		CBTipo.setBounds(20, 113, 127, 20);
		contentPanel.add(CBTipo);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 88, 97, 14);
		contentPanel.add(lblTipo);
		
		txtContrasena = new JTextField();
		txtContrasena.setBounds(190, 49, 147, 20);
		contentPanel.add(txtContrasena);
		txtContrasena.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(189, 26, 97, 14);
		contentPanel.add(lblPassword);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Password:");
		lblConfirmarPassword.setBounds(189, 88, 167, 14);
		contentPanel.add(lblConfirmarPassword);
		
		txtConfirContra = new JTextField();
		txtConfirContra.setColumns(10);
		txtConfirContra.setBounds(190, 113, 147, 20);
		contentPanel.add(txtConfirContra);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtContrasena.getText().equals(txtConfirContra.getText())) {
							User user = new User(CBTipo.getSelectedItem().toString(),txtNombre.getText(),txtContrasena.getText());
						    Control.getInstance().regUser(user);
						    JOptionPane.showMessageDialog(null, "Usuaria registrado exitosamente!!! ");
						    clear();
						} else {

						    
							JOptionPane.showMessageDialog(null, "La contraseña no coincide", null, JOptionPane.WARNING_MESSAGE, null);
							txtConfirContra.setText("");
							txtContrasena.setText("");
							
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void clear() {
		txtNombre.setText("");
		txtConfirContra.setText("");
		txtContrasena.setText("");
		CBTipo.setSelectedIndex(0);
	}
}
