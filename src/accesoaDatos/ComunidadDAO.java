package accesoaDatos;

import java.util.ArrayList;

import dominio.Comunidad;
import excepciones.DAOExcepcion;

public interface ComunidadDAO {
	public void anyadiromodificarComunidad(Comunidad comunidad) 
		throws DAOExcepcion;
	public void borrarComunidadPorNombre(String nombre) 
		throws DAOExcepcion;
	public void vaciarTablaComunidades() 
		throws DAOExcepcion;
	public ArrayList<Comunidad> getListaComunidadesDAO() 
		throws DAOExcepcion;
	public Comunidad getComunidad(String nombre) 
		throws DAOExcepcion;
	public void run(String nombre)
		throws DAOExcepcion;


}
