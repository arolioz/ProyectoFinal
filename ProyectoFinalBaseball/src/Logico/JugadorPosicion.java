package Logico;

import java.util.Date;

public class JugadorPosicion extends Jugador {
	private String posicion;
	private EstadisticaJugadorPosicion estadisticas;
	private float promedio;
	private static final long serialVersionUID = 1L;
	
	
	public JugadorPosicion(String idJugador, String nombre, String apellido, Date fechaNacimiento, String telefono,
			String direccion, String nacionalidad, String correoElectronico, int numCamiseta, Equipo equipo, float peso,
			float altura, String ladoDominante, String posicion
			) {
		
		super(idJugador, nombre, apellido, fechaNacimiento, telefono, direccion, nacionalidad, correoElectronico,
				numCamiseta, equipo, peso, altura, ladoDominante);
		
		this.posicion = posicion;
		this.estadisticas = new EstadisticaJugadorPosicion(idJugador);
		this.promedio = 0;
	}


	public String getPosicion() {
		return posicion;
	}


	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}


	public EstadisticaJugadorPosicion getEstadisticas() {
		return estadisticas;
	}


	public void setEstadisticas(EstadisticaJugadorPosicion estadisticas) {
		this.estadisticas = estadisticas;
	}


	public float getPromedio() {
		return promedio;
	}


	public void setPromedio(float promedio) {
		this.promedio = promedio;
	}
	
	
	public float calcularPromedio() {
		if (estadisticas.getTurnosAlBate() == 0) {
			return 0;
		}
		float promedio = ((float) (estadisticas.getHits()) / estadisticas.getTurnosAlBate());
		return promedio;
		
	}
	
	public void actualizarEstadisticas(EstadisticaJugadorPosicion est) {
	    estadisticas.setHits(estadisticas.getHits() + est.getHits());
	    estadisticas.setBasePorBolas(estadisticas.getBasePorBolas() + est.getBasePorBolas());
	    estadisticas.setCantHomeruns(estadisticas.getCantHomeruns() + est.getCantHomeruns());
	    estadisticas.setPonches(estadisticas.getPonches() + est.getPonches());
	    estadisticas.setCarrerasRemolcadas(estadisticas.getCarrerasRemolcadas() + est.getCarrerasRemolcadas());
	    estadisticas.setTurnosAlBate(estadisticas.getTurnosAlBate() + est.getTurnosAlBate());
	    estadisticas.setCarrerasAnotadas(estadisticas.getCarrerasAnotadas() + est.getCarrerasAnotadas());
	    estadisticas.setError(estadisticas.getError() + est.getError());
	}
}
