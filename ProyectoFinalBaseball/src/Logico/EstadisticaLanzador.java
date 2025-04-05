package Logico;

import java.io.Serializable;

public class EstadisticaLanzador implements Serializable {
	String idJugador;
	private int ponches;
	private int strikes;
	private int bolas;
	private int bateadoresGolpeados;
	private int basePorBolas;
	private int carrerasPermitidas;
	private int hitsPermitidos;
	private int inningsJugados;
	private int error;
	
	public EstadisticaLanzador(String idJugador) {
		super();
		this.idJugador = idJugador;
		this.ponches = 0;
		this.strikes = 0;
		this.bolas = 0;
		this.bateadoresGolpeados = 0;
		this.basePorBolas = 0;
		this.carrerasPermitidas = 0;
		this.hitsPermitidos = 0;
		this.inningsJugados = 0;
		this.error = 0;
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

	public void setIdJugador(String idJugador) {
		this.idJugador = idJugador;
	}

	public String getIdJugador() {
		
		return idJugador;
	}

}
