package es.altair.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


	@Entity
	@Table(name="equipo")

	public class Equipos implements Serializable{

		@Id
		  private int idEquipo;
		  private String nombre;
		  private String ciudad;
		  private int numSocios;
		  
		  @OneToMany(mappedBy="equipo", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
		  private Set<Jugador> jugadores;
		  
		public Equipos() {
			super();
		}

		
		
		public Equipos(String nombre, String ciudad, int numSocios) {
			super();
			this.nombre = nombre;
			this.ciudad = ciudad;
			this.numSocios = numSocios;
		}



		public Equipos(int idEquipo, String nombre, String ciudad, int numSocios) {
			super();
			this.idEquipo = idEquipo;
			this.nombre = nombre;
			this.ciudad = ciudad;
			this.numSocios = numSocios;
		}

		public int getIdEquipo() {
			return idEquipo;
		}

		public void setIdEquipo(int idEquipo) {
			this.idEquipo = idEquipo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getCiudad() {
			return ciudad;
		}

		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}

		public int getNumSocios() {
			return numSocios;
		}

		public void setNumSocios(int numSocios) {
			this.numSocios = numSocios;
		}
		
		public Set<Jugador> getJugadores() {
			return jugadores;
		}
		
		public void setJugadores(Set<Jugador> jugadores) {
			this.jugadores = jugadores;
		}

		@Override
		public String toString() {
			return "Equipo [idEquipo=" + idEquipo + ", nombre=" + nombre + ", ciudad=" + ciudad + ", numSocios=" + numSocios
					+ "]";
		}
		  
		  
		  
	}

