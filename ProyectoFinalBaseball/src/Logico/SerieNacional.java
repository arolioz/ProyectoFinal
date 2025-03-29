package Logico;

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
		generadorJugadores++;
	}
	
	public void ingresarLanzador(Lanzador jugador) {
		misJugadores.add(jugador);
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
	
} 
