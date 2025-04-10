package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Equipo;
import Logico.Juego;
import Logico.SerieNacional;
import java.awt.Toolkit;

public class ListarEstadisticaJuego extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private static DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton cancelButton;
	private static Object[] fila;
	private JPanel buttonPane;

	/*
	public static void main(String[] args) {
		try {
			ListarEstadisticaJuego dialog = new ListarEstadisticaJuego();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}

	*/
	public ListarEstadisticaJuego() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaDePosiciones.class.getResource("/Image/Logo.png")));

		setFont(new Font("Broadway", Font.BOLD, 16));
		getContentPane().setFont(new Font("Broadway", Font.PLAIN, 16));
		setTitle("Estadisticas de juego");
		setBounds(100, 100, 680, 326);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel();
					String[] headers = {"Id", "Equipo L", "Equipo V" , "Carreras L", "Carreras V", "Ganador", "Fecha Partido"}; 
					model.setColumnIdentifiers(headers);
					
					table = new JTable();
					
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadEquiposOrdenados();
	}
	

	private void loadEquiposOrdenados() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Juego> aux = SerieNacional.getInstance().getMisJuegos();

		
		for (Juego juego : aux) {
			if(juego.isJuegoTerminado()) {

				fila[0] = juego.getId();
				fila[1] = juego.getEquipoLocal().getNombre();
				fila[2] = juego.getEquipoVisitante().getNombre();
				fila[3] = String.valueOf(juego.getCarrerasEquipoLocal());
				fila[4] = String.valueOf(juego.getCarrerasEquipoVisitante());;
				fila[5] = juego.getGanadorJuego();
				fila[6] = juego.getFechaPartido();
				
				model.addRow(fila);
			}
	}
		
	}

}
