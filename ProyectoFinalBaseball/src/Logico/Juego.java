package Logico;

import java.io.Serializable;
import java.time.LocalDate;

public class Juego implements Serializable {
	private String id;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private LocalDate fechaPartido;
	private int carrerasEquipoLocal;
	private int carrerasEquipoVisitante;
	private boolean juegoTerminado;
	private static final long serialVersionUID = 1L;

	public Juego(String id, Equipo equipoLocal, Equipo equipoVisitante, LocalDate fechaPartido) {
		super();
		this.id = id;
		this.equipoLocal = equipoLocal;
		this.equipoVisitante = equipoVisitante;
		this.fechaPartido = fechaPartido;
		this.carrerasEquipoLocal = 0;
		this.carrerasEquipoVisitante = 0; 
		this.juegoTerminado = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public LocalDate getFechaPartido() {
		return fechaPartido;
	}

	public void setFechaPartido(LocalDate fechaPartido) {
		this.fechaPartido = fechaPartido;
	}

	public int getCarrerasEquipoLocal() {
		return carrerasEquipoLocal;
	}

	public void setCarrerasEquipoLocal(int carrerasEquipoLocal) {
		this.carrerasEquipoLocal = carrerasEquipoLocal;
	}

	public int getCarrerasEquipoVisitante() {
		return carrerasEquipoVisitante;
	}

	public void setCarrerasEquipoVisitante(int carrerasEquipoVisitante) {
		this.carrerasEquipoVisitante = carrerasEquipoVisitante;
	}

	public String getGanadorJuego() {
		if (carrerasEquipoLocal == carrerasEquipoVisitante) {
			return "Empate";
		}

		if (carrerasEquipoLocal > carrerasEquipoVisitante) {
			return equipoLocal.getNombre();
		}

		if (carrerasEquipoVisitante > carrerasEquipoLocal) {
			return equipoVisitante.getNombre();
		}

		return null;

	}

	public boolean isJuegoTerminado() {
		return juegoTerminado;
	}

	public void setJuegoTerminado(boolean juegoTerminado) {
		this.juegoTerminado = juegoTerminado;
	}

	public String toString() {
		return id;
	}

}
