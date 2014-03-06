package accesoaDatos;
import org.hibernate.*;
import org.hibernate.cfg.*;



public class UtilidadHibernate { 
	private static SessionFactory sf; 
	private static Session session; 
	private static Transaction tx; 
	
	public static Session getSession(){ 
		if(session==null) 
			session=getSessionFactory().openSession(); 
		return session; 
	} 
	
	public static void closeSession(){ 
		session.close(); 
		session=null; 
	} 
	
	public static void beginTransaction(){ 
		if(tx==null) tx=getSession().beginTransaction(); 
	} 
	
	public static void endTransaction(){ 
		tx.commit(); 
		tx=null; 
	} 
	
	public static SessionFactory getSessionFactory(){ 
		if(sf==null){ 

	/*		
			Configuration configuration = new Configuration();
            configuration.configure();
            ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration.getProperties());
            sf= configuration.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());	
	*/
			sf= new Configuration().configure().buildSessionFactory(); 
		}
		return sf; 
	} 
	
} 
