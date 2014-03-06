package accesoaDatos;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import dominio.Factura;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class FacturaDAOImp implements FacturaDAO{

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
	
	public void anyadiromodificarFactura(Factura f) throws DAOExcepcion{

		//Mediante Hibernate:
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(f);
			transaccion.commit();
			}
			catch (JDBCConnectionException e)
			{ 
			throw new DAOConfiguracionExcepcion(e);
			} 
			catch (HibernateException e) { rollback();
			throw new DAOExcepcion(e);
			} finally { sesion.close(); }
		
	}
	
	public void borrarFacturaPorId(int id) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object f = sesion.load(Factura.class, id);
		    sesion.delete(f);
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
	
	public void vaciarTablaFacturas() throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete Factura");
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
	
	public ArrayList<Factura> getListaFacturasDAO() throws DAOExcepcion{

		try{
		  
		sesion = UtilidadHibernate.getSessionFactory().openSession();
	    transaccion = sesion.beginTransaction();
	        
	    ArrayList<Factura> lista = (ArrayList<Factura>) sesion.createQuery("from Factura").list();
	     
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
	public Factura getFactura(int id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			Factura f= (Factura) sesion.get(Factura.class, id);
			
//			System.out.println(i.getEscalera());
	//		i.getEscalera();
			return (Factura)f;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
			
	}

	
}









