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

import dominio.LineaFactura;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class LineaFacturaDAOImp implements LineaFacturaDAO{

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
	
	public void anyadiromodificarLineaFactura(LineaFactura l) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(l);
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
	
	public void borrarLineaFacturaPorId(int id) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object c = sesion.load(LineaFactura.class, id);
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
	
	public void vaciarTablaLineasFactura() throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete LineaFactura");
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
	
	public ArrayList<LineaFactura> getListaLineasFacturaDAO(int numfactura) throws DAOExcepcion{

		try{
		  sesion = UtilidadHibernate.getSessionFactory().openSession();
	      transaccion = sesion.beginTransaction();
	        
	      ArrayList<LineaFactura> lista=new ArrayList<LineaFactura>();
	        
	      lista = (ArrayList<LineaFactura>) sesion.createQuery("from LineaFactura L where L.factura.numfactura="+numfactura).list();
	     
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

	public ArrayList<LineaFactura> getListaLineasFacturaDAO() throws DAOExcepcion{

		try{
		  sesion = UtilidadHibernate.getSessionFactory().openSession();
	      transaccion = sesion.beginTransaction();
	        
	      ArrayList<LineaFactura> lista=new ArrayList<LineaFactura>();
	        
	      lista = (ArrayList<LineaFactura>) sesion.createQuery("from LineaFactura").list();
	     
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
	public LineaFactura getLineaFactura(int id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			LineaFactura l = (LineaFactura) sesion.get(LineaFactura.class, id);
			
	//		System.out.println(c.getCalle());
			
			return l;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
		
		
	}

	
}




