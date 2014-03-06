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

import accesoaDatos.InmuebleDAO;
import accesoaDatos.InmuebleDAOImp;

import dominio.Comunidad;
import dominio.Inmueble;

import excepciones.DAOExcepcion;
import excepciones.InmuebleYaExiste;

@SuppressWarnings("all")
public class GestionInmuebles{
	private InmuebleDAOImp dao;
	private ArrayList<Inmueble> lista = new ArrayList<Inmueble>();
	
	public ArrayList<Inmueble> getListaInmuebles() throws DAOExcepcion {
		for(Inmueble i : dao.getListaInmueblesDAO()) {
			lista.add(i);
		}
		return dao.getListaInmueblesDAO();
	}
	
	public GestionInmuebles() {
		dao = new InmuebleDAOImp();
		lista = new ArrayList<Inmueble>();
		
	}	

	public void setLista(ArrayList<Inmueble> lista) {
		this.lista = lista;
	}
	
	public void addInmueble(Inmueble inmueble) throws InmuebleYaExiste{
		Inmueble aux = null;
		for(Inmueble i : lista) {
			if(i.getIdInmueble() == inmueble.getIdInmueble()){ 
				throw new InmuebleYaExiste();
			}
		}
		try {
			dao.anyadiromodificarInmueble(inmueble);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(inmueble);	
	}

	//Nuevo
	public void actualizaInmueble(Inmueble inmueble){

		try {
			dao.anyadiromodificarInmueble(inmueble);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public Inmueble getInmueblePorId(int id) throws DAOExcepcion {
		return dao.getInmueble(id);
	}	


	public void borraInmueblePorId(int id) throws DAOExcepcion{
		lista.remove(getInmueblePorId(id));
		dao.borrarInmueblePorId(id);	
	}
	
	public void vaciarListaInmuebles(){
		lista.clear();
		try {
			dao.vaciarTablaInmuebles();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double sumaPorcentajes(String nombre){
		double suma=0;
		try {
			for(Inmueble i : dao.getListaInmueblesDAO()) {
				if(i.getComunidad().getNombre().equals(nombre))
					suma += i.getPorcentaje();
			}
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return suma;
	}
}