package accesoaDatos;

import java.util.ArrayList;

import dominio.Concepto;
import excepciones.DAOExcepcion;

public interface ConceptoDAO {
	public void anyadiromodificarConcepto(Concepto concepto) 
		throws DAOExcepcion;
	public void borrarConceptoPorClave(String clave) 
		throws DAOExcepcion;
	public void vaciarTablaConceptos() 
		throws DAOExcepcion;
	public ArrayList<Concepto> getListaConceptosDAO() 
		throws DAOExcepcion;
	public Concepto getConcepto(String clave) 
		throws DAOExcepcion;


}
