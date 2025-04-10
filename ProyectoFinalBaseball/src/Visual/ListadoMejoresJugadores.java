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
// import javax.swing.table.TableColumnModel;
import Logico.Jugador;
import Logico.JugadorPosicion;
import Logico.Lanzador;
import Logico.SerieNacional;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListadoMejoresJugadores extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static JTable tableEquipo;
    private static Object[] fila;
	private static DefaultTableModel model;

    private JRadioButton rdbLanzadores;
    private JRadioButton rdbBateadores;
    private JPanel panel_1;
    private JLabel lblNewLabel;
    /*

    public static void main(String[] args) {
        try {
        	ListadoMejoresJugadores dialog = new ListadoMejoresJugadores();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    public ListadoMejoresJugadores() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoMejoresJugadores.class.getResource("/Image/Logo.png")));
        setTitle("Listado de los mejores jugadores");
        setBounds(100, 100, 1004, 604);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        
        panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.setBounds(10, 11, 1018, 510);
        contentPanel.add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(209, 34, 534, 64);
        panel_1.add(panel_2);
        panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_2.setLayout(null);

        lblNewLabel = new JLabel("Filtro:");
        lblNewLabel.setFont(new Font("Broadway", Font.BOLD, 16));
        lblNewLabel.setBounds(52, 20, 87, 19);
        panel_2.add(lblNewLabel);
        

        rdbLanzadores = new JRadioButton("Lanzadores");
        rdbLanzadores.setFont(new Font("Times New Roman", Font.BOLD, 14));
        rdbLanzadores.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                int filtro = getSeleccionFiltro();
                loadJugadores(filtro);
            }
        });
        rdbLanzadores.setSelected(true);
        rdbLanzadores.setBounds(197, 19, 100, 23);
        panel_2.add(rdbLanzadores);

        rdbBateadores = new JRadioButton("Bateadores");
        rdbBateadores.setFont(new Font("Times New Roman", Font.BOLD, 14));
        rdbBateadores.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                int filtro = getSeleccionFiltro();
                loadJugadores(filtro);
            }
        });
        rdbBateadores.setBounds(352, 19, 100, 23);
        panel_2.add(rdbBateadores);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbLanzadores);
        group.add(rdbBateadores);

        model = new DefaultTableModel();
        String[] headers = {"Posicion", "Nombre", "Rol", "Equipo", "Efectividad"};
        model.setColumnIdentifiers(headers);
        
        tableEquipo = new JTable();
        tableEquipo.setBounds(10, 142, 953, 336);
        // panel_1.add(tableEquipo);
        tableEquipo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        tableEquipo.setFillsViewportHeight(true);
        tableEquipo.setBackground(Color.WHITE);
        tableEquipo.setFont(new Font("Broadway", Font.PLAIN, 16));
        tableEquipo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 142, 955, 336);
                        
        scrollPane.setViewportView(tableEquipo);
        panel_1.add(scrollPane);
        
        loadJugadores(1);
                        
        JPanel panel = new JPanel();
        scrollPane.setColumnHeaderView(panel);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        {
            JButton cancelButton = new JButton("Salir");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            cancelButton.setActionCommand("Cancel");
            buttonPane.add(cancelButton);
        }
    }

    private int getSeleccionFiltro() 
    {
        if (rdbLanzadores.isSelected()) 
        {
            return 1; 
        } 
        else 
        {
            return 2; 
        }
    }

    public static void loadJugadores(int filtro) 
    {
        model.setRowCount(0); 
        fila = new Object[model.getColumnCount()];


        if (filtro == 1) 
        {
        	String[] headers = {"Top 10", "Nombre", "Rol", "Equipo", "Efectividad"};
            model.setColumnIdentifiers(headers);
        	
        	loadMejoresLanzadores();
        }

        if (filtro == 2) 
        {
        	String[] headers = {"Top 10", "Nombre", "Posición", "Equipo", "Promedio"};
            model.setColumnIdentifiers(headers);
            
        	loadMejoresBateadores();
        }

        tableEquipo.getTableHeader().setReorderingAllowed(false);
        tableEquipo.setModel(model);
    }
    
    private static void loadMejoresLanzadores() 
    {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		ArrayList<Lanzador> misLanzadores = new ArrayList<>();
		ArrayList<Jugador> aux = SerieNacional.getInstance().getMisJugadores();
		
		for (Jugador jugador : aux) 
		{
			if(jugador instanceof Lanzador) 
			{
				misLanzadores.add((Lanzador) jugador);
			}
		}
		
		SerieNacional.getInstance().ordenarMejoresLanzadores(misLanzadores);
			
		int posicion = 1; 
		int ind = 0;
		int cant = misLanzadores.size();
		
		while (ind < cant && posicion <= 10 ) 
		{
			Lanzador lanzador = misLanzadores.get(ind);
			
			fila[0] = posicion;
			fila[1] = lanzador.getNombre()+ " " + lanzador.getApellido();
			fila[2] = lanzador.getRolLanzador();
			fila[3] = lanzador.getEquipo().getNombre();				
			fila[4] = lanzador.calcularEfectividad();
				
			model.addRow(fila);
				
			posicion++;
			ind++;
			
		}
	}
    
    private static void loadMejoresBateadores()
    {
    	model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		
		ArrayList<JugadorPosicion> misBateadores = new ArrayList<>();
		ArrayList<Jugador> aux = SerieNacional.getInstance().getMisJugadores();
		
		for (Jugador jugador : aux) 
		{
			if(jugador instanceof JugadorPosicion) 
			{
				misBateadores.add((JugadorPosicion) jugador);
			}
		}
		
		SerieNacional.getInstance().ordenarMejoresBateadores(misBateadores);
			
		int posicion = 1; 
		int ind = 0;
		int cant = misBateadores.size();
		
		while (ind < cant && posicion <= 10) 
		{
			JugadorPosicion bateador = misBateadores.get(ind); 
			
			fila[0] = posicion;
			fila[1] = bateador.getNombre()+ " " + bateador.getApellido();
			fila[2] = bateador.getPosicion();
			fila[3] = bateador.getEquipo().getNombre();
			fila[4] = bateador.calcularPromedio();
			
			model.addRow(fila);
			
			posicion++;
			ind++;
			
		}
    	
    }
}
