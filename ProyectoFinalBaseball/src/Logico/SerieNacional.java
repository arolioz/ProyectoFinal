package Logico;

import java.time.LocalDate;
import java.util.ArrayList;



public class SerieNacional {
	private ArrayList<Equipo> misEquipos;
	private ArrayList<Jugador> misJugadores;
	private ArrayList<Juego> misJuegos;
	private ArrayList<Lesion> misLesiones;
	
	private static int generadorEquipos = 1;
	private static int generadorJugadores = 1;
	private static int generadorJuegos = 1;
	private static int generadorLesiones = 1;
	
	private static SerieNacional serieNacional = null;
	private LocalDate fechaActual = LocalDate.now();
	
	private SerieNacional() {
		super();
		
		misEquipos = new ArrayList<Equipo>();
		misJugadores = new ArrayList<Jugador>();
		misJuegos = new ArrayList<Juego>();
		misLesiones = new ArrayList<Lesion>();
		
		generadorEquipos = 1;
		generadorJugadores = 1;
		generadorJuegos = 1;
		generadorLesiones = 1;
	}
	
	
	public static SerieNacional getInstance() {
		if (serieNacional == null) {
			serieNacional = new SerieNacional();
		}
		return serieNacional;
	}


	public ArrayList<Equipo> getMisEquipos() {
		return misEquipos;
	}


	public ArrayList<Jugador> getMisJugadores() {
		return misJugadores;
	}


	public ArrayList<Juego> getMisJuegos() {
		return misJuegos;
	}


	public ArrayList<Lesion> getMisLesiones() {
		return misLesiones;
	}


	public static int getGeneradorEquipos() {
		return generadorEquipos;
	}


	public static int getGeneradorJugadores() {
		return generadorJugadores;
	}


	public static int getGeneradorJuegos() {
		return generadorJuegos;
	}


	public static int getGeneradorLesiones() {
		return generadorLesiones;
	}
	
	public void ingresarJugadorPosicion(JugadorPosicion jugador) {
		misJugadores.add(jugador);
		Equipo equipo = buscarEquipoDadoId(jugador.getEquipo().getId());
		equipo.getMisJugadores().add(jugador);
		
		generadorJugadores++;
	}
	
	public void ingresarLanzador(Lanzador jugador) {
		misJugadores.add(jugador);
		Equipo equipo = buscarEquipoDadoId(jugador.getEquipo().getId());
		equipo.getMisJugadores().add(jugador);
		
		generadorJugadores++;
	}

	
	public void ingresarEquipo(Equipo equipo) {
		misEquipos.add(equipo);
		generadorEquipos++;
	}
	
	public void ingresarLesion(Lesion lesion) {
		misLesiones.add(lesion);
		lesion.getJugador().setEstaLesionado(true);
		generadorLesiones++;
	}
	
	public void ingresarJuego(Juego juego) {
		misJuegos.add(juego);
		generadorJuegos++;
	}
	
	public void eliminarEquipo(Equipo equipo) {
		misEquipos.remove(equipo);
	}
	
	public void eliminarJugador(Jugador jugador) {
		misJugadores.remove(jugador);
	}
	
	public Jugador buscarjugadorDadoId(String id){
		Jugador jugador = null;
		for (Jugador aux : misJugadores) {
			if(aux.getIdJugador().equalsIgnoreCase(id)){
				jugador = aux;
			}
		}
		return jugador;
	}
	
	public Equipo buscarEquipoDadoId(String id){
		Equipo equipo = null;
		for( Equipo aux : misEquipos) {
			if(aux.getId().equalsIgnoreCase(id)) {
				equipo = aux;
			}
		}
		return equipo;
	}
	
	public Juego buscarJuegoDadoId(String id){
		Juego juego = null;
		for( Juego aux : misJuegos) {
			if(aux.getId().equalsIgnoreCase(id)) {
				juego = aux;
			}
		}
		return juego;
	}
	
	public Lesion buscarLesionDadoId(String id)
	{
		Lesion lesion = null;
		for (Lesion aux : misLesiones) {
			if(aux.getId().equalsIgnoreCase(id)) {
				lesion = aux;
			}
		}
		return lesion;
	}
	
	public void crearJuego(Equipo equipoLocal,Equipo equipoVisitante) {
		
		Juego juego = new Juego(String.valueOf(getGeneradorJuegos()), equipoLocal, equipoVisitante, fechaActual);
		fechaActual = fechaActual.plusWeeks(1);
		misJuegos.add(juego);
	}
	
	public void crearTorneo(ArrayList<Equipo> misEquipos) {
		for(int i = 0; i < misEquipos.size();i++) {
			for(int j = 0; j < misEquipos.size();j++) {
				if(i != j) {
					crearJuego(misEquipos.get(i), misEquipos.get(j));
					generadorJuegos++;
					System.out.println(i+""+j);
					
				}
			}
		}
	}


	public void ordenarEquipos(ArrayList<Equipo> misEquipos) {
		int n = misEquipos.size();
		
		for(int ind = 0; ind < n - 1; ind++){
			for(int ind2 = 0; ind2 < n - ind - 1; ind2++){
				
				Equipo actual = misEquipos.get(ind2);
				Equipo sig = misEquipos.get(ind2 + 1);
				
				if(actual.getNumeroVictorias() < sig.getNumeroVictorias())
				{
					misEquipos.set(ind2, sig);
					misEquipos.set(ind2+1, actual);
				}
			}
		}
	}


	public float calcularPorcentajeVictorias(Equipo equipo) {
		if(equipo.getCantJuegos() == 0)
		{
			return 0;
		}
		return (float) equipo.getNumeroVictorias() / equipo.getCantJuegos();
	}


	public float calcularDif(Equipo equipo) {
		Equipo mejor = misEquipos.get(0);
		int victoriasMejor = mejor.getNumeroVictorias();
		int derrotasMejor = mejor.getNumeroDerrotas();
		
		int victoriasEquipo = equipo.getNumeroVictorias();
		int derrotasEquipo = equipo.getNumeroDerrotas();
		
		int difVictorias = victoriasMejor - victoriasEquipo;
		int difDerrotas = derrotasEquipo - derrotasMejor;
		
		return (float)(difVictorias + difDerrotas) / 2 ;
	}
	
	
} 
