package accesoaDatos;

import java.util.ArrayList;
import java.util.Date;

import dominio.Propietario;
import excepciones.DAOExcepcion;

public interface PropietarioDAO {
	public void anyadiromodificarPropietario(Propietario propietario) 
	throws DAOExcepcion;
//	public void borrarPropietarioPorId(String nif) 
//	throws DAOExcepcion;
	public void vaciarTablaPropietarios() 
	throws DAOExcepcion;
	public ArrayList<Propietario> getListaPropietariosDAO() 
	throws DAOExcepcion;
	public Propietario getPropietario(String nif) 
	throws DAOExcepcion;
	public void generar()
	throws DAOExcepcion;
	public void generarListadoRecibos(String nif, Date fecha)
	throws DAOExcepcion;
}
