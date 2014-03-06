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
import java.util.Date;
import java.util.Iterator;

import accesoaDatos.ReciboDAO;
import accesoaDatos.ReciboDAOImp;

import dominio.ReciboInmueble;

import excepciones.DAOExcepcion;
import excepciones.ReciboYaExiste;

@SuppressWarnings("all")
public class GestionRecibos{
	private ReciboDAOImp dao;
	private ArrayList<ReciboInmueble> lista = new ArrayList<ReciboInmueble>();

	public ArrayList<ReciboInmueble> getListaRecibos() throws DAOExcepcion {
		for(ReciboInmueble r : dao.getListaRecibosDAO()) {
			lista.add(r);
		}
		return dao.getListaRecibosDAO();
	}
	
	public GestionRecibos() {
		dao = new ReciboDAOImp();
		lista = new ArrayList<ReciboInmueble>();
	}	

	public void setLista(ArrayList<ReciboInmueble> lista) {
		this.lista = lista;
	}
	
	public void addRecibo(ReciboInmueble recibo) throws ReciboYaExiste{
		ReciboInmueble aux = null;
		for(ReciboInmueble r : lista) {
			if(r.getIdRecibo()==recibo.getIdRecibo()){ 
				throw new ReciboYaExiste();	
			}
		}
		try {
			dao.anyadiromodificarRecibo(recibo);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(recibo);	
	}
	
	//Nuevo
	public void actualizaRecibo(ReciboInmueble recibo){

		try {
			dao.anyadiromodificarRecibo(recibo);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.remove(getReciboPorId(recibo.getIdRecibo()));
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

	public ReciboInmueble getReciboPorId(Integer id) throws DAOExcepcion {
		return dao.getRecibo(id);
	}	

	public void borraReciboPorId(Integer id) throws DAOExcepcion{
		lista.remove(getReciboPorId(id));
		dao.borrarReciboPorId(id);		
	}
	
	public void vaciarListaRecibos(){
		lista.clear();
		try {
			dao.vaciarTablaRecibos();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarInforme(Integer id, Date fecha, Date fechapago) {
		// TODO Auto-generated method stub
		dao.generar(id, fecha, fechapago);
	}

}