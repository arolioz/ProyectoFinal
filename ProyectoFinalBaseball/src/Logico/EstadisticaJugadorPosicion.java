package Logico;

public class EstadisticaJugadorPosicion {
	String idJugador;
	private int hits;
	private int basePorBolas;
	private int cantHomeruns;
	private int carrerasRemolcadas;
	private int carrerasAnotadas;
	private int turnosAlBate;
	private int ponches;
	private int error;
	
	public EstadisticaJugadorPosicion(String idJugador) {
		super();
		this.idJugador = idJugador;
		this.hits = 0;
		this.basePorBolas = 0;
		this.cantHomeruns = 0;
		this.carrerasRemolcadas = 0;
		this.carrerasAnotadas = 0;
		this.turnosAlBate = 0;
		this.ponches = 0;
		this.error = 0;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getBasePorBolas() {
		return basePorBolas;
	}

	public void setBasePorBolas(int basePorBolas) {
		this.basePorBolas = basePorBolas;
	}

	public int getCantHomeruns() {
		return cantHomeruns;
	}

	public void setCantHomeruns(int cantHomeruns) {
		this.cantHomeruns = cantHomeruns;
	}

	public int getCarrerasRemolcadas() {
		return carrerasRemolcadas;
	}

	public void setCarrerasRemolcadas(int carrerasRemolcadas) {
		this.carrerasRemolcadas = carrerasRemolcadas;
	}

	public int getCarrerasAnotadas() {
		return carrerasAnotadas;
	}

	public void setCarrerasAnotadas(int carrerasAnotadas) {
		this.carrerasAnotadas = carrerasAnotadas;
	}

	public int getTurnosAlBate() {
		return turnosAlBate;
	}

	public void setTurnosAlBate(int turnosAlBate) {
		this.turnosAlBate = turnosAlBate;
	}

	public int getPonches() {
		return ponches;
	}

	public void setPonches(int ponches) {
		this.ponches = ponches;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getIdJugador() {
		return idJugador;
	}
}
