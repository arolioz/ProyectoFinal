package Logico;

public class EstadisticaJugadorPosicion {
	private int hits;
	private int basePorBolas;
	private int cantHomeruns;
	private int carrerasRemolcadas;
	private int carrerasAnotadas;
	private int turnosAlBate;
	private int ponches;
	private int error;
	
	public EstadisticaJugadorPosicion(int hits, int basePorBolas, int cantHomeruns, int carrerasRemolcadas,
			int carrerasAnotadas, int turnosAlBate, int ponches, int error) {
		super();
		this.hits = hits;
		this.basePorBolas = basePorBolas;
		this.cantHomeruns = cantHomeruns;
		this.carrerasRemolcadas = carrerasRemolcadas;
		this.carrerasAnotadas = carrerasAnotadas;
		this.turnosAlBate = turnosAlBate;
		this.ponches = ponches;
		this.error = error;
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
}
