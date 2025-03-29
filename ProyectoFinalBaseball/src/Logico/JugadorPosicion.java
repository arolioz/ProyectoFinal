package Logico;

import java.util.Date;

public class JugadorPosicion extends Jugador {
	private String posicion;
	private EstadisticaJugadorPosicion estadisticas;
	private float promedio;
	
	
	public JugadorPosicion(String idJugador, String nombre, String apellido, Date fechaNacimiento, String telefono,
			String direccion, String nacionalidad, String correoElectronico, int numCamiseta, Equipo equipo, float peso,
			float altura, String ladoDominante, String posicion
			) {
		
		super(idJugador, nombre, apellido, fechaNacimiento, telefono, direccion, nacionalidad, correoElectronico,
				numCamiseta, equipo, peso, altura, ladoDominante);
		
		this.posicion = posicion;
		this.estadisticas = new EstadisticaJugadorPosicion();
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
		return (float) (estadisticas.getHits() / estadisticas.getTurnosAlBate());
	}
	

}
