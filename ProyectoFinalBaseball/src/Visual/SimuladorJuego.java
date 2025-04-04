package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Juego;
import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;

import java.awt.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class SimuladorJuego extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private DefaultTableModel modelVisitante;
    private DefaultTableModel modelLocal;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField txtLocal;
    private JTable tablaLocal;
    static Object[] filaVisitante;
    static Object[] filaLocal;
    private JTable tablaVisitante;

    public static void main(String[] args) {
        try {
            SimuladorJuego dialog = new SimuladorJuego(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SimuladorJuego(Juego partido) {
        setTitle("Partido de {Local} vs {Visitante}");

        setBounds(100, 100, 1191, 765);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        String nombreELocal = "Local";
        String nombreEVisitante = "Visitante";

        if (partido != null) {
            nombreELocal = partido.getEquipoLocal().getNombre();
            nombreEVisitante = partido.getEquipoVisitante().getNombre();
            setTitle("Partido de " + nombreELocal + " " + nombreEVisitante);
        }

        JPanel panel = new JPanel();
        panel.setBounds(130, 25, 694, 100);
        contentPanel.add(panel);
        panel.setLayout(new BorderLayout());

        String[] columnasJugadores = {"Nombre", "Rol","Posicion"};
        String[] columnas = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[][] datos = {
                {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0"}
        };

        model = new DefaultTableModel(datos, columnas);
        modelLocal = new DefaultTableModel(columnasJugadores, 0);
        modelVisitante = new DefaultTableModel(columnasJugadores, 0);

        table = new JTable(model);
        table.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                try {
                    actualizarMarcador();
                } catch (Exception e) {
                    System.out.println("Error al actualizar el marcador: " + e.getMessage());
                }
            }
        });
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel("Total");
        lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblNewLabel.setBackground(Color.RED);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
        lblNewLabel.setBounds(823, 25, 77, 21);
        contentPanel.add(lblNewLabel);

        textField = new JTextField();
        textField.setBorder(new LineBorder(new Color(171, 173, 179)));
        textField.setBackground(Color.WHITE);
        textField.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setText("0");
        textField.setEditable(false);
        textField.setBounds(823, 46, 78, 32);
        contentPanel.add(textField);
        textField.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBorder(new LineBorder(new Color(171, 173, 179)));
        textField_2.setBackground(Color.WHITE);
        textField_2.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        textField_2.setText("0");
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(823, 77, 78, 32);
        contentPanel.add(textField_2);

        JLabel lblEquipo = new JLabel("Equipo");
        lblEquipo.setHorizontalAlignment(SwingConstants.CENTER);
        lblEquipo.setFont(new Font("Broadway", Font.PLAIN, 16));
        lblEquipo.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblEquipo.setBackground(Color.RED);
        lblEquipo.setBounds(53, 25, 77, 21);
        contentPanel.add(lblEquipo);

        textField_1 = new JTextField();
        textField_1.setText(nombreEVisitante);
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBorder(new LineBorder(new Color(171, 173, 179)));
        textField_1.setBackground(Color.WHITE);
        textField_1.setBounds(53, 46, 78, 32);
        contentPanel.add(textField_1);

        textField_3 = new JTextField();
        textField_3.setText(nombreELocal);
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBorder(new LineBorder(new Color(171, 173, 179)));
        textField_3.setBackground(Color.WHITE);
        textField_3.setBounds(53, 77, 78, 32);
        contentPanel.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setText("Visitante");
        textField_4.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        textField_4.setEditable(false);
        textField_4.setColumns(10);
        textField_4.setBorder(null);
        textField_4.setBackground(SystemColor.menu);
        textField_4.setBounds(157, 150, 78, 32);
        contentPanel.add(textField_4);

        txtLocal = new JTextField();
        txtLocal.setText("Local");
        txtLocal.setHorizontalAlignment(SwingConstants.CENTER);
        txtLocal.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        txtLocal.setEditable(false);
        txtLocal.setColumns(10);
        txtLocal.setBorder(null);
        txtLocal.setBackground(SystemColor.menu);
        txtLocal.setBounds(542, 150, 78, 32);
        contentPanel.add(txtLocal);

        
        JPanel panel_1 = new JPanel(new BorderLayout());
        panel_1.setBounds(53, 193, 314, 380);
        contentPanel.add(panel_1);

        tablaVisitante = new JTable(modelVisitante);
        JScrollPane scrollPaneVisitante = new JScrollPane(tablaVisitante);
        panel_1.add(scrollPaneVisitante, BorderLayout.CENTER);

        // Panel Local
        JPanel panel_2 = new JPanel(new BorderLayout());
        panel_2.setBounds(426, 193, 314, 380);
        contentPanel.add(panel_2);

        tablaLocal = new JTable(modelLocal);
        JScrollPane scrollPaneLocal = new JScrollPane(tablaLocal);
        panel_2.add(scrollPaneLocal, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("New button");
        buttonPane.add(btnNewButton);
        
        loadJugadores(partido);
    }

    private void actualizarMarcador() {
        int filas = table.getRowCount();
        int columnas = table.getColumnCount();

        for (int i = 0; i < filas; i++) {
            int total = 0;

            for (int j = 0; j < columnas; j++) {
                try {
                    int valor = Integer.parseInt(model.getValueAt(i, j).toString());
                    if (valor >= 0) {
                        total += valor;
                    } else {
                        model.setValueAt("0", i, j);
                    }
                } catch (NumberFormatException e) {
                    
                }
            }

            if (i == 0) {
                textField.setText(String.valueOf(total));
            } else {
                textField_2.setText(String.valueOf(total));
            }
        }
    }

    private void loadJugadores(Juego partido) {
    	
    	if (partido == null) {
    		return;
    	}
    	
		modelLocal.setRowCount(0);
		filaLocal = new Object[modelLocal.getColumnCount()];
		
		
		if (partido.getEquipoLocal() != null) {
			for (Jugador jugador : partido.getEquipoLocal().getMisJugadores()) {
				filaLocal[0] = jugador.getNombre();
				 if (jugador instanceof Lanzador) {
					 filaLocal[1] = "Lanzador";
					 filaLocal[2] = ((Lanzador)jugador).getRolLanzador();
				 }

				if (jugador instanceof JugadorPosicion) {
					filaLocal[1] = "Bateador";
					filaLocal[2] = ((JugadorPosicion)jugador).getPosicion();
				}
				modelLocal.addRow(filaLocal);
			}
		}
		
		modelVisitante.setRowCount(0);
		filaVisitante = new Object[modelLocal.getColumnCount()];
		if (partido.getEquipoVisitante() != null) {
			for (Jugador jugador : partido.getEquipoVisitante().getMisJugadores()) {
				filaVisitante[0] = jugador.getNombre();
				 if (jugador instanceof Lanzador) {
					 filaVisitante[1] = "Lanzador";
					 filaVisitante[2] = ((Lanzador)jugador).getRolLanzador();
				 }

				if (jugador instanceof JugadorPosicion) {
					filaVisitante[1] = "Bateador";
					filaVisitante[2] = ((JugadorPosicion)jugador).getPosicion();
				}
				modelVisitante.addRow(filaVisitante);
			}
		}
    }
}
