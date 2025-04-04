package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Lanzador extends Jugador {
	private ArrayList<String> misLanzamientos;
	private String rolLanzador;
	private EstadisticaLanzador estadisticas;
	
	public Lanzador(String idJugador, String nombre, String apellido, Date fechaNacimiento, String telefono,
			String direccion, String nacionalidad, String correoElectronico, int numCamiseta, Equipo equipo, float peso,
			float altura, String ladoDominante,
			String rolLanzador) {
		
		super(idJugador, nombre, apellido, fechaNacimiento, telefono, direccion, nacionalidad, correoElectronico,
				numCamiseta, equipo, peso, altura, ladoDominante);
		
		this.estadisticas = new EstadisticaLanzador(idJugador);
		this.misLanzamientos = null;
		this.rolLanzador = rolLanzador;
	}

	public ArrayList<String> getMisLanzamientos() {
		return misLanzamientos;
	}

	public void setMisLanzamientos(ArrayList<String> misLanzamientos) {
		this.misLanzamientos = misLanzamientos;
	}

	public String getRolLanzador() {
		return rolLanzador;
	}

	public void setRolLanzador(String rolLanzador) {
		this.rolLanzador = rolLanzador;
	}

	public EstadisticaLanzador getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(EstadisticaLanzador estadisticas) {
		this.estadisticas = estadisticas;
	}
	
	public float calcularEfectividad() {
		return (float) ((estadisticas.getCarrerasPermitidas() * 9)/ estadisticas.getInningsJugados());
	}
}
