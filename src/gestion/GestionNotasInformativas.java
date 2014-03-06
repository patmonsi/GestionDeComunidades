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

import accesoaDatos.NotaInformativaDAO;
import accesoaDatos.NotaInformativaDAOImp;

import dominio.NotaInformativa;

import excepciones.DAOExcepcion;
import excepciones.NotaInformativaYaExiste;

@SuppressWarnings("all")
public class GestionNotasInformativas{
	private NotaInformativaDAOImp dao;
	private ArrayList<NotaInformativa> lista = new ArrayList<NotaInformativa>();

	public ArrayList<NotaInformativa> getListaNotasInformativas() throws DAOExcepcion {
		for(NotaInformativa n : dao.getListaNotasInformativasDAO()) {
			lista.add(n);
		}
		return dao.getListaNotasInformativasDAO();
	}
	
	public GestionNotasInformativas() {
		dao = new NotaInformativaDAOImp();
		lista = new ArrayList<NotaInformativa>();
	}	

	public void setLista(ArrayList<NotaInformativa> lista) {
		this.lista = lista;
	}

	public void addNotaInformativa(NotaInformativa nota) throws NotaInformativaYaExiste{
		NotaInformativa aux = null;
		for(NotaInformativa n : lista) {
			if(n.getIdNota()==nota.getIdNota()){ 
				throw new NotaInformativaYaExiste();	
			}
		}
		try {
			dao.anyadiromodificarNotaInformativa(nota);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(nota);	
	}
	
	//Nuevo
	public void actualizaNotaInformativa(NotaInformativa nota){
		try {
			dao.anyadiromodificarNotaInformativa(nota);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.remove(getNotaInformativaPorId(nota.getIdNota()));
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public NotaInformativa getNotaInformativaPorId(Integer id) throws DAOExcepcion {
		return dao.getNotaInformativa(id);
	}	

	public void borraNotaInformativaPorId(Integer id) throws DAOExcepcion{
		lista.remove(getNotaInformativaPorId(id));
		dao.borrarNotaInformativaPorId(id);		
	}
	
	public void vaciarListaNotasInformativas(){
		lista.clear();
		try {
			dao.vaciarTablaNotasInformativas();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void mostrarInforme(Integer id) {
		// TODO Auto-generated method stub
		dao.generar(id);
	}

}