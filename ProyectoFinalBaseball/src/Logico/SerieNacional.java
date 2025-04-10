package Logico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

import Excepcion.UsuarioYaExisteException;



public class SerieNacional implements Serializable 
{
	private ArrayList<Equipo> misEquipos;
	private ArrayList<Jugador> misJugadores;
	private ArrayList<Juego> misJuegos;
	private ArrayList<Lesion> misLesiones;

	private int generadorEquipos = 1;
	private int generadorJugadores = 1;
	private int generadorJuegos = 1;
	private int generadorLesiones = 1; 

	private static SerieNacional serieNacional = null;
	private LocalDate fechaActual = LocalDate.now();

	private ArrayList<User> misUsers;
	private static User loginUser;

	private static final long serialVersionUID = 1L;

	private SerieNacional() {
		super();

		misEquipos = new ArrayList<Equipo>();
		misJugadores = new ArrayList<Jugador>();
		misJuegos = new ArrayList<Juego>();
		misLesiones = new ArrayList<Lesion>();

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

	public Jugador buscarJugadorDadoId(String id){
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


	public void ordenarEquipos(ArrayList<Equipo> misEquipos) 
	{
		int n = misEquipos.size();

		for(int ind = 0; ind < n - 1; ind++)
		{
			for(int ind2 = 0; ind2 < n - ind - 1; ind2++)
			{

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

	public void ordenarMejoresLanzadores(ArrayList<Lanzador> misLanzadores) 
	{
		int n = misLanzadores.size();

		for(int ind = 0; ind < n - 1; ind++)
		{
			for(int ind2 = 0; ind2 < n - ind - 1; ind2++)
			{

				Lanzador actual = misLanzadores.get(ind2);
				Lanzador sig = misLanzadores.get(ind2+1);

				if(actual.calcularEfectividad() > sig.calcularEfectividad())
				{
					misLanzadores.set(ind2, sig);
					misLanzadores.set(ind2+1, actual);
				}
			}
		}

	}

	public void ordenarMejoresBateadores(ArrayList<JugadorPosicion> misBateadores)
	{
		int n = misBateadores.size();

		for (int i = 0; i < n - 1; i++) 
		{
			for (int j = 0; j < n - i - 1; j++) 
			{
				JugadorPosicion actual = misBateadores.get(j);
				JugadorPosicion siguiente = misBateadores.get(j + 1);

				if (actual.getPromedio() < siguiente.getPromedio()) 
				{
					misBateadores.set(j, siguiente);
					misBateadores.set(j + 1, actual);
				}
			}
		}
	}




	public float calcularPorcentajeVictorias(Equipo equipo) 
	{
		if(equipo.getCantJuegos() == 0)
		{
			return 0;
		}
		return (float) equipo.getNumeroVictorias() / equipo.getCantJuegos();
	}


	public float calcularDif(Equipo equipo) 
	{
		Equipo mejor = misEquipos.get(0);
		int victoriasMejor = mejor.getNumeroVictorias();
		int derrotasMejor = mejor.getNumeroDerrotas();

		int victoriasEquipo = equipo.getNumeroVictorias();
		int derrotasEquipo = equipo.getNumeroDerrotas();

		int difVictorias = victoriasMejor - victoriasEquipo;
		int difDerrotas = derrotasEquipo - derrotasMejor;

		return (float)(difVictorias + difDerrotas) / 2 ;
	}


	public static SerieNacional getSerieNacional() {
		return serieNacional;
	}


	public static void setSerieNacional(SerieNacional serieNacional) {
		SerieNacional.serieNacional = serieNacional;
	}


	public void actualizarEstBateadores(ArrayList<EstadisticaJugadorPosicion> estadisticasJuego) {
		for (EstadisticaJugadorPosicion aux : estadisticasJuego) {
			Jugador bateador = buscarJugadorDadoId(aux.getIdJugador());
			if (bateador instanceof JugadorPosicion) {
				Equipo equipoJugador = buscarEquipoDadoId(bateador.getEquipo().getId());
				((JugadorPosicion) bateador).actualizarEstadisticas(aux);
				equipoJugador.actualizarEstadisticasJugadorPosicion(bateador.getIdJugador(), aux);
			}
		}
	}	

	public void actualizarEstLanzadores(ArrayList<EstadisticaLanzador> estadisticasJuego) {
		for (EstadisticaLanzador aux : estadisticasJuego) {
			Jugador lanzador = buscarJugadorDadoId(aux.getIdJugador());
			if (lanzador instanceof Lanzador) {
				Equipo equipoJugador = buscarEquipoDadoId(lanzador.getEquipo().getId());
				((Lanzador) lanzador).actualizarEstadisticas(aux);
				equipoJugador.actualizarEstadisticasLanzador(lanzador.getIdJugador(), aux);
			}

		}
	}

	public void terminarPartido(Juego juego) {
		Juego partido = buscarJuegoDadoId(juego.getId());
		if (partido != null && !partido.isJuegoTerminado()) {
			partido.setCarrerasEquipoLocal(juego.getCarrerasEquipoLocal());
			partido.setCarrerasEquipoVisitante(juego.getCarrerasEquipoVisitante());
			partido.setJuegoTerminado(true);

			Equipo local = buscarEquipoDadoId(juego.getEquipoLocal().getId());
			Equipo visitante = buscarEquipoDadoId(juego.getEquipoVisitante().getId());
			local.setCantJuegos(local.getCantJuegos() + 1);
			visitante.setCantJuegos(visitante.getCantJuegos() + 1);

			if (partido.getGanadorJuego() != null) {
				if (partido.getGanadorJuego().equalsIgnoreCase(local.getNombre())) {
					local.setNumeroVictorias(local.getNumeroVictorias() + 1);
					visitante.setNumeroDerrotas(visitante.getNumeroDerrotas() + 1);
				}
				else if (partido.getGanadorJuego().equalsIgnoreCase(visitante.getNombre())) {
					visitante.setNumeroVictorias(visitante.getNumeroVictorias() + 1);
					local.setNumeroDerrotas(local.getNumeroDerrotas() + 1);
				}
			}
		}
	}

	public void terminarTroneo() {
		vaciarJuegos();
		Equipo ganador = buscarGanadorDelTorneo();
		ganador.aumentarTorneosGanados();
		reiniciarJuegosEquipos();
	}

	public void vaciarJuegos() {
		misJuegos.clear();
		generadorJuegos = 1;
		fechaActual = LocalDate.now(); 
	}

	public Equipo buscarGanadorDelTorneo() {
		Equipo aux = misEquipos.get(0);
		for (int i = 0; i < misEquipos.size(); i++) {
			if (misEquipos.get(i).getNumeroVictorias() > aux.getNumeroVictorias()) {
				aux = misEquipos.get(i);
			}
		}
		return aux;
	}

	public void reiniciarJuegosEquipos() {
		for (Equipo equipo : misEquipos) {
			equipo.setNumeroDerrotas(0);
			equipo.setNumeroVictorias(0);
			equipo.setCantJuegos(0);
		}
	}



	public ArrayList<User> getMisUsers() {
		return misUsers;
	}

	public void setMisUsers(ArrayList<User> misUsers) {
		this.misUsers = misUsers;
	}


	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		SerieNacional.loginUser = loginUser;
	}

	public void regUser(User user) throws UsuarioYaExisteException {

		for(User u : misUsers)
		{
			if(u.getUserName().equalsIgnoreCase(user.getUserName())) 
			{
				throw new UsuarioYaExisteException("Ya existe un usuario con ese nombre.");
			}
		}

		misUsers.add(user);

	}

	public int getGeneradorEquipos() {
		return generadorEquipos;
	}


	public void setGeneradorEquipos(int generadorEquipos) {
		this.generadorEquipos = generadorEquipos;
	}


	public int getGeneradorJugadores() {
		return generadorJugadores;
	}


	public void setGeneradorJugadores(int generadorJugadores) {
		this.generadorJugadores = generadorJugadores;
	}


	public int getGeneradorJuegos() {
		return generadorJuegos;
	}


	public void setGeneradorJuegos(int generadorJuegos) {
		this.generadorJuegos = generadorJuegos;
	}


	public int getGeneradorLesiones() {
		return generadorLesiones;
	}


	public void setGeneradorLesiones(int generadorLesiones) {
		this.generadorLesiones = generadorLesiones;
	}







} 
