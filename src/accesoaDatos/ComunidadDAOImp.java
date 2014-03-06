package accesoaDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import dominio.Comunidad;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class ComunidadDAOImp implements ComunidadDAO{

	private Session sesion = null; // Hibernate Session
	private Transaction transaccion = null; // Hibernate Transaction
	
	private void rollback(){
		try {
			if (transaccion != null) {
				transaccion.rollback();
			}
		} catch (HibernateException ignored) {
		// The rollback cannot be done;
			}
		}
	
	public void anyadiromodificarComunidad(Comunidad c) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(c);
			transaccion.commit();
			}
			catch (JDBCConnectionException e)
			{ // no puede conectar con la bd, error grave.
			throw new DAOConfiguracionExcepcion(e);
			} 
			catch (HibernateException e) { rollback();
			throw new DAOExcepcion(e);
			} finally { sesion.close(); }
		
		
	}
	
	public void borrarComunidadPorNombre(String nombre) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object c = sesion.load(Comunidad.class, nombre);
		    sesion.delete(c);
		    transaccion.commit();
		      
			}catch (JDBCConnectionException e)
			{ // no puede conectar con la bd, error grave.
			throw new DAOConfiguracionExcepcion(e);
			} 
			catch (HibernateException e) {
			rollback();
			throw new DAOExcepcion(e);
			} finally {
			sesion.close();
			}
		
	}
	
	public void vaciarTablaComunidades() throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete Comunidad");
		    transaccion.commit();
		      
			}catch (JDBCConnectionException e)
			{ // no puede conectar con la bd, error grave.
			throw new DAOConfiguracionExcepcion(e);
			} 
			catch (HibernateException e) {
			rollback();
			throw new DAOExcepcion(e);
			} finally {
			sesion.close();
			}
		
	}
	
	public ArrayList<Comunidad> getListaComunidadesDAO() throws DAOExcepcion{

		try{
		  sesion = UtilidadHibernate.getSessionFactory().openSession();
	      transaccion = sesion.beginTransaction();
	        
	      ArrayList<Comunidad> lista=new ArrayList<Comunidad>();
	        
	      lista = (ArrayList<Comunidad>) sesion.createQuery("from Comunidad").list();
	     
	      transaccion.commit();
	      return lista;
		}
		catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 

	      
		
	       
		
		}

	@Override
	public Comunidad getComunidad(String nombre) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			Comunidad c = (Comunidad) sesion.get(Comunidad.class, nombre);
			
	//		System.out.println(c.getCalle());
			
			return c;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
		
		
	}


	
	
	public void run(String nombre){ 
	// Cargar el report y visualizarlo.
	 Session Sesion = null; 
	 Sesion = UtilidadHibernate.getSessionFactory().openSession(); 
	 String reportDirectory = "src/listados/classic.jasper"; 
	 HashMap parameterMap = new HashMap(); 
	 parameterMap.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, Sesion); 
	 parameterMap.put("nombre", nombre);
	 JasperPrint report = null; 
	try{ 
	 System.out.println("Llenando informe..."); 
	 report = JasperFillManager.fillReport(reportDirectory, parameterMap); 
	 JasperViewer.viewReport(report, false); 
	 System.out.println("Finalizado.."); 
	 } 
		catch (JRException e) 
	 { 
	 e.printStackTrace(); 
	 } 
	}
	
	
}





