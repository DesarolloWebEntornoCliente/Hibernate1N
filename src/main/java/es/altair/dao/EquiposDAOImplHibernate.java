package es.altair.dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import es.altair.bean.Equipos;



public class EquiposDAOImplHibernate implements EquiposDAO {

	private static Scanner sc = new Scanner(System.in);
	private static Scanner tecladoLine = new Scanner(System.in);
	private static Scanner tecladoInt = new Scanner(System.in);
	
	
	public void guardar(Equipos e) {
		
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		
		Session sesion = sf.openSession();
	    
	    try {
			sesion.beginTransaction();
			sesion.save(e);
			sesion.getTransaction().commit();
	    	
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			sesion.close();
			sf.close();
		}

	}

		public Equipos colectarDatos() {
			   System.out.println("Introduzca los Datos del Nuevo Equipo [ENTER para Retornar]");
			   System.out.println();
			   System.out.println("Nombre: ");
			   String nombre = tecladoLine.nextLine();
			   if(nombre.trim().length() != 0) {
				   
				   System.out.println("Ciudad: ");
				   String ciudad = tecladoLine.nextLine();	
				   
				   System.out.println("Edad: "); 
				   int numSocios = tecladoInt.nextInt();
				   
			        Equipos e = new Equipos(nombre,ciudad,numSocios);
			        
			     return e;
			   }
			   
	      return null;
	   }

		public Equipos obtenerEquipos(int idEquipo) {
			
			Equipos equipo = null;
			
			SessionFactory sessionFactory;
		    
		    StandardServiceRegistry str = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		    
		    Metadata mt = new MetadataSources(str).getMetadataBuilder().build();
		    
		    sessionFactory = mt.getSessionFactoryBuilder().build();
		    
		       Session sesion = sessionFactory.openSession();
                
		        sesion.beginTransaction();
		        
		        equipo = (Equipos) sesion.get(Equipos.class, idEquipo);
		        
			
			sesion.close();
	        
	        sessionFactory.close();
	        
	        
			return equipo;
		}

		public List<Equipos> listaEquipos() {
			
			List<Equipos> listaEquipo = null;
			
			SessionFactory sessionFactory;
		    
		    StandardServiceRegistry str = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		    
		    Metadata mt = new MetadataSources(str).getMetadataBuilder().build();
		    
		    sessionFactory = mt.getSessionFactoryBuilder().build();
		    
		       Session sesion = sessionFactory.openSession();
                
		        sesion.beginTransaction();
		        
		        listaEquipo = sesion.createQuery("from Equipos").list();
		        		
			sesion.close();
	        
	        sessionFactory.close();
			
			
			return listaEquipo;
		}

		
}
