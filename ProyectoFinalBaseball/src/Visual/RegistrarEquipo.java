package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepcion.CamposVaciosException;
import Logico.Equipo;
import Logico.SerieNacional;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class RegistrarEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtCiudad;
	private JTextField txtEstadio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarEquipo dialog = new RegistrarEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarEquipo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrarEquipo.class.getResource("/Image/Logo.png")));
		setTitle("Registrar equipo");
		setBounds(100, 100, 341, 228);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Id:");
			lblNewLabel.setBounds(10, 11, 46, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre: ");
			lblNewLabel_1.setBounds(10, 36, 56, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Ciudad: ");
			lblNewLabel_2.setBounds(10, 69, 46, 14);
			panel.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Estadio: ");
			lblNewLabel_3.setBounds(10, 109, 56, 14);
			panel.add(lblNewLabel_3);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setBounds(31, 8, 75, 20);
			panel.add(txtId);
			txtId.setColumns(10);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(60, 33, 207, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtCiudad = new JTextField();
			txtCiudad.setBounds(60, 66, 207, 20);
			panel.add(txtCiudad);
			txtCiudad.setColumns(10);
			
			txtEstadio = new JTextField();
			txtEstadio.setBounds(60, 106, 207, 20);
			panel.add(txtEstadio);
			txtEstadio.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						try {
							validarCampos();
							
							Equipo equipo = new Equipo(txtId.getText(), txtNombre.getText(), txtCiudad.getText(), txtEstadio.getText());
							SerieNacional.getInstance().ingresarEquipo(equipo);
							JOptionPane.showMessageDialog(null, "El equipo ha sido registrado satisfactoriamente", null, JOptionPane.INFORMATION_MESSAGE, null);
							clean(); 
						} catch (CamposVaciosException ex) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		
		clean();
	}
	
	private void validarCampos() throws CamposVaciosException 
	{
		if (txtNombre.getText().isEmpty()) 
		{
			throw new CamposVaciosException("El nombre del equipo es obligatorio.");
		}
		if (txtCiudad.getText().isEmpty()) 
		{
			throw new CamposVaciosException("La ciudad del equipo es obligatoria.");
		}
		if (txtEstadio.getText().isEmpty()) 
		{
			throw new CamposVaciosException("El estadio del equipo es obligatorio.");
		}
	}
	
	private void clean() {
		txtId.setText("E-"+SerieNacional.getGeneradorEquipos());
		txtNombre.setText("");
		txtEstadio.setText("");
		txtCiudad.setText("");
	}
}
