package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import Logico.Equipo;
import Logico.SerieNacional;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Toolkit;

public class SeleccionarEquipo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tableEquipo;
	private JTable table_1;
	private static JTable tableCarrito;
	static DefaultTableModel tableModel1;
	static DefaultTableModel tableModel;
	static Object[] fila1;
	static Object[] fila;
	Equipo selectEquipo1 = null;
	Equipo selectEquipo2 = null;
	ArrayList<Equipo> tempEquipo = new ArrayList<Equipo>(SerieNacional.getInstance().getMisEquipos());;
	ArrayList<Equipo> tempCarrito = new ArrayList<Equipo>();
	private JButton btnEquipo;
	private JButton btnCarrito;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SeleccionarEquipo dialog = new SeleccionarEquipo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SeleccionarEquipo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SeleccionarEquipo.class.getResource("/Image/Logo.png")));		
		setTitle("Seleccionar equipo");
		setBounds(100, 100, 742, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(39, 51, 251, 172);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			tableEquipo = new JTable();
			tableEquipo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String codigo;
					if(tableEquipo.getSelectedRow() != -1){
						btnCarrito.setEnabled(true);
						codigo = (String) tableEquipo.getValueAt(tableEquipo.getSelectedRow(), 0);
						selectEquipo1 = SerieNacional.getInstance().buscarEquipoDadoId(codigo);

					}
				}
			});
			
			scrollPane.setViewportView(tableEquipo);
			tableModel = new DefaultTableModel();
			String[] columnNames = {"Id","Nombre","Ciudad","Estadio"};
			tableModel.setColumnIdentifiers(columnNames);
			tableEquipo.setModel(tableModel);
			
			JLabel lblNewLabel = new JLabel("Equipos");
			lblNewLabel.setBounds(39, 26, 66, 14);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Equipos serie nacional");
			lblNewLabel_1.setBounds(399, 26, 142, 14);
			panel.add(lblNewLabel_1);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(399, 51, 251, 172);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, BorderLayout.CENTER);
			
			tableCarrito = new JTable();
			tableCarrito.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					String codigo;
					if(tableCarrito.getSelectedRow()!= -1){
						btnEquipo.setEnabled(true);
						codigo = (String) tableCarrito.getValueAt(tableCarrito.getSelectedRow(), 0);
						selectEquipo2 = buscarEquipoTempDadoId(codigo);
					}
				}
			});
			scrollPane_1.setViewportView(tableCarrito);
			tableModel1 = new DefaultTableModel();
			String[] columnNames1 = {"Id","Nombre","Ciudad","Estadio"};			
			tableModel1.setColumnIdentifiers(columnNames1);
			tableCarrito.setModel(tableModel1);
			
			btnCarrito = new JButton(">");
			btnCarrito.setEnabled(false);
			btnCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selectEquipo1 != null) {
			            tempEquipo.remove(selectEquipo1);
			            tempCarrito.add(selectEquipo1);
			            cargarEquipos(tempEquipo);
			            cargarCarrito(tempCarrito);
			            selectEquipo1 = null;
			            btnCarrito.setEnabled(false);
			        }
				}
			});
			btnCarrito.setBounds(300, 102, 89, 23);
			panel.add(btnCarrito);
			
			btnEquipo = new JButton("<");
			btnEquipo.setEnabled(false);
			btnEquipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selectEquipo2 != null) {
			            tempCarrito.remove(selectEquipo2);
			            tempEquipo.add(selectEquipo2);
			            cargarEquipos(tempEquipo);
			            cargarCarrito(tempCarrito);
			            selectEquipo2 = null;
			            btnEquipo.setEnabled(false);
			        }
				}
			});
			btnEquipo.setBounds(300, 136, 89, 23);
			panel.add(btnEquipo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
										
						SerieNacional.getInstance().crearTorneo(tempCarrito);
						JOptionPane.showMessageDialog(null, "Se ha creado el torneo", "Exito", JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarEquipos(tempEquipo);
		
	}
	
	private static void cargarEquipos(ArrayList<Equipo> tempEquipo){
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];


			for (Equipo aux : tempEquipo) {
				fila[0] = aux.getId();
				fila[1] = aux.getNombre();
				fila[2] = aux.getCiudad();
				fila[3] = aux.getEstadio();
				tableModel.addRow(fila);
			}


		tableEquipo.setModel(tableModel);
		tableEquipo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEquipo.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = tableEquipo.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(70);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(70);
		columnModel.getColumn(3).setPreferredWidth(70);


	}

	private static void cargarCarrito(ArrayList<Equipo> tempEquipo){
		tableModel1.setRowCount(0);
		fila1 = new Object[tableModel1.getColumnCount()];

		for (Equipo aux : tempEquipo) {
			fila1[0] = aux.getId();
			fila1[1] = aux.getNombre();
			fila1[2] = aux.getCiudad();
			fila1[3] = aux.getEstadio();
			tableModel1.addRow(fila1);
		}


		tableCarrito.setModel(tableModel1);
		tableCarrito.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCarrito.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel1 = tableCarrito.getColumnModel();
		columnModel1.getColumn(0).setPreferredWidth(70);
		columnModel1.getColumn(1).setPreferredWidth(180);
		columnModel1.getColumn(2).setPreferredWidth(70);
		columnModel1.getColumn(3).setPreferredWidth(70); 


	}
		
	private Equipo buscarEquipoTempDadoId(String id){
		Equipo equipo = null;
		for( Equipo aux : tempCarrito) {
			if(aux.getId().equalsIgnoreCase(id)) {
				equipo = aux;
			}
		}
		return equipo;
	}
	
	private void clear() {
		tempCarrito.clear();
		cargarCarrito(tempCarrito);
	}
	
	
}
