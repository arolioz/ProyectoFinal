package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepcion.CamposVaciosException;
import Excepcion.UsuarioYaExisteException;
import Logico.Control;
import Logico.SerieNacional;
import Logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContrasena;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream usuario1;
				FileOutputStream usuario2;
				ObjectInputStream usuarioRead;
				ObjectOutputStream usuarioWrite;
				
				FileInputStream serieNacional1;
				FileOutputStream serieNacional2;
				ObjectInputStream serieNacionalRead;
				ObjectOutputStream serieNacionalWrite;
				

				
				try {
					usuario1 = new FileInputStream ("Usuarios.dat");
					usuarioRead = new ObjectInputStream(usuario1);
					Control temp = (Control)usuarioRead.readObject();
					Control.setControl(temp);
					usuario1.close();
					usuarioRead.close();
					
					serieNacional1 = new FileInputStream ("SerieNacional.dat");
					serieNacionalRead = new ObjectInputStream(serieNacional1);
					SerieNacional temp1 = (SerieNacional)serieNacionalRead.readObject();
					SerieNacional.setSerieNacional(temp1);
					serieNacional1.close();
					serieNacionalRead.close();
					
					SerieNacional.getInstance().cargarGeneradores();
				} catch (FileNotFoundException e) {
					try {
						usuario2 = new  FileOutputStream("Usuarios.dat");
						usuarioWrite = new ObjectOutputStream(usuario2);
						User aux = new User("Administrador", "Admin", "Admin");
						
						try {
							Control.getInstance().regUser(aux);
						} catch (UsuarioYaExisteException exc) {
							// TODO: handle exception
							JOptionPane.showConfirmDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						
						usuarioWrite.writeObject(Control.getInstance());
						usuario2.close();
						usuarioWrite.close();
						
						serieNacional2 = new  FileOutputStream("SerieNacional.dat");
						serieNacionalWrite = new ObjectOutputStream(serieNacional2);
						serieNacionalWrite.writeObject(SerieNacional.getInstance());
						serieNacional2.close();
						serieNacionalWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Image/Logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(39, 39, 75, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(39, 98, 105, 14);
		panel.add(lblContrasea);
		
		textNombre = new JTextField();
		textNombre.setBounds(39, 64, 191, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		textContrasena = new JTextField();
		textContrasena.setBounds(39, 128, 191, 20);
		panel.add(textContrasena);
		textContrasena.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Control.getInstance().confirmLogin(textNombre.getText(),textContrasena.getText())){
					PrincipalVisual frame = new PrincipalVisual();
					dispose();
					frame.setVisible(true);
				};
				
			}
		});
		btnLogin.setBounds(37, 175, 89, 23);
		panel.add(btnLogin);
	}
	
}