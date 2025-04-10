package Logico;

import java.io.Serializable;
import java.util.Date;

public class Lesion implements Serializable {
	private String id;
	private String tipoLesion;
	private Juego juego;
	private Jugador jugador;
	private int prorroga;
	private Date fechaLesion;
	private Date fechaReincorporacion;
	private String descripcion;
	private static final long serialVersionUID = 1L;

	public Lesion(String id, String tipoLesion, Juego juego, Jugador jugador, int prorroga, Date fechaLesion,
			Date fechaReincorporacion, String descripcion) {
		super();
		this.id = id;
		this.tipoLesion = tipoLesion;
		this.juego = juego;
		this.jugador = jugador;
		this.prorroga = prorroga;
		this.fechaLesion = fechaLesion;
		this.fechaReincorporacion = fechaReincorporacion;
		this.descripcion = descripcion;
	}

	public String getTipoLesion() {
		return tipoLesion;
	}

	public void setTipoLesion(String tipoLesion) {
		this.tipoLesion = tipoLesion;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public int getProrroga() {
		return prorroga;
	}

	public void setProrroga(int prorroga) {
		this.prorroga = prorroga;
	}

	public Date getFechaLesion() {
		return fechaLesion;
	}

	public void setFechaLesion(Date fechaLesion) {
		this.fechaLesion = fechaLesion;
	}

	public Date getFechaReincorporacion() {
		return fechaReincorporacion;
	}

	public void setFechaReincorporacion(Date fechaReincorporacion) {
		this.fechaReincorporacion = fechaReincorporacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getId() {
		return id;
	}

}
