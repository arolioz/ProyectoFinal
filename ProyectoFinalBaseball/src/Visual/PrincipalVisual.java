package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepcion.SinEquiposException;
import Excepcion.SinJugadoresException;
import Logico.Equipo;
import Logico.Jugador;
import Logico.SerieNacional;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import Logico.Control;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PrincipalVisual extends JFrame {

	
	private JPanel contentPane;
	private Dimension dim;
	private JPanel panel;
	//private JMenu TablaDePosiciones;
	private JMenu tablaDePosiciones;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mnAdmin;
	private JMenu mnmAdmin;
	private JMenuItem mntmNewMenuItem_5;
	private JButton btnIniciarTorneo;
	private JButton btnNewButton_1;
	private JMenuItem Respaldo;
	
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	private JMenuItem mmRegistrarJugador;
	private JMenu mnNewMenu_1;
	private JMenuItem mmRegistrarEquipo;
	private JMenuItem mmRegistrarLesion;
	

	/**
	 * Create the frame.
	 */
	public PrincipalVisual() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PrincipalVisual.class.getResource("/Image/Logo.png")));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream usuario;
				ObjectOutputStream usuarioWrite;
				
				FileOutputStream serieNacional;
				ObjectOutputStream serieNacionarWrite;
				
				FileOutputStream generador;
				ObjectOutputStream generadorWrite; 
				try {
					usuario = new  FileOutputStream("Usuarios.dat");
					usuarioWrite = new ObjectOutputStream(usuario);
					usuarioWrite.writeObject(Control.getInstance());
					
					serieNacional = new  FileOutputStream("SerieNacional.dat");
					serieNacionarWrite = new ObjectOutputStream(serieNacional);
					serieNacionarWrite.writeObject(SerieNacional.getInstance());
					

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		setTitle("Serie Nacional");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dim = super.getToolkit().getScreenSize();
		dim.setSize(dim.getWidth(), dim.getHeight()-50);
		super.setSize(dim);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Jugador");
		menuBar.add(mnNewMenu);
		
		mmRegistrarJugador = new JMenuItem("Registrar Jugador");
		mmRegistrarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					if(SerieNacional.getInstance().getMisEquipos().isEmpty())
					{
						throw new SinEquiposException("Debe registrar al menos un equipo antes de ingresar un jugador.");
					}
					
					RegistrarJugador aux = new RegistrarJugador();
					aux.setModal(true);
					aux.setVisible(true);
				} catch ( SinEquiposException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		mnNewMenu.add(mmRegistrarJugador);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado de jugadores");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					ArrayList<Jugador> jugadores = SerieNacional.getInstance().getMisJugadores();
					
					if(jugadores == null || jugadores.isEmpty()) 
					{
						throw new SinJugadoresException("No hay jugadores para mostrar.");
					}
					
					ListadoJugadores aux = new ListadoJugadores();
					aux.setModal(true);
					aux.setVisible(true);
					
				} catch ( SinJugadoresException ex ) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mnNewMenu_1 = new JMenu("Equipo");
		menuBar.add(mnNewMenu_1);
		
		mmRegistrarEquipo = new JMenuItem("Registrar Equipo");
		mmRegistrarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEquipo aux = new RegistrarEquipo();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnNewMenu_1.add(mmRegistrarEquipo);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado de equipos");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					ArrayList<Equipo> equipo = SerieNacional.getInstance().getMisEquipos();
			        
			        if (equipo == null || equipo.isEmpty()) 
			        {
			            throw new SinEquiposException("No hay equipos para mostrar.");
			        }
			        
					ListadoEquipo aux = new ListadoEquipo();
					aux.setModal(true);
					aux.setVisible(true);
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		
		
		
		tablaDePosiciones = new JMenu("Tabla de Posiciones");
		menuBar.add(tablaDePosiciones);
		
		mntmNewMenuItem_4 = new JMenuItem("Equipos");
		mntmNewMenuItem_4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ArrayList<Equipo> equipos = SerieNacional.getInstance().getMisEquipos();
					
					if(equipos == null || equipos.isEmpty()) 
					{
						throw new SinEquiposException("No hay equipos para mostrar");
					}
					
					TablaDePosiciones aux = new TablaDePosiciones();
					aux.setModal(true);
					aux.setVisible(true);
					
				} catch(SinEquiposException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
		
		tablaDePosiciones.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("Jugadores");
		mntmNewMenuItem_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ArrayList<Jugador> jugadores = SerieNacional.getInstance().getMisJugadores();
					
					if(jugadores == null || jugadores.isEmpty()) 
					{
						throw new SinJugadoresException("No hay jugadores para mostrar.");
					}
					
					ListadoMejoresJugadores aux = new ListadoMejoresJugadores();
					aux.setModal(true);
					aux.setVisible(true);
					
				} catch ( SinJugadoresException ex ) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		tablaDePosiciones.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_2 = new JMenu("Lesion");
		menuBar.add(mnNewMenu_2);
		
		mmRegistrarLesion = new JMenuItem("Registrar Lesion");
		mmRegistrarLesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (SerieNacional.getInstance().getMisJuegos().size() > 0) {
					RegistrarLesion lesion = new RegistrarLesion();
					lesion.setModal(true);
					lesion.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe haber al menos un partido para registrar una lesion", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		mnNewMenu_2.add(mmRegistrarLesion);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listar lesiones");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoLesion lesion = new ListadoLesion();
				lesion.setModal(true);
				lesion.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_3 = new JMenu("Juego");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Estadisticas juegos");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEstadisticaJuego est = new ListarEstadisticaJuego();
				est.setModal(true);
				est.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		mnmAdmin = new JMenu("Administraccion");
		menuBar.add(mnmAdmin);
		
		mnAdmin = new JMenuItem("Registrar Usuarios");
		mnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarUsuario reg = new RegistrarUsuario();
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mnmAdmin.add(mnAdmin);
		
		Respaldo = new JMenuItem("Respaldo");
		Respaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
			    {
			      sfd = new Socket("0",7000); //IP
			      
			      File[] archivos = {new File("SerieNacional.dat"), new File("Usuarios.dat")};

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
                          JOptionPane.showMessageDialog(null, "Se ha hecho el respaldo del archivo. " + arch.getName() + " correctamente", null, JOptionPane.INFORMATION_MESSAGE, null);
                      } 
			    	  catch (IOException ioe) {
			    		  JOptionPane.showMessageDialog(null, "No se ha podido hacer el respaldo.", null, JOptionPane.WARNING_MESSAGE, null);
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
		mnmAdmin.add(Respaldo);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Cargar respaldo");
		mnmAdmin.add(mntmNewMenuItem_9);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));



		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Image/Estadio.png")));
		lblNewLabel.setBounds(305, 0, 1716, 776);
		panel.add(lblNewLabel);
		
		btnIniciarTorneo = new JButton("");
		btnIniciarTorneo.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Image/BotonIniciarTorneo.png")));
		btnIniciarTorneo.setForeground(Color.WHITE);
		btnIniciarTorneo.setBackground(Color.WHITE);
		btnIniciarTorneo.setFont(new Font("Calisto MT", Font.PLAIN, 30));
		btnIniciarTorneo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(SerieNacional.getInstance().getMisJuegos().size() > 0) {
					JOptionPane.showMessageDialog( null, "No pueden haber dos torneos al mismo tiempo, primero debes terminar el anterior para crear uno nuevo","Advertencia",JOptionPane.WARNING_MESSAGE);
					
				}
				else {
					if(SerieNacional.getInstance().getMisEquipos().size() >= 2) {
						SeleccionarEquipo eq = new SeleccionarEquipo(); 
						eq.setModal(true);
						eq.setVisible(true);
					} else {
						JOptionPane.showMessageDialog( null, "Debes ingresar al menos dos equipos para poder iniciar un torneo!!!","Advertencia",JOptionPane.WARNING_MESSAGE);
					}
				}
				




				
			}
		});
		btnIniciarTorneo.setBounds(817, 787, 192, 165);
		panel.add(btnIniciarTorneo);
		

		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(SerieNacional.getInstance().getMisJuegos().size() > 0) {
					SeleccionarJuego juego = new SeleccionarJuego();
					juego.setModal(true);
					juego.setVisible(true);
				} else {
					JOptionPane.showMessageDialog( null, "Primero debes iniciar un torneo.","Advertencia",JOptionPane.WARNING_MESSAGE);
				}


			}
		});

		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Image/BotonCalendario.png")));
		btnNewButton_1.setBounds(10, 11, 200, 196);
		panel.add(btnNewButton_1);
		
		

		Anotador();
	}
	
	private void Anotador() {
		if(!Control.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
			mnmAdmin.setEnabled(false);
			mmRegistrarEquipo.setVisible(false);
			mmRegistrarJugador.setVisible(false);
			mmRegistrarLesion.setVisible(false);
			mnmAdmin.setVisible(false);
			btnIniciarTorneo.setVisible(false);
		}
	}
}
