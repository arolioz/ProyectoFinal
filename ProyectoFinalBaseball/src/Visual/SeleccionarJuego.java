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
import java.awt.Toolkit;
import java.awt.FlowLayout;

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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(SeleccionarJuego.class.getResource("/Image/Logo.png")));
    	setTitle("Seleccionar juego");
        setBounds(100, 100, 920, 620);
        getContentPane().setLayout(new BorderLayout());
        ArrayList<Juego> juegos = SerieNacional.getInstance().getMisJuegos();		        
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
                    sim.setModal(true);
                    sim.setVisible(true); 
                   
                }
            });

            tarjeta.add(lblLocal);
            tarjeta.add(lblVisitante);
            tarjeta.add(lblFecha);
            tarjeta.add(btnIniciar);
            contentPanel.add(tarjeta);
        }


        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JButton btnNewButton = new JButton("Terminar Torneo");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int respuesta = JOptionPane.showConfirmDialog(null, 
                        "¿Estás seguro de que quieres terminar el partido?", 
                        "Confirmar", 
                        JOptionPane.YES_NO_OPTION);
                  
                    if (respuesta == JOptionPane.YES_OPTION) {
                    	SerieNacional.getInstance().terminarTroneo();              
                        dispose();
                    }
        	}
        });
        buttonPane.add(btnNewButton);
        buttonPane.add(cancelButton);
    }
}
