package Logico;

import java.util.ArrayList;
import java.util.Date;

public class Jugador {
	protected String idJugador;
	protected String nombre;
	protected String apellido;
	protected Date fechaNacimiento;
	protected String telefono;
	protected String direccion;
	protected String nacionalidad;
	protected String correoElectronico;
	protected int numCamiseta;
	protected Equipo equipo;
	protected float peso;
	protected float altura;
	protected String ladoDominante;
	protected boolean estaLesionado;
	//protected ArrayList<Lesion> misLesiones;
	
	protected String cedula;
	
	public Jugador(String idJugador, String nombre, String apellido, Date fechaNacimiento, String telefono,
			String direccion, String nacionalidad, String correoElectronico, int numCamiseta, Equipo equipo, float peso,
			float altura, String ladoDominante) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
		this.correoElectronico = correoElectronico;
		this.numCamiseta = numCamiseta;
		this.equipo = equipo;
		this.peso = peso;
		this.altura = altura;
		this.ladoDominante = ladoDominante;
		this.estaLesionado = false;
		//this.misLesiones = new ArrayList<Lesion>;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public int getNumCamiseta() {
		return numCamiseta;
	}

	public void setNumCamiseta(int numCamiseta) {
		this.numCamiseta = numCamiseta;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public String getLadoDominante() {
		return ladoDominante;
	}

	public void setLadoDominante(String ladoDominante) {
		this.ladoDominante = ladoDominante;
	}

	public boolean isEstaLesionado() {
		return estaLesionado;
	}

	public void setEstaLesionado(boolean estaLesionado) {
		this.estaLesionado = estaLesionado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getIdJugador() {
		return idJugador;
	}
}
