package Logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Equipo implements Serializable {
	private String id;
	private String nombre;
	private String ciudad;
	private ArrayList<Jugador> misJugadores;
	private int numeroVictorias;
	private int numeroDerrotas;
	private String estadio;
	private Date agnoFundado;
	private int totalCarreras;
	private int cantJuegos;
	private static final long serialVersionUID = 1L;
	
	
	public Equipo(String id, String nombre, String ciudad,String estadio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.misJugadores = null;
		this.numeroVictorias = 0;
		this.numeroDerrotas = 0;
		this.estadio = estadio;
		this.agnoFundado = new Date();
		this.totalCarreras = 0;
		this.cantJuegos = 0;
		this.misJugadores = new ArrayList<Jugador>();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public ArrayList<Jugador> getMisJugadores() {
		return misJugadores;
	}
	public void setMisJugadores(ArrayList<Jugador> misJugadores) {
		this.misJugadores = misJugadores;
	}
	public int getNumeroVictorias() {
		return numeroVictorias;
	}
	public void setNumeroVictorias(int numeroVictorias) {
		this.numeroVictorias = numeroVictorias;
	}
	public int getNumeroDerrotas() {
		return numeroDerrotas;
	}
	public void setNumeroDerrotas(int numeroDerrotas) {
		this.numeroDerrotas = numeroDerrotas;
	}
	public String getEstadio() {
		return estadio;
	}
	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}
	public Date getAgnoFundado() {
		return agnoFundado;
	}
	public void setAgnoFundado(Date agnoFundado) {
		this.agnoFundado = agnoFundado;
	}
	public int getTotalCarreras() {
		return totalCarreras;
	}
	public void setTotalCarreras(int totalCarreras) {
		this.totalCarreras = totalCarreras;
	}
	public int getCantJuegos() {
		return cantJuegos;
	}
	public void setCantJuegos(int cantJuegos) {
		this.cantJuegos = cantJuegos;
	}
	public String getId() {
		return id;
	}
	
	public String toString() {
		return nombre;
	}

	public boolean actualizarEstadisticasJugadorPosicion(String idJugador, EstadisticaJugadorPosicion est) {
		for (Jugador jugador : misJugadores) {
			if (jugador.getIdJugador().equalsIgnoreCase(idJugador)) {
				if (jugador instanceof JugadorPosicion) {
					((JugadorPosicion) jugador).actualizarEstadisticas(est);
					return true;
				}
			}
		}
		return false;
		
	}
	
	public boolean actualizarEstadisticasLanzador(String idJugador, EstadisticaLanzador est) {
		for (Jugador jugador : misJugadores) {
			if (jugador.getIdJugador().equalsIgnoreCase(idJugador)) {
				if (jugador instanceof Lanzador) {
					((Lanzador) jugador).actualizarEstadisticas(est);
					return true;
				}
			}
		}
		return false;
		
	}
	
	
}
