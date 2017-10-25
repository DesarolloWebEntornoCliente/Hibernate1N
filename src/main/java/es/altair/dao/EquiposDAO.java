package es.altair.dao;

import java.util.List;

import es.altair.bean.Equipos;

public interface EquiposDAO {
	
	public void guardar(Equipos e);
	
	public Equipos colectarDatos();
	
	public Equipos obtenerEquipos(int idEquipo);
	
	public List<Equipos> listaEquipos();
 }
