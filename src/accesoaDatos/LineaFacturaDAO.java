package accesoaDatos;

import java.util.ArrayList;

import dominio.LineaFactura;
import excepciones.DAOExcepcion;

public interface LineaFacturaDAO {
	public void anyadiromodificarLineaFactura(LineaFactura lineafactura) 
		throws DAOExcepcion;
	public void borrarLineaFacturaPorId(int id) 
		throws DAOExcepcion;
	public void vaciarTablaLineasFactura() 
		throws DAOExcepcion;
	public ArrayList<LineaFactura> getListaLineasFacturaDAO(int numfactura) 
		throws DAOExcepcion;
	public ArrayList<LineaFactura> getListaLineasFacturaDAO() 
	throws DAOExcepcion;
	public LineaFactura getLineaFactura(int id) 
		throws DAOExcepcion;



}

