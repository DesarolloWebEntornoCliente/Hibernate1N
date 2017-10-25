package es.altair.main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.mapping.Set;

import es.altair.bean.Equipos;
import es.altair.bean.Jugador;
import es.altair.dao.ConexionDAO;
import es.altair.dao.EquiposDAO;
import es.altair.dao.EquiposDAOImplHibernate;


public class App 
{
	
	private static Scanner sc = new Scanner(System.in);
	private static Scanner tecladoLine = new Scanner(System.in);
	private static Scanner tecladoInt = new Scanner(System.in);
	
	private static EquiposDAO equipoDAO = new EquiposDAOImplHibernate();


    public static void main( String[] args )
    {
    	
        int opcion = 0;
        do {
    			menu();
    			opcion = sc.nextInt();
    			
    			switch (opcion) {
 				case 1:
 					
 					// Añadir un Equipo //
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

 				       Session sesion = ConexionDAO.abrirConexion();
 		                
 				        
 				        sesion.save(e);
 				        
 				        sesion.getTransaction().commit();
 				        
 				      //ConexionDAO.desconectar(sesion, sessionFactory);
 				        
 	  				      ConexionDAO.desconectar(sesion);
 				        
 				        //sessionFactory.close();
 				      
 				      System.out.println("Equipo Insertado Corretamente !!!");

 				   }
 					
  					break;
 				case 2:
 					// Añadir Judadores //
 					
 					Equipos eq = new Equipos(17,"Ponte Preta","Aracatuba",895);
 					
 					Set<Jugador> jugadores = new HashSet<Jugador>();
 					
 					jugadores.add(new Jugador("Pedro","Portero",eq));
 					jugadores.add(new Jugador("Joao Dias Manuel","Centro ",eq));
 					jugadores.add(new Jugador("Manuel Pinto","Defensa",eq));   
 					
 				    eq.setJugadores(jugadores);
 					
 					equipoDAO.guardar(eq);
 					
				    System.out.println("Jugadores Insertados Corretamente !!!");   

 					
 					break;
 				case 3:
 					
				     Session sesion = ConexionDAO.abrirConexion();
				       
				       Equipos alt = (Equipos)sesion.get(Equipos.class, 10);
 					
 				// Modificar Datos de un Equipo //
  				   System.out.println("Introduzca los Datos a Modificar [ENTER para Saltar]");
  				   System.out.println();
  				   System.out.println("Nombre: ");
  				   String nombreAlt = tecladoLine.nextLine();
  				   
  				   if(nombreAlt.trim().length() == 0)
  					   nombreAlt = alt.getNombre();
  					   
  					   System.out.println("Ciudad: ");
  					   String ciudadAlt = tecladoLine.nextLine();	
  					   
  	  				   if(ciudadAlt.trim().length() == 0)
  	  					   ciudadAlt = alt.getCiudad();
  	  				   
  	  				   System.out.println("Edad: ");
  					   int numSociosAlt = tecladoInt.nextInt();
  					   
  					   if(numSociosAlt == 0)
  						   numSociosAlt = alt.getNumSocios();
  					               
  				        sesion.beginTransaction();
  				        
  				        alt.setNombre(nombreAlt);
  				        sesion.update(alt);
  				        
  				        alt.setCiudad(ciudadAlt);
  				        sesion.update(alt);

  				        alt.setNumSocios(numSociosAlt);
  				        sesion.update(alt);
  				        
  				        sesion.getTransaction().commit();
  				        
  				      ConexionDAO.desconectar(sesion);
  				      
  				      System.out.println("Equipo Modificado Corretamente !!!");

  				   
  				   
  				   break;
 				case 4:
 					break;
 				case 5:
 					break;
 				case 6:
 					break;
 				case 7:
 					/*	int miIdEquipo = 16;
 						
 						Equipos equipo = null;
 						
 						equipo = equipoDAO.obtenerEquipos(miIdEquipo);
 						
 						System.out.println(equipo); */
 					
 						List<Equipos> datos = equipoDAO.listaEquipos();
 						
 						Iterator it = datos.iterator();
 						
 						while (it.hasNext()) {
 							
							Equipos eqLista = (Equipos)it.next();
							
							Object[] fila = new Object[4];
							
							fila[0] = eqLista.getIdEquipo();
							fila[1] = eqLista.getNombre();
							fila[2] = eqLista.getCiudad();
							fila[3] = eqLista.getNumSocios();
							
							for (int i = 0; i < fila.length; i++) {
								System.out.print(fila[i] + "		" );
							}
							System.out.println();

						}
 						
 					
 					
 					break;
 				
 				
    			
    			
    			}
    			
    	} while (opcion != 0);   	
    			
    	sc.close();
    }


	private static void desconectar(SessionFactory sessionFactory, Session sesion) {
		sesion.close();
        
        sessionFactory.close();
	}


	private static SessionFactory conectar() {
		SessionFactory sessionFactory;
		    
		    StandardServiceRegistry str = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		    
		    Metadata mt = new MetadataSources(str).getMetadataBuilder().build();
		    
		    sessionFactory = mt.getSessionFactoryBuilder().build();
		return sessionFactory;
	}
    
  
	private static void menu() {
		// Menu Principal
		System.out.println();
		System.out.println("╔════════════════════════╗");
		System.out.println("║     MENÚ PRINCIPAL     ║");
		System.out.println("╠════════════════════════╣");
		System.out.println("║ 1) Añadir un Equipo    ║");		
		System.out.println("║                        ║");
		System.out.println("║ 2) Añadir un Jugador   ║");		
		System.out.println("║                        ║");
		System.out.println("║ 3) Actualizar Equipo   ║");		
		System.out.println("║                        ║");
		System.out.println("║ 4) Actualizar Jugador  ║");		
		System.out.println("║                        ║");
		System.out.println("║ 5) Borrar Equipo       ║");
		System.out.println("║                        ║");		
		System.out.println("║ 6) Borrar Jugador      ║");
		System.out.println("║                        ║");
		System.out.println("║ 7) Mostrar todo        ║");	
		System.out.println("║________________________║");
		System.out.println("║        0) Salir        ║");
		System.out.println("╚════════════════════════╝");
	}
    
}
