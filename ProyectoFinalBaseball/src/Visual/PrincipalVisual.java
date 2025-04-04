package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Equipo;
import Logico.SerieNacional;
import Logico.Control;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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
	

	/**
	 * Create the frame.
	 */
	public PrincipalVisual() {
		
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
					
					SerieNacional.getInstance().guardarGeneradores();
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Jugador");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarJugador aux = new RegistrarJugador();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado de jugadores");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ListadoJugadores aux = new ListadoJugadores();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("Equipo");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Equipo");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarEquipo aux = new RegistrarEquipo();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado de equipos");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Equipo> equipo = SerieNacional.getInstance().getMisEquipos();
		        
		        if (equipo == null || equipo.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "No hay equipos para mostrar", "Aviso", JOptionPane.WARNING_MESSAGE);
		            return;
		        }
		        
				ListadoEquipo aux = new ListadoEquipo();
				aux.setModal(true);
				aux.setVisible(true);
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
				TablaDePosiciones aux = new TablaDePosiciones();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		
		tablaDePosiciones.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("Jugadores");
		mntmNewMenuItem_5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				ListadoMejoresJugadores aux = new ListadoMejoresJugadores();
				aux.setModal(true);
				aux.setVisible(true);
			}
		});
		tablaDePosiciones.add(mntmNewMenuItem_5);
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		if(!Control.getLoginUser().getTipo().equalsIgnoreCase("Administrador")) {
			mnmAdmin.setEnabled(false);
		}
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PrincipalVisual.class.getResource("/Image/Estadio.jpeg")));
		lblNewLabel.setBounds(129, 106, 1765, 904);
		panel.add(lblNewLabel);
		


	}
}
