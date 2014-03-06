package accesoaDatos;

import java.util.ArrayList;
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

import dominio.NotaInformativa;
import dominio.ReciboInmueble;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class NotaInformativaDAOImp implements NotaInformativaDAO{

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
	
	public void anyadiromodificarNotaInformativa(NotaInformativa n) throws DAOExcepcion {
		// TODO Auto-generated method stub
		
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(n);
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

	public void borrarNotaInformativaPorId(Integer id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object r = sesion.load(NotaInformativa.class, id);
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

	public void vaciarTablaNotasInformativas() throws DAOExcepcion{
		
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete NotaInformativa");
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
	
	
	
	public ArrayList<NotaInformativa> getListaNotasInformativasDAO() throws DAOExcepcion {
		// TODO Auto-generated method stub

		try{
		ArrayList<NotaInformativa> lista=new ArrayList<NotaInformativa>();
		  
		sesion = UtilidadHibernate.getSessionFactory().openSession();
	    transaccion = sesion.beginTransaction();
	        
	    lista = (ArrayList<NotaInformativa>) sesion.createQuery("from NotaInformativa").list();
	     
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

	public NotaInformativa getNotaInformativa(Integer id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			NotaInformativa n = (NotaInformativa) sesion.get(NotaInformativa.class, id);
			
			return n;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
		
	}

	public void generar(Integer id) {
		// TODO Auto-generated method stub
		 Session Sesion = null; 
		 Sesion = UtilidadHibernate.getSessionFactory().openSession(); 
		 String reportDirectory = "src/listados/listadodegastos.jasper"; 
		 HashMap parameterMap = new HashMap(); 
		 parameterMap.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, Sesion); 
		 parameterMap.put("id_nota", id);
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