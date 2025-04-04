package Visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;

import Logico.Equipo;
import Logico.Juego;
import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;
import Logico.SerieNacional;

public class SeleccionarJuego extends JDialog {
	private JButton cancelButton;
    public static void main(String[] args) {
        try {
            SeleccionarJuego dialog = new SeleccionarJuego();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    public SeleccionarJuego() {
    	setTitle("Seleccionar juego");
        setBounds(100, 100, 920, 620);
        getContentPane().setLayout(new BorderLayout());
        ArrayList<Juego> juegos = SerieNacional.getInstance().getMisJuegos();

        
		Equipo equipo = new Equipo("1", "Lakers", "Santiago", "Contreras");
		Equipo equipo1 = new Equipo("2", "Chicago bull", "Santiago", "Contreras");
		Equipo equipo2 = new Equipo("3", "Golden state", "Santiago", "Contreras");
		Equipo equipo3 = new Equipo("4", "Santiago", "Santiago", "Contreras");
		
		JugadorPosicion jugador2 = new JugadorPosicion("1", "Carlos", "Gómez", new Date(), "829-444-5678", "Avenida 456, Santiago", "Dominicana", "carlosg@example.com", 6, equipo, 78, 1, "Izquierdo", "Jardinero Central");
		JugadorPosicion jugador1 = new JugadorPosicion("2", "CABALLO", "Gómez", new Date(), "829-444-5678", "Avenida 456, Santiago", "Dominicana", "carlosg@example.com", 6, equipo, 78, 1, "Izquierdo", "Jardinero Central");
		Lanzador l1 = new Lanzador("30","Aaron","Escano",new Date(),"829-444-5678", "Avenida 456, Santiago", "Dominicana", "carlosg@example.com", 6, equipo, 78, 1, "Izquierdo", "Abridor");
		Lanzador l2 = new Lanzador("29","Aaron","Escano",new Date(),"829-444-5678", "Avenida 456, Santiago", "Dominicana", "carlosg@example.com", 6, equipo, 78, 1, "Izquierdo", "Abridor");
		
		equipo.getMisJugadores().add(l1);
		equipo.getMisJugadores().add(jugador2);
		equipo1.getMisJugadores().add(jugador1);
		equipo1.getMisJugadores().add(l2);
		
		ArrayList<Equipo> misEquipos = new ArrayList<>();
		misEquipos.add(equipo);
		misEquipos.add(equipo1);
		misEquipos.add(equipo2);
		misEquipos.add(equipo3);
		
		SerieNacional.getInstance().crearTorneo(misEquipos);
		for(int i = 0; i < SerieNacional.getInstance().getMisJuegos().size();i++) {
			System.out.println("Equipo Local:"+SerieNacional.getInstance().getMisJuegos().get(i).getEquipoLocal().getNombre() +" Equipo visitante:"+SerieNacional.getInstance().getMisJuegos().get(i).getEquipoVisitante().getNombre()+"\n");
		}
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 3, 10, 10));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        for (Juego juego : juegos) {
            JPanel tarjeta = new JPanel();
            tarjeta.setLayout(null);
            tarjeta.setBorder(BorderFactory.createTitledBorder("Juego"));
            tarjeta.setPreferredSize(new Dimension(250, 130));

            JLabel lblLocal = new JLabel("Local: " + juego.getEquipoLocal().getNombre());
            lblLocal.setBounds(10, 20, 200, 20);

            JLabel lblVisitante = new JLabel("Visitante: " + juego.getEquipoVisitante().getNombre());
            lblVisitante.setBounds(10, 45, 200, 20);

            JLabel lblFecha = new JLabel("Fecha: " + juego.getFechaPartido());
            lblFecha.setBounds(10, 70, 200, 20);

            JButton btnIniciar = new JButton("Iniciar Juego");
            btnIniciar.setBounds(10, 95, 200, 26);
            
            tarjeta.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 2), "Juego"));
                        
            if(juego.getFechaPartido().isAfter(LocalDate.now())) {
            	btnIniciar.setEnabled(false);
            	tarjeta.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "Juego"));
            }
            
            if(juego.isJuegoTerminado()) {
                btnIniciar.setEnabled(false);
                
                JButton btnTerminado = new JButton("Juego terminado");
                btnTerminado.setBounds(10, 95, 200, 26);
                tarjeta.add(btnTerminado);
                btnTerminado.setEnabled(false);
                btnIniciar.setBounds(10, 250, 200, 26);
                tarjeta.setBorder(new TitledBorder(new LineBorder(Color.GREEN, 2), "Juego"));
                
            }
            
            
            
            btnIniciar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Iniciando juego entre "+ juego.getEquipoLocal().getNombre() + " vs "+ juego.getEquipoVisitante().getNombre());
                    SimuladorJuego sim = new SimuladorJuego(juego);
                    sim.setVisible(true);
                    sim.setModal(true);
                    
                }
            });

            tarjeta.add(lblLocal);
            tarjeta.add(lblVisitante);
            tarjeta.add(lblFecha);
            tarjeta.add(btnIniciar);
            contentPanel.add(tarjeta);
        }


        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.setLayout(new BorderLayout(0, 0));
        buttonPane.add(cancelButton, BorderLayout.CENTER);
    }
}
