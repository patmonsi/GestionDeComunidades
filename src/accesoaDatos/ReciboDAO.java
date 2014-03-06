package accesoaDatos;

import java.util.ArrayList;
import java.util.Date;

import dominio.ReciboInmueble;
import excepciones.DAOExcepcion;

public interface ReciboDAO {
	public void anyadiromodificarRecibo(ReciboInmueble recibo) 
		throws DAOExcepcion;
	public void borrarReciboPorId(Integer id) 
		throws DAOExcepcion;
	public void vaciarTablaRecibos() 
		throws DAOExcepcion;
	public ArrayList<ReciboInmueble> getListaRecibosDAO() 
		throws DAOExcepcion;
	public ReciboInmueble getRecibo(Integer id) 
		throws DAOExcepcion;
	public void generar(Integer id, Date fecha, Date fechapago)
	throws DAOExcepcion;

}
