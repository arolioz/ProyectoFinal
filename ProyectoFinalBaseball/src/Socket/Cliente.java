package Socket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Cliente extends JFrame {

	private JPanel contentPane;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnRespaldo = new JMenu("Respaldo");
		menuBar.add(mnRespaldo);

		JMenuItem mntmRespaldo = new JMenuItem("Realizar respaldo");
		mntmRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					sfd = new Socket("0",7000); //IP

					File[] archivos = {new File("SerieNacional.dat"), new File("Generadores.dat"), new File("Usuarios.dat")};

					SalidaSocket = new DataOutputStream((sfd.getOutputStream()));

					SalidaSocket.writeInt(archivos.length);
					SalidaSocket.flush();

					for(File arch : archivos) {
						SalidaSocket.writeUTF(arch.getName());
						SalidaSocket.flush();

						DataInputStream aux = new DataInputStream(new FileInputStream(arch));

						int unByte;
						try {
							while ((unByte = aux.read()) != -1) {
								SalidaSocket.write(unByte);
								SalidaSocket.flush();
							}
							aux.close();
							System.out.println("Archivo " + arch.getName() + " enviado.");
						} 
						catch (IOException ioe) {
							System.out.println("Error al enviar archivo " + arch.getName() + ": " + ioe);
						}

					}
				}catch (UnknownHostException uhe)
				{
					System.out.println("No se puede acceder al servidor.");
					System.exit(1);
				} catch (IOException ioe)
				{
					System.out.println("Comunicación rechazada.");
					System.exit(1);
				}

			}
		});
		mnRespaldo.add(mntmRespaldo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
