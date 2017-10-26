package es.altair.dao;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ConexionDAO {

	public static Session abrirConexion() {
		SessionFactory sessionFactory;
		    
		    StandardServiceRegistry str = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		    
		    Metadata mt = new MetadataSources(str).getMetadataBuilder().build();
		    
		    sessionFactory = mt.getSessionFactoryBuilder().build();
		    
		       Session sesion = sessionFactory.openSession();
              
		        sesion.beginTransaction();
		        
		        return sesion;
		    
	}
	
	public static void desconectar(Session sesion) {
		
		sesion.close();
        
	}

	
}
