package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Logico.SerieNacional;

public class TablaDePosiciones extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private static DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton cancelButton;
	private static Object[] fila;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TablaDePosiciones dialog = new TablaDePosiciones();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TablaDePosiciones() {

		setFont(new Font("Broadway", Font.BOLD, 16));
		getContentPane().setFont(new Font("Broadway", Font.PLAIN, 16));
		setTitle("Tabla De Posiciones");
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
					String[] headers = {"Posición", "Nombre", "J" , "G", "P", "PCT", "DIF"}; 
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
				cancelButton = new JButton("Cancel");
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
		ArrayList<Equipo> aux = SerieNacional.getInstance().getMisEquipos();
		SerieNacional.getInstance().ordenarEquipos(aux);
		
		int posicion = 1; 
		
		for (Equipo equipo : aux) {
			float pct = SerieNacional.getInstance().calcularPorcentajeVictorias(equipo);
			float dif = SerieNacional.getInstance().calcularDif(equipo);
			
			fila[0] = posicion++;
			fila[1] = equipo.getNombre();
			fila[2] = equipo.getCantJuegos();
			fila[3] = equipo.getNumeroVictorias();
			fila[4] = equipo.getNumeroDerrotas();
			fila[5] = pct;
			fila[6] = dif;
			
			model.addRow(fila);
		}
	}

}
