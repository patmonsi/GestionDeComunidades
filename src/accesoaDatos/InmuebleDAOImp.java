package accesoaDatos;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.JDBCConnectionException;

import dominio.Inmueble;
import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

@SuppressWarnings("all")
public class InmuebleDAOImp implements InmuebleDAO{

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
	
	public void anyadiromodificarInmueble(Inmueble i) throws DAOExcepcion{

		//Mediante Hibernate:
		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			transaccion = sesion.beginTransaction();
			sesion.saveOrUpdate(i);
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
	
	public void borrarInmueblePorId(int id) throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    Object i = sesion.load(Inmueble.class, id);
		    sesion.delete(i);
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
	
	public void vaciarTablaInmuebles() throws DAOExcepcion{

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
		    transaccion = sesion.beginTransaction();
		    sesion.createQuery("delete Inmueble");
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
	
	public ArrayList<Inmueble> getListaInmueblesDAO() throws DAOExcepcion{

		try{
		  
		sesion = UtilidadHibernate.getSessionFactory().openSession();
	    transaccion = sesion.beginTransaction();
	        
	    ArrayList<Inmueble> lista = (ArrayList<Inmueble>) sesion.createQuery("from Inmueble").list();
	     
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
	public Inmueble getInmueble(int id) throws DAOExcepcion {
		// TODO Auto-generated method stub

		try {
			sesion = UtilidadHibernate.getSessionFactory().openSession();
			Inmueble i= (Inmueble) sesion.get(Inmueble.class, id);
			
//			System.out.println(i.getEscalera());
	//		i.getEscalera();
			return (Inmueble)i;
			}
			catch (HibernateException e) {
			rollback(); throw e;
			} 
			finally {
			sesion.close();
			} 
			
	}

	
}













