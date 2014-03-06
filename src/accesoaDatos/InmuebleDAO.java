package accesoaDatos;

import java.util.ArrayList;

import dominio.Inmueble;
import excepciones.DAOExcepcion;

public interface InmuebleDAO {
	public void anyadiromodificarInmueble(Inmueble inmueble) 
		throws DAOExcepcion;
	public void borrarInmueblePorId(int id) 
		throws DAOExcepcion;
	public void vaciarTablaInmuebles() 
		throws DAOExcepcion;
	public ArrayList<Inmueble> getListaInmueblesDAO() 
		throws DAOExcepcion;
	public Inmueble getInmueble(int id) 
		throws DAOExcepcion;


}
