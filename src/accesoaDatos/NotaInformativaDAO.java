package accesoaDatos;

import java.util.ArrayList;

import dominio.NotaInformativa;
import excepciones.DAOExcepcion;

public interface NotaInformativaDAO {
	public void anyadiromodificarNotaInformativa(NotaInformativa nota) 
		throws DAOExcepcion;
	public void borrarNotaInformativaPorId(Integer id) 
		throws DAOExcepcion;
	public void vaciarTablaNotasInformativas() 
		throws DAOExcepcion;
	public ArrayList<NotaInformativa> getListaNotasInformativasDAO() 
		throws DAOExcepcion;
	public NotaInformativa getNotaInformativa(Integer id) 
		throws DAOExcepcion;
	public void generar(Integer id)
	throws DAOExcepcion;
}
