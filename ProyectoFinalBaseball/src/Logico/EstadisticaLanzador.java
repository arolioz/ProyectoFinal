package Logico;

public class EstadisticaLanzador {
	private int ponches;
	private int strikes;
	private int bolas;
	private int bateadoresGolpeados;
	private int basePorBolas;
	private int carrerasPermitidas;
	private int hitsPermitidos;
	private int inningsJugados;
	private int error;
	
	public EstadisticaLanzador(int ponches, int strikes, int bolas, int bateadoresGolpeados, int basePorBolas,
			int carrerasPermitidas, int hitsPermitidos, int inningsJugados, int error) {
		super();
		this.ponches = ponches;
		this.strikes = strikes;
		this.bolas = bolas;
		this.bateadoresGolpeados = bateadoresGolpeados;
		this.basePorBolas = basePorBolas;
		this.carrerasPermitidas = carrerasPermitidas;
		this.hitsPermitidos = hitsPermitidos;
		this.inningsJugados = inningsJugados;
		this.error = error;
	}

	public int getPonches() {
		return ponches;
	}

	public void setPonches(int ponches) {
		this.ponches = ponches;
	}

	public int getStrikes() {
		return strikes;
	}

	public void setStrikes(int strikes) {
		this.strikes = strikes;
	}

	public int getBolas() {
		return bolas;
	}

	public void setBolas(int bolas) {
		this.bolas = bolas;
	}

	public int getBateadoresGolpeados() {
		return bateadoresGolpeados;
	}

	public void setBateadoresGolpeados(int bateadoresGolpeados) {
		this.bateadoresGolpeados = bateadoresGolpeados;
	}

	public int getBasePorBolas() {
		return basePorBolas;
	}

	public void setBasePorBolas(int basePorBolas) {
		this.basePorBolas = basePorBolas;
	}

	public int getCarrerasPermitidas() {
		return carrerasPermitidas;
	}

	public void setCarrerasPermitidas(int carrerasPermitidas) {
		this.carrerasPermitidas = carrerasPermitidas;
	}

	public int getHitsPermitidos() {
		return hitsPermitidos;
	}

	public void setHitsPermitidos(int hitsPermitidos) {
		this.hitsPermitidos = hitsPermitidos;
	}

	public int getInningsJugados() {
		return inningsJugados;
	}

	public void setInningsJugados(int inningsJugados) {
		this.inningsJugados = inningsJugados;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

}
