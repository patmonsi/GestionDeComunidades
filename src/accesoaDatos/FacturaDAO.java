package accesoaDatos;

import java.util.ArrayList;

import dominio.Factura;
import excepciones.DAOExcepcion;

public interface FacturaDAO {
	public void anyadiromodificarFactura(Factura factura) 
		throws DAOExcepcion;
	public void borrarFacturaPorId(int numfactura) 
		throws DAOExcepcion;
	public void vaciarTablaFacturas() 
		throws DAOExcepcion;
	public ArrayList<Factura> getListaFacturasDAO() 
		throws DAOExcepcion;
	public Factura getFactura(int numfactura) 
		throws DAOExcepcion;


}