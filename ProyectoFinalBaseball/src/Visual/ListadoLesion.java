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
import Logico.Lesion;
import Logico.SerieNacional;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListadoLesion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private static DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable table;
	private Lesion miLesion = null; 
	private JButton cancelButton;
	private static Object[] fila;
	private JPanel buttonPane;
	private JButton btnNewButton;
	
	/*

	public static void main(String[] args) {
		try {
			ListadoEquipo dialog = new ListadoEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/


	public ListadoLesion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoLesion.class.getResource("/Image/Logo.png")));
		
		setFont(new Font("Broadway", Font.BOLD, 16));
		getContentPane().setFont(new Font("Broadway", Font.PLAIN, 16));
		setTitle("Listado de Lesiones");
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
					String[] headers = {"Id", "Jugador", "Juego", "Tipo de lesión", "Fecha", "Reincorporación", "Descripción"};
					model.setColumnIdentifiers(headers);
					
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							int ind = table.getSelectedRow();
							if(ind != -1)
							{
								miLesion = SerieNacional.getInstance().buscarLesionDadoId(table.getValueAt(ind, 0).toString());
								
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
				cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnNewButton = new JButton("Prorroga");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							
						}
					});
					buttonPane.add(btnNewButton);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		loadListadoLesiones();
	}

	private void loadListadoLesiones() {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<Lesion> aux = SerieNacional.getInstance().getMisLesiones();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		
		for (Lesion lesion : aux) {
			String fechaLesion = formatoFecha.format(lesion.getFechaLesion());
			String fechaReincorporacion = formatoFecha.format(lesion.getFechaReincorporacion());
			fila[0] = lesion.getId();
			fila[1] = lesion.getJugador().getIdJugador();
			fila[2] = lesion.getJuego().getId();
			fila[3] = lesion.getTipoLesion();
			fila[4] = fechaLesion;
			fila[5] = fechaReincorporacion;
			fila[6] = lesion.getDescripcion();
			
			model.addRow(fila);
		}
	}
	
	
	
}
