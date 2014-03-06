package gestion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import accesoaDatos.ConceptoDAO;
import accesoaDatos.ConceptoDAOImp;

import dominio.Concepto;

import excepciones.DAOExcepcion;
import excepciones.ConceptoYaExiste;

@SuppressWarnings("all")
public class GestionConceptos{
	private ConceptoDAOImp dao;
	private ArrayList<Concepto> lista = new ArrayList<Concepto>();

	public ArrayList<Concepto> getListaConceptos() throws DAOExcepcion {
		for(Concepto c : dao.getListaConceptosDAO()) {
			lista.add(c);
		}
		return dao.getListaConceptosDAO();
	}
	
	public GestionConceptos() {
		dao = new ConceptoDAOImp();
		lista = new ArrayList<Concepto>();
	}	

	public void setLista(ArrayList<Concepto> lista) {
		this.lista = lista;
	}
	
	public void addConcepto(Concepto concepto) throws ConceptoYaExiste{
		Concepto aux = null;
		for(Concepto c : lista) {
			if(c.getClaveconcepto() == concepto.getClaveconcepto()){ 
				throw new ConceptoYaExiste();	
			}
		}
		try {
			dao.anyadiromodificarConcepto(concepto);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(concepto);	
	}
	
	//Nuevo
	public void actualizaConcepto(Concepto concepto){

		try {
			dao.anyadiromodificarConcepto(concepto);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.remove(getConceptoPorClave(concepto.getClaveconcepto()));
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public Concepto getConceptoPorClave(String clave) throws DAOExcepcion {
		return dao.getConcepto(clave);
	}	
	
	public void borraConceptoPorClave(String clave) throws DAOExcepcion{
		lista.remove(getConceptoPorClave(clave));
		dao.borrarConceptoPorClave(clave);		
	}
}