package accesoaDatos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import dominio.Propietario;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class PropietarioDAOImp implements PropietarioDAO{

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
	
	public void anyadiromodificarPropietario(Propietario p) throws DAOExcepcion {
		// TODO Auto-generated method stub
		
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(p);
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

	public void borrarPropietarioPorNIF(String nif) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object p = sesion.load(Propietario.class, nif);
		    sesion.delete(p);
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

	public void vaciarTablaPropietarios() throws DAOExcepcion{
		
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete Propietario");
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
	
	
	
	public ArrayList<Propietario> getListaPropietariosDAO() throws DAOExcepcion {
		// TODO Auto-generated method stub

		try{
		ArrayList<Propietario> lista=new ArrayList<Propietario>();
		  
		sesion = UtilidadHibernate.getSessionFactory().openSession();
	    transaccion = sesion.beginTransaction();
	        
	    lista = (ArrayList<Propietario>) sesion.createQuery("from Propietario").list();
	     
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

	public Propietario getPropietario(String nif) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			Propietario p = (Propietario) sesion.get(Propietario.class, nif);
			
	//		System.out.println(p.getNombre());
			
			return p;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
		
	}
	
	public void generar() {
		// TODO Auto-generated method stub
		 Session Sesion = null; 
		 Sesion = UtilidadHibernate.getSessionFactory().openSession(); 
		 String reportDirectory = "src/listados/listadodepropietarios2.jasper"; 
		 HashMap parameterMap = new HashMap(); 
		 parameterMap.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, Sesion); 
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

	public void generarListadoRecibos(String nif, Date fecha) {
		// TODO Auto-generated method stub
		 Session Sesion = null; 
		 Sesion = UtilidadHibernate.getSessionFactory().openSession(); 
		 String reportDirectory = "src/listados/carta.jasper"; 
		 HashMap parameterMap = new HashMap(); 
		 parameterMap.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, Sesion);
		 parameterMap.put("fecha_actual", fecha);
		 parameterMap.put("nif_propietario", nif);
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
