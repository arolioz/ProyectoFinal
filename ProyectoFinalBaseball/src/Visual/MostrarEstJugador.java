package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Jugador;

import javax.swing.JLabel;
import java.awt.Font;

public class MostrarEstJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MostrarEstJugador dialog = new MostrarEstJugador(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MostrarEstJugador(Jugador jugador) {
		
		if (jugador != null) {
			txtNombre.setText("Estadisticas de " + jugador.getNombre());
		}
		
		
		setTitle("Estadisticas de jugador");
		setBounds(100, 100, 967, 515);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 931, 421);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JPanel panelLanzador = new JPanel();
		panelLanzador.setBounds(58, 90, 832, 320);
		panel.add(panelLanzador);
		
		JPanel panelBateador = new JPanel();
		panelBateador.setBounds(58, 90, 832, 320);
		panel.add(panelBateador);
		
		txtNombre = new JLabel("Estadisticas de {NOMBRE}");
		txtNombre.setFont(new Font("Broadway", Font.PLAIN, 16));
		txtNombre.setBounds(363, 37, 255, 42);
		panel.add(txtNombre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
