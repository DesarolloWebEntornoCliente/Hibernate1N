package es.altair.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="jugador")
public class Jugador implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nombre;
	private String posicion;
	
	@ManyToOne
	@JoinColumn(name="idEquipo")
	private Equipos equipo;
	
	public Jugador() {
		super();
	}
	
	public Jugador(String nombre, String posicion, Equipos equipo) {
		super();
		this.nombre = nombre;
		this.posicion = posicion;
		this.equipo = equipo;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public Equipos getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipos equipo) {
		this.equipo = equipo;
	}
	
	
}
