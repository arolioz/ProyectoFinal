package Visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Juego;
import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;
import Logico.EstadisticaJugadorPosicion;
import Logico.EstadisticaLanzador;

import java.awt.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SimuladorJuego extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel model;
    private DefaultTableModel modelVisitante;
    private DefaultTableModel modelLocal;
    private DefaultTableModel modelEstBateador;
    private DefaultTableModel modelEstLanzador;
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
    private ArrayList<EstadisticaJugadorPosicion> estadisticasBateadores = new ArrayList<EstadisticaJugadorPosicion>();
    private ArrayList<EstadisticaLanzador> estadisticasLanzadores = new ArrayList<EstadisticaLanzador>();
    private JTable tablaEstBateadores;
    private JTextField txtEstadisticas;
    private JTable tablaEstLanzdores;
    private JPanel panelEstLanzadores;
    private JPanel panelEstBateadores;
    

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

        String[] columnasJugadores = {"Id","Nombre", "Rol","Posicion"};
        String[] columnas = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[][] datos = {
                {"0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0"}
        };
        
        String[] columnasEstBateadores = {"Estadisticas","Cantidad"};
        String[][] datosEstBateadores = {
        		{"Id jugador","null"},
        		{"Hits","0"},
        		{"Base por bolas","0"},
        		{"Home runs","0"},
        		{"Remolcadas","0"},
        		{"Carreras anotadas","0"},
        		{"Turnos al bate","0"},
        		{"Ponches","0"},
        		{"Errores","0"}
        		};

        String[] columnasEstLanzador = {"Estadisticas","Cantidad"};
        String[][] datosEstLanzadores = {
        		{"Id jugador","null"},
        		{"Ponches","0"},
        		{"Strikes","0"},
        		{"Bolas","0"},
        		{"Hit by pitch","0"},
        		{"Base por Bolas","0"},
        		{"carreras permitidas","0"},
        		{"Hits permitidos","0"},
        		{"Innings Lanzados","0"},
        		{"Errores","0"}
        		};
        

        
        model = new DefaultTableModel(datos, columnas);
        modelVisitante = new DefaultTableModel(columnasJugadores, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };


        modelLocal = new DefaultTableModel(columnasJugadores, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        
        modelEstBateador = new DefaultTableModel(datosEstBateadores, columnasEstBateadores) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; 
            }
        };
        modelEstLanzador = new DefaultTableModel(datosEstLanzadores, columnasEstLanzador) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; 
            }
        };
        
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
        tablaVisitante.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		tablaLocal.clearSelection();
        		txtEstadisticas.setText("Estadisticas de " + (modelVisitante.getValueAt(tablaVisitante.getSelectedRow(), 1).toString()));
        		
        		String id = modelVisitante.getValueAt(tablaVisitante.getSelectedRow(), 0).toString();
        		EstadisticaJugadorPosicion estBateador = buscarEstadisticaBatedorByIdJugador(id);
        		if (estBateador != null) {
        			panelEstBateadores.setVisible(true);
        			panelEstLanzadores.setVisible(false);
        			cargarEstadisticaBateador(estBateador);
        		}
        		else {
        			EstadisticaLanzador estLanzador = buscarEstadisticaLanzadorByIdJugador(id);
        			panelEstBateadores.setVisible(false);
        			panelEstLanzadores.setVisible(true);
        			cargarEstadisticasLanzador(estLanzador);
        		}
        		

        	}
        });
        tablaVisitante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaVisitante.setRowSelectionAllowed(true);
        tablaVisitante.setColumnSelectionAllowed(false);
        tablaVisitante.setCellSelectionEnabled(false);
        
        JScrollPane scrollPaneVisitante = new JScrollPane(tablaVisitante);
        panel_1.add(scrollPaneVisitante, BorderLayout.CENTER);

        // Panel Local
        JPanel panel_2 = new JPanel(new BorderLayout());
        panel_2.setBounds(426, 193, 314, 380);
        contentPanel.add(panel_2);

        tablaLocal = new JTable(modelLocal);
        tablaLocal.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		tablaVisitante.clearSelection();
        		txtEstadisticas.setText("Estadisticas de " + (modelLocal.getValueAt(tablaLocal.getSelectedRow(), 1).toString()));
        		
        		String id = modelLocal.getValueAt(tablaLocal.getSelectedRow(), 0).toString();
        		EstadisticaJugadorPosicion estBateador = buscarEstadisticaBatedorByIdJugador(id);
        		if (estBateador != null) {
        			panelEstBateadores.setVisible(true);
        			panelEstLanzadores.setVisible(false);
        			cargarEstadisticaBateador(estBateador);
        		}
        		else {
        			EstadisticaLanzador estLanzador = buscarEstadisticaLanzadorByIdJugador(id);
        			panelEstBateadores.setVisible(false);
        			panelEstLanzadores.setVisible(true);
        			cargarEstadisticasLanzador(estLanzador);
        		}
        		
        		
                tablaLocal.setSelectionBackground(Color.RED);
                tablaLocal.setSelectionForeground(Color.RED);
        	}
        });
        tablaLocal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaLocal.setRowSelectionAllowed(true);
        tablaLocal.setColumnSelectionAllowed(false);
        tablaLocal.setCellSelectionEnabled(false);
        
        
        JScrollPane scrollPaneLocal = new JScrollPane(tablaLocal);
        panel_2.add(scrollPaneLocal, BorderLayout.CENTER);
        
        panelEstBateadores = new JPanel(new BorderLayout());
        panelEstBateadores.setVisible(false);
        panelEstBateadores.setBounds(807, 193, 286, 156);
        contentPanel.add(panelEstBateadores);

        
        tablaEstBateadores = new JTable(modelEstBateador);
        tablaEstBateadores.addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent evt) {
                try {
                	
                    actualizarEstadisticasBateador(tablaEstBateadores.getValueAt(0, 1).toString());;
                } catch (Exception e) {
                    System.out.println("Ha ocurrido un error" + e.getMessage());
                }
        	}
        });
        JScrollPane scrollPaneEstadisticaBateador = new JScrollPane(tablaEstBateadores);
        panelEstBateadores.add(scrollPaneEstadisticaBateador, BorderLayout.CENTER);
        
        txtEstadisticas = new JTextField();
        txtEstadisticas.setText("Estadisticas");
        txtEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
        txtEstadisticas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
        txtEstadisticas.setEditable(false);
        txtEstadisticas.setColumns(10);
        txtEstadisticas.setBorder(null);
        txtEstadisticas.setBackground(SystemColor.menu);
        txtEstadisticas.setBounds(807, 150, 286, 32);
        contentPanel.add(txtEstadisticas);
        
        panelEstLanzadores = new JPanel((LayoutManager) null);
        panelEstLanzadores.setVisible(false);
        panelEstLanzadores.setBounds(807, 193, 286, 156);
        contentPanel.add(panelEstLanzadores);
        panelEstLanzadores.setLayout(new BorderLayout());
        
        tablaEstLanzdores = new JTable(modelEstLanzador);
        tablaEstLanzdores.addPropertyChangeListener(new PropertyChangeListener() {
        	public void propertyChange(PropertyChangeEvent arg0) {
                try {
              
                    actualizarEstadisticasLanzador(tablaEstLanzdores.getValueAt(0, 1).toString());;
                } catch (Exception e) {
                    System.out.println("Ha ocurrido un error " + e.getMessage());
                }
        	}
        });
        JScrollPane scrollPaneEstadisticaLanzador = new JScrollPane(tablaEstLanzdores);
        panelEstLanzadores.add(scrollPaneEstadisticaLanzador, BorderLayout.CENTER);
        


        JPanel buttonPane = new JPanel();
        buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("New button");
        buttonPane.add(btnNewButton);
        
        loadJugadores(partido);
        cargarEstadisticas(partido);
        for(int i = 0; i < estadisticasBateadores.size(); i++) {
        	System.out.println(estadisticasBateadores.get(i).getIdJugador());
        }

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
				filaLocal[0] = jugador.getIdJugador();
				filaLocal[1] = jugador.getNombre();
				 if (jugador instanceof Lanzador) {
					 filaLocal[2] = "Lanzador";
					 filaLocal[3] = ((Lanzador)jugador).getRolLanzador();
				 }

				if (jugador instanceof JugadorPosicion) {
					filaLocal[2] = "Bateador";
					filaLocal[3] = ((JugadorPosicion)jugador).getPosicion();
				}
				modelLocal.addRow(filaLocal);
			}
		}
		
		modelVisitante.setRowCount(0);
		filaVisitante = new Object[modelVisitante.getColumnCount()];
		if (partido.getEquipoVisitante() != null) {
			for (Jugador jugador : partido.getEquipoVisitante().getMisJugadores()) {
				filaVisitante[0] = jugador.getIdJugador();
				filaVisitante[1] = jugador.getNombre();
				 if (jugador instanceof Lanzador) {
					 filaVisitante[2] = "Lanzador";
					 filaVisitante[3] = ((Lanzador)jugador).getRolLanzador();
				 }

				if (jugador instanceof JugadorPosicion) {
					filaVisitante[2] = "Bateador";
					filaVisitante[3] = ((JugadorPosicion)jugador).getPosicion();
				}
				modelVisitante.addRow(filaVisitante);
			}
		}
    }
    
    private void cargarEstadisticas(Juego juego) {
    	if (juego == null) {
    		return;
    	}
    	
    	if (juego.getEquipoLocal() != null) {
    		for (Jugador jugador : juego.getEquipoLocal().getMisJugadores()) {
    			if (jugador instanceof JugadorPosicion) {
    				estadisticasBateadores.add(new EstadisticaJugadorPosicion(jugador.getIdJugador()));
    			}
    			if (jugador instanceof Lanzador) {
    				estadisticasLanzadores.add(new EstadisticaLanzador(jugador.getIdJugador()));
    			}
    		}
    	}
    	
    	if (juego.getEquipoVisitante() != null) {
    		for (Jugador jugador : juego.getEquipoVisitante().getMisJugadores()) {
    			if (jugador instanceof JugadorPosicion) {
    				estadisticasBateadores.add(new EstadisticaJugadorPosicion(jugador.getIdJugador()));
    			}
    			if (jugador instanceof Lanzador) {
    				estadisticasLanzadores.add(new EstadisticaLanzador(jugador.getIdJugador()));
    			}
    		}
    	}
    }
    
    private void cargarEstadisticaBateador(EstadisticaJugadorPosicion est) {
        
    	
        String[] columnas = {"Estadística", "Valor"};
        String[][] datos = {
        	{"Id jugador",String.valueOf(est.getIdJugador())},
            {"Hits", String.valueOf(est.getHits())},
            {"Base por Bolas (BB)", String.valueOf(est.getBasePorBolas())},
            {"Home Runs (HR)", String.valueOf(est.getCantHomeruns())},
            {"Carreras Remolcadas", String.valueOf(est.getCarrerasRemolcadas())},
            {"Carreras Anotadas", String.valueOf(est.getCarrerasAnotadas())},
            {"Turnos al Bate", String.valueOf(est.getTurnosAlBate())},
            {"Ponches", String.valueOf(est.getPonches())},
            {"Errores", String.valueOf(est.getError())}
        };

        DefaultTableModel modeloEst = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column != 0 && row != 0); 
                }
            };
        tablaEstBateadores.setModel(modeloEst);
    }
    
    
    private void cargarEstadisticasLanzador(EstadisticaLanzador est) {
    	
    	
        String[] columnas = {"Estadística", "Valor"};
        String[][] datos = {
        	{"Id jugador",String.valueOf(est.getIdJugador())},
            {"Ponches", String.valueOf(est.getPonches())},
            {"Strikes", String.valueOf(est.getStrikes())},
            {"Bolas", String.valueOf(est.getBolas())},
            {"Hit by pitch", String.valueOf(est.getBateadoresGolpeados())},
            {"Base por Bolas", String.valueOf(est.getBasePorBolas())},
            {"Carreras Permitidas", String.valueOf(est.getCarrerasPermitidas())},
            {"Hits Permitidos", String.valueOf(est.getHitsPermitidos())},
            {"Innings Lanzados", String.valueOf(est.getInningsJugados())},
            {"Errores", String.valueOf(est.getError())}
        };

        DefaultTableModel modeloEst = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column != 0 && row != 0); 
                }
            };
        tablaEstLanzdores.setModel(modeloEst);
    }
    
    private EstadisticaJugadorPosicion buscarEstadisticaBatedorByIdJugador(String id) {
    	EstadisticaJugadorPosicion est = null;
		for( EstadisticaJugadorPosicion aux : estadisticasBateadores) {
			if(aux.getIdJugador().equalsIgnoreCase(id)) {
				est = aux;
			}
		}
		return est;
    }
    
    private EstadisticaLanzador buscarEstadisticaLanzadorByIdJugador(String id) {
    	EstadisticaLanzador est = null;
		for( EstadisticaLanzador aux : estadisticasLanzadores) {
			if(aux.getIdJugador().equalsIgnoreCase(id)) {
				est = aux;
			}
		}
		return est;
    }
    
    private void actualizarEstadisticasBateador(String id) {
        for (int i = 0; i < estadisticasBateadores.size(); i++) {
            EstadisticaJugadorPosicion est = estadisticasBateadores.get(i);
            if (est.getIdJugador().equalsIgnoreCase(id)) {

                try {
                    
                    int hits = Integer.parseInt(tablaEstBateadores.getValueAt(1, 1).toString()); 
                    int basePorBolas = Integer.parseInt(tablaEstBateadores.getValueAt(2, 1).toString()); 
                    int homeRuns = Integer.parseInt(tablaEstBateadores.getValueAt(3, 1).toString()); 
                    int remolcadas = Integer.parseInt(tablaEstBateadores.getValueAt(4, 1).toString()); 
                    int carrerasAnotadas = Integer.parseInt(tablaEstBateadores.getValueAt(5, 1).toString()); 
                    int turnosAlBate = Integer.parseInt(tablaEstBateadores.getValueAt(6, 1).toString());
                    int ponches = Integer.parseInt(tablaEstBateadores.getValueAt(7, 1).toString()); 
                    int errores = Integer.parseInt(tablaEstBateadores.getValueAt(8, 1).toString()); 

                    est.setHits(hits);
                    est.setBasePorBolas(basePorBolas);
                    est.setCantHomeruns(homeRuns);
                    est.setCarrerasRemolcadas(remolcadas);
                    est.setCarrerasAnotadas(carrerasAnotadas);
                    est.setTurnosAlBate(turnosAlBate);
                    est.setPonches(ponches);
                    est.setError(errores);
                    
                    return; 
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir los datos de la tabla a enteros: " + e.getMessage());
                }
            }
        }
        return;
    }
    
    private void actualizarEstadisticasLanzador(String id) {
        for (int i = 0; i < estadisticasLanzadores.size(); i++) {
            EstadisticaLanzador est = estadisticasLanzadores.get(i);
            if (est.getIdJugador().equalsIgnoreCase(id)) {
                try {
                   
                   
                    int ponches = Integer.parseInt(tablaEstLanzdores.getValueAt(1, 1).toString()); 
                    int strikes = Integer.parseInt(tablaEstLanzdores.getValueAt(2, 1).toString()); 
                    int bolas = Integer.parseInt(tablaEstLanzdores.getValueAt(3, 1).toString()); 
                    int bateadoresGolpeados = Integer.parseInt(tablaEstLanzdores.getValueAt(4, 1).toString());
                    int basePorBolas = Integer.parseInt(tablaEstLanzdores.getValueAt(5, 1).toString()); 
                    int carrerasPermitidas = Integer.parseInt(tablaEstLanzdores.getValueAt(6, 1).toString()); 
                    int hitsPermitidos = Integer.parseInt(tablaEstLanzdores.getValueAt(7, 1).toString()); 
                    int inningsJugados = Integer.parseInt(tablaEstLanzdores.getValueAt(8, 1).toString()); 
                    int error = Integer.parseInt(tablaEstLanzdores.getValueAt(9, 1).toString()); 

                    
                   
                    est.setPonches(ponches);
                    est.setStrikes(strikes);
                    est.setBolas(bolas);
                    est.setBateadoresGolpeados(bateadoresGolpeados);
                    est.setBasePorBolas(basePorBolas);
                    est.setCarrerasPermitidas(carrerasPermitidas);
                    est.setHitsPermitidos(hitsPermitidos);
                    est.setInningsJugados(inningsJugados);
                    est.setError(error);
                } catch (NumberFormatException e) {
                    System.out.println("Error " + e.getMessage());
                }
                return; 
            }
        }
        return;
    }

}
