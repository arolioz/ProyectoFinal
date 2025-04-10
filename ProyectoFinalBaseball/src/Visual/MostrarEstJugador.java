package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;
import Logico.EstadisticaJugadorPosicion;
import Logico.EstadisticaLanzador;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarEstJugador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel txtNombre;
	private JPanel panelBateador;
	private JPanel panelLanzador;
	private JTable tablaBateadores;
	private JTable tablaLanzadores;

	/*
	public static void main(String[] args) {
		try {
			
			MostrarEstJugador dialog = new MostrarEstJugador(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 */
	public MostrarEstJugador(Jugador jugador) {
		setTitle("Estadísticas de jugador");
		setBounds(100, 100, 967, 515);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 931, 421);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtNombre = new JLabel("Estadísticas de {NOMBRE}");
		txtNombre.setFont(new Font("Broadway", Font.PLAIN, 16));
		txtNombre.setBounds(363, 37, 255, 42);
		panel.add(txtNombre);

		if (jugador != null) {
			txtNombre.setText("Estadísticas de " + jugador.getNombre());
		}

	
		panelLanzador = new JPanel();
		panelLanzador.setVisible(false);
		panelLanzador.setBounds(58, 90, 832, 172);
		panel.add(panelLanzador);
		panelLanzador.setLayout(new BorderLayout());

		tablaLanzadores = new JTable();
		JScrollPane scrollPaneLanzador = new JScrollPane(tablaLanzadores);
		panelLanzador.add(scrollPaneLanzador, BorderLayout.CENTER);

		
		panelBateador = new JPanel();
		panelBateador.setVisible(false);
		panelBateador.setBounds(58, 90, 832, 172);
		panel.add(panelBateador);
		panelBateador.setLayout(new BorderLayout());

		tablaBateadores = new JTable();
		JScrollPane scrollPaneBateador = new JScrollPane(tablaBateadores);
		panelBateador.add(scrollPaneBateador, BorderLayout.CENTER);

	
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	
		cargarDatosPorDefecto();
		
		if (jugador instanceof Lanzador) {
			cargarEstadisticasLanzador( ((Lanzador)jugador).getEstadisticas());
			panelLanzador.setVisible(true);
			panelBateador.setVisible(false);
		}
		if (jugador instanceof JugadorPosicion) {
			cargarEstadisticaBateador(((JugadorPosicion)jugador).getEstadisticas());
			panelLanzador.setVisible(false);
			panelBateador.setVisible(true);
		}
	}

	private void cargarDatosPorDefecto() {
		String[] columnasEstBateadores = {"Estadísticas", "Cantidad"};
		String[][] datosEstBateadores = {
			{"Id jugador", "null"},
			{"Hits", "0"},
			{"Base por bolas", "0"},
			{"Home runs", "0"},
			{"Remolcadas", "0"},
			{"Carreras anotadas", "0"},
			{"Turnos al bate", "0"},
			{"Ponches", "0"},
			{"Errores", "0"}
		};

		String[] columnasEstLanzadores = {"Estadísticas", "Cantidad"};
		String[][] datosEstLanzadores = {
			{"Id jugador", "null"},
			{"Ponches", "0"},
			{"Strikes", "0"},
			{"Bolas", "0"},
			{"Hit by pitch", "0"},
			{"Base por Bolas", "0"},
			{"Carreras permitidas", "0"},
			{"Hits permitidos", "0"},
			{"Innings Lanzados", "0"},
			{"Errores", "0"}
		};

		DefaultTableModel modeloBateador = new DefaultTableModel(datosEstBateadores, columnasEstBateadores) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DefaultTableModel modeloLanzador = new DefaultTableModel(datosEstLanzadores, columnasEstLanzadores) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaBateadores.setModel(modeloBateador);
		tablaLanzadores.setModel(modeloLanzador);
	}
	
	private void cargarEstadisticaBateador(EstadisticaJugadorPosicion est) {
		String[] columnas = {"Estadística", "Valor"};
		String[][] datos = {
			{"Id jugador", est.getIdJugador()},
			{"Hits", String.valueOf(est.getHits())},
			{"Base por Bolas", String.valueOf(est.getBasePorBolas())},
			{"Home Runs", String.valueOf(est.getCantHomeruns())},
			{"Carreras Remolcadas", String.valueOf(est.getCarrerasRemolcadas())},
			{"Carreras Anotadas", String.valueOf(est.getCarrerasAnotadas())},
			{"Turnos al Bate", String.valueOf(est.getTurnosAlBate())},
			{"Ponches", String.valueOf(est.getPonches())},
			{"Errores", String.valueOf(est.getError())}
		};

		DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaBateadores.setModel(modelo);
	}

	private void cargarEstadisticasLanzador(EstadisticaLanzador est) {
		String[] columnas = {"Estadística", "Valor"};
		String[][] datos = {
			{"Id jugador", est.getIdJugador()},
			{"Ponches", String.valueOf(est.getPonches())},
			{"Strikes", String.valueOf(est.getStrikes())},
			{"Bolas", String.valueOf(est.getBolas())},
			{"Hit by Pitch", String.valueOf(est.getBateadoresGolpeados())},
			{"Base por Bolas", String.valueOf(est.getBasePorBolas())},
			{"Carreras Permitidas", String.valueOf(est.getCarrerasPermitidas())},
			{"Hits Permitidos", String.valueOf(est.getHitsPermitidos())},
			{"Innings Lanzados", String.valueOf(est.getInningsJugados())},
			{"Errores", String.valueOf(est.getError())}
		};

		DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaLanzadores.setModel(modelo);
	}
	
}
