package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Logico.Equipo;
import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;
import Logico.SerieNacional;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ListadoJugadores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tableEquipo;
	static DefaultTableModel tableModel;
	static Object[] fila;
	private JComboBox cmbEquipos;
	private JRadioButton rdbTodos;
	private JRadioButton rdbLanzador;
	private JRadioButton rdbBateador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoJugadores dialog = new ListadoJugadores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoJugadores() {
		setTitle("Listado de jugadores");
		setBounds(100, 100, 1054, 604);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 11, 1018, 510);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		{
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(116, 11, 780, 220);
			panel_1.add(panel_2);
			panel_2.setBorder(new LineBorder(Color.BLACK, 3));
			panel_2.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Equipo: ");
			lblNewLabel.setFont(new Font("Broadway", Font.PLAIN, 16));
			lblNewLabel.setBounds(26, 31, 87, 19);
			panel_2.add(lblNewLabel);
			
			cmbEquipos = new JComboBox();
			cmbEquipos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int filtro = 1;
					
					if (rdbTodos.isSelected()) {
						filtro = 1;
					}
					if (rdbLanzador.isSelected()) {
						filtro = 2;
					}
					if (rdbBateador.isSelected()) {
						filtro = 3;
					}
					
					if (cmbEquipos.getSelectedItem() instanceof Equipo) {
						String id = ((Equipo)(cmbEquipos.getSelectedItem())).getId();
						loadEquipo(id , filtro);
					}
					else {
						loadEquipo("Null", filtro);
						
					}
					
				}
			});
			cmbEquipos.setBackground(Color.WHITE);
			cmbEquipos.setForeground(Color.BLACK);
			cmbEquipos.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cmbEquipos.setModel(new DefaultComboBoxModel(new String[] {"<Todos>"}));
			cmbEquipos.setBounds(111, 32, 140, 20);
			cargarEquipo();
			panel_2.add(cmbEquipos);
			
			JLabel lblFiltro = new JLabel("Filtro:");
			lblFiltro.setFont(new Font("Broadway", Font.PLAIN, 16));
			lblFiltro.setBounds(26, 130, 87, 19);
			panel_2.add(lblFiltro);
			
			JLabel lblTodos = new JLabel("Todos");
			lblTodos.setFont(new Font("Broadway", Font.PLAIN, 16));
			lblTodos.setBounds(183, 102, 87, 19);
			panel_2.add(lblTodos);
			
			JLabel lblLanzador = new JLabel("Lanzador");
			lblLanzador.setFont(new Font("Broadway", Font.PLAIN, 16));
			lblLanzador.setBounds(324, 102, 104, 19);
			panel_2.add(lblLanzador);
			
			JLabel lblBateador = new JLabel("Bateador");
			lblBateador.setFont(new Font("Broadway", Font.PLAIN, 16));
			lblBateador.setBounds(503, 102, 94, 19);
			panel_2.add(lblBateador);
			
			rdbTodos = new JRadioButton("");
			rdbTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if (cmbEquipos.getSelectedItem() instanceof Equipo) {
						String id = ((Equipo)(cmbEquipos.getSelectedItem())).getId();
						loadEquipo(id , 1);
					}
					else {
						loadEquipo("Null", 1);
					}
					
				}
			});
			rdbTodos.setSelected(true);
			rdbTodos.setBounds(193, 130, 26, 23);
			panel_2.add(rdbTodos);
			
			rdbLanzador = new JRadioButton("");
			rdbLanzador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cmbEquipos.getSelectedItem() instanceof Equipo) {
						String id = ((Equipo)(cmbEquipos.getSelectedItem())).getId();
						loadEquipo(id , 2);
					}
					else {
						loadEquipo("Null", 2);
					}
					
				}
			});
			rdbLanzador.setBounds(353, 130, 26, 23);
			panel_2.add(rdbLanzador);
			
			rdbBateador = new JRadioButton("");
			rdbBateador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (cmbEquipos.getSelectedItem() instanceof Equipo) {
						String id = ((Equipo)(cmbEquipos.getSelectedItem())).getId();
						loadEquipo(id , 3);
					}
					else {
						loadEquipo("Null", 3);
					}
					
				}
			});
			rdbBateador.setBounds(532, 130, 26, 23);
			panel_2.add(rdbBateador);
			
			ButtonGroup group = new ButtonGroup();
			group.add(rdbTodos);
			group.add(rdbLanzador);
			group.add(rdbBateador);
			
		}
		
		tableEquipo = new JTable();
		tableEquipo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tableEquipo.setFillsViewportHeight(true);
		tableEquipo.setBackground(Color.WHITE);
		tableEquipo.setFont(new Font("Broadway", Font.PLAIN, 16));
		tableEquipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableModel = new DefaultTableModel();
		String[] columnNames = {"Id", "Nombre","Nacionalidad","Tipo","Rol","Equipo"};
		tableModel.setColumnIdentifiers(columnNames);
		loadEquipo("Null",1);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 241, 955, 258);
		panel_1.add(panel);
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(tableEquipo);
		scrollPane.setBounds(0, 0, 955, 258);
		panel.add(scrollPane);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static void loadEquipo(String idEquipo, int filtro) {
		Equipo equipo = SerieNacional.getInstance().buscarEquipoDadoId(idEquipo);
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		
		if (equipo == null) {
			for (Jugador jugador : SerieNacional.getInstance().getMisJugadores()) {
				 if (filtro == 1) {
					 fila[0] = jugador.getIdJugador();
					 fila[1] = jugador.getNombre();
					 fila[2] = jugador.getNacionalidad();
					 if (jugador instanceof Lanzador) {
						 fila[3] = "Lanzador";
						 fila[4] = ((Lanzador)jugador).getRolLanzador();
					 }
					 if (jugador instanceof JugadorPosicion) {
						 fila[3] = "Bateador";
						 fila[4] = ((JugadorPosicion)jugador).getPosicion();
					 }
					 fila[5] = jugador.getEquipo().getNombre();
					 tableModel.addRow(fila);
				 }
				 if (filtro == 2) {
					 if (jugador instanceof Lanzador) {
						 fila[0] = jugador.getIdJugador();
						 fila[1] = jugador.getNombre();
						 fila[2] = jugador.getNacionalidad();
						 fila[3] = "Lanzador";
						 fila[4] = ((Lanzador)jugador).getRolLanzador();
						 fila[5] = jugador.getEquipo().getNombre();
						 tableModel.addRow(fila);
					 }
				 }
				 if (filtro == 3) {
					 if (jugador instanceof JugadorPosicion) {
						 fila[0] = jugador.getIdJugador();
						 fila[1] = jugador.getNombre();
						 fila[2] = jugador.getNacionalidad();
						 fila[3] = "Bateador";
						 fila[4] = ((JugadorPosicion)jugador).getPosicion();
						 fila[5] = jugador.getEquipo().getNombre();
						 tableModel.addRow(fila);
					 }
				 }
			}
		}
		
		if (equipo != null) {
			for (Jugador jugador : equipo.getMisJugadores()) {
				if (filtro == 1) {
					 fila[0] = jugador.getIdJugador();
					 fila[1] = jugador.getNombre();
					 fila[2] = jugador.getNacionalidad();
					 if (jugador instanceof Lanzador) {
						 fila[3] = "Lanzador";
						 fila[4] = ((Lanzador)jugador).getRolLanzador();
					 }
					 if (jugador instanceof JugadorPosicion) {
						 fila[3] = "Bateador";
						 fila[4] = ((JugadorPosicion)jugador).getPosicion();
					 }
					 fila[5] = jugador.getEquipo().getNombre();
					 tableModel.addRow(fila);
				 }
				 if (filtro == 2) {
					 if (jugador instanceof Lanzador) {
						 fila[0] = jugador.getIdJugador();
						 fila[1] = jugador.getNombre();
						 fila[2] = jugador.getNacionalidad();
						 fila[3] = "Lanzador";
						 fila[4] = ((Lanzador)jugador).getRolLanzador();
						 fila[5] = jugador.getEquipo().getNombre();
						 tableModel.addRow(fila);
					 }
				 }
				 if (filtro == 3) {
					 if (jugador instanceof JugadorPosicion) {
						 fila[0] = jugador.getIdJugador();
						 fila[1] = jugador.getNombre();
						 fila[2] = jugador.getNacionalidad();
						 fila[3] = "Bateador";
						 fila[4] = ((JugadorPosicion)jugador).getPosicion();
						 fila[5] = jugador.getEquipo().getNombre();
						 tableModel.addRow(fila);
					 }
				 }
			}
		}
		tableEquipo.getTableHeader().setReorderingAllowed(false);
        tableEquipo.setModel(tableModel);
		
	}
	
	private void cargarEquipo() {
		for(Equipo equipo : SerieNacional.getInstance().getMisEquipos()) {
			cmbEquipos.addItem(equipo);
		}
		
	}
	
}
