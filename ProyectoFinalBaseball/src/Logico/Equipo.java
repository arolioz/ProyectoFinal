package Logico;

import java.util.ArrayList;

public class Equipo {
	private String id;
	private String nombre;
	private String ciudad;
	private ArrayList<Jugador> misJugadores;
	private int numeroVictorias;
	private int numeroDerrotas;
	private String estadio;
	private int agnoFundado;
	private int totalCarreras;
	private int cantJuegos;
	
	
	public Equipo(String id, String nombre, String ciudad, ArrayList<Jugador> misJugadores, int numeroVictorias,
			int numeroDerrotas, String estadio, int agnoFundado, int totalCarreras, int cantJuegos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.misJugadores = misJugadores;
		this.numeroVictorias = numeroVictorias;
		this.numeroDerrotas = numeroDerrotas;
		this.estadio = estadio;
		this.agnoFundado = agnoFundado;
		this.totalCarreras = totalCarreras;
		this.cantJuegos = cantJuegos;
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
	public int getAgnoFundado() {
		return agnoFundado;
	}
	public void setAgnoFundado(int agnoFundado) {
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
	
	
}
