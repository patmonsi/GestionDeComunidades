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

import dominio.ReciboInmueble;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class ReciboDAOImp implements ReciboDAO{

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
	
	public void anyadiromodificarRecibo(ReciboInmueble r) throws DAOExcepcion {
		// TODO Auto-generated method stub
		
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(r);
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

	public void borrarReciboPorId(Integer id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object r = sesion.load(ReciboInmueble.class, id);
		    sesion.delete(r);
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

	public void vaciarTablaRecibos() throws DAOExcepcion{
		
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete ReciboInmueble");
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
	
	
	
	public ArrayList<ReciboInmueble> getListaRecibosDAO() throws DAOExcepcion {
		// TODO Auto-generated method stub

		try{
		ArrayList<ReciboInmueble> lista=new ArrayList<ReciboInmueble>();
		  
		sesion = UtilidadHibernate.getSessionFactory().openSession();
	    transaccion = sesion.beginTransaction();
	        
	    lista = (ArrayList<ReciboInmueble>) sesion.createQuery("from ReciboInmueble").list();
	     
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

	public ReciboInmueble getRecibo(Integer id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			ReciboInmueble r = (ReciboInmueble) sesion.get(ReciboInmueble.class, id);
			
	//		System.out.println(p.getNombre());
			
			return r;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
		
	}
	
	public void generar(Integer id, Date fecha, Date fechapago) {
		// TODO Auto-generated method stub
		 Session Sesion = null; 
		 HashMap parameterMap;
		 String reportDirectory;
		 Sesion = UtilidadHibernate.getSessionFactory().openSession(); 
		 if(fechapago==null){
			 reportDirectory = "src/listados/recibo.jasper"; 
			 parameterMap = new HashMap(); 
			 parameterMap.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, Sesion); 
			 parameterMap.put("fecha_calculo", fecha);
			 parameterMap.put("id_recibo", id);
		 }
		 else{
			 reportDirectory = "src/listados/justificante.jasper"; 
			 parameterMap = new HashMap(); 
			 parameterMap.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, Sesion); 
			 parameterMap.put("fecha_calculo", fecha);
			 parameterMap.put("id_recibo", id);
		 }
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
