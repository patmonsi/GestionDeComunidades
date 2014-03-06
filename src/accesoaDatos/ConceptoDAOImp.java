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

import dominio.Concepto;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class ConceptoDAOImp implements ConceptoDAO{

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
	
	public void anyadiromodificarConcepto(Concepto c) throws DAOExcepcion{

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
	public void borrarConceptoPorClave(String clave) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object c = sesion.load(Concepto.class, clave);
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
	
	public void vaciarTablaConceptos() throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete Concepto");
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
	
	public ArrayList<Concepto> getListaConceptosDAO() throws DAOExcepcion{

		try{
		  sesion = UtilidadHibernate.getSessionFactory().openSession();
	      transaccion = sesion.beginTransaction();
	        
	      ArrayList<Concepto> lista=new ArrayList<Concepto>();
	        
	      lista = (ArrayList<Concepto>) sesion.createQuery("from Concepto").list();
	     
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
	public Concepto getConcepto(String clave) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			Concepto c = (Concepto) sesion.get(Concepto.class, clave);
			
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

	
	
}




