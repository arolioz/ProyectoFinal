package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Logico.Equipo;
import Logico.SerieNacional;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private static DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private Equipo miEquipo = null; 
	private JButton cancelButton;
	private static Object[] fila;
	private JPanel buttonPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoEquipo dialog = new ListadoEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEquipo() {
		
		setFont(new Font("Broadway", Font.BOLD, 16));
		getContentPane().setFont(new Font("Broadway", Font.PLAIN, 16));
		setTitle("Listado de Equipos");
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
					String[] headers = {"Id", "Nombre", "Ciudad", "Estadio", "Año fundado"};
					model.setColumnIdentifiers(headers);
					
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							int ind = table.getSelectedRow();
							if(ind != -1)
							{
								miEquipo = SerieNacional.getInstance().buscarEquipoDadoId(table.getValueAt(ind,  0).toString());
							}
						}
					});
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
		
		loadListadoEquipos();
	}

	private void loadListadoEquipos() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Equipo> aux = SerieNacional.getInstance().getMisEquipos();
		
		for (Equipo equipo : aux) {
			fila[0] = equipo.getId();
			fila[1] = equipo.getNombre();
			fila[2] = equipo.getCiudad();
			fila[3] = equipo.getEstadio();
			fila[4] = equipo.getAgnoFundado();	
			
			model.addRow(fila);
		}
	}
}
