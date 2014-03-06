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

import accesoaDatos.ComunidadDAO;
import accesoaDatos.ComunidadDAOImp;

import dominio.Comunidad;
import dominio.Inmueble;
import dominio.Propietario;

import excepciones.DAOExcepcion;
import excepciones.ComunidadYaExiste;

@SuppressWarnings("all")
public class GestionComunidades{
	private ComunidadDAOImp dao;
	private ArrayList<Comunidad> lista = new ArrayList<Comunidad>();
	private ArrayList<Comunidad> listacuadrados = new ArrayList<Comunidad>();

	public ArrayList<Comunidad> getListaComunidades() throws DAOExcepcion {
		for(Comunidad c : dao.getListaComunidadesDAO()) {
			lista.add(c);
		}
		return dao.getListaComunidadesDAO();
	}
	
	public ArrayList<Comunidad> getListaComunidadesCuadrados() throws DAOExcepcion {
		listacuadrados.clear();
		for(Comunidad c : dao.getListaComunidadesDAO()) {
			if(c.getEstado().trim().equals("Cuadrado")){
				listacuadrados.add(c);
			}
		}
		return listacuadrados;
	}
	
	public GestionComunidades() {
		dao = new ComunidadDAOImp();
		lista = new ArrayList<Comunidad>();
	}	
	

	public void setLista(ArrayList<Comunidad> lista) {
		this.lista = lista;
	}
	
	public void addComunidad(Comunidad comunidad) throws ComunidadYaExiste{
		Comunidad aux = null;
		for(Comunidad c : lista) {
			if(c.getNombre().equals(comunidad.getNombre())){ 
				throw new ComunidadYaExiste();	
			}
		}
		try {
			dao.anyadiromodificarComunidad(comunidad);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(comunidad);	
	}
	
	//Nuevo
	public void actualizaComunidad(Comunidad comunidad){

		try {
			dao.anyadiromodificarComunidad(comunidad);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public Comunidad getComunidadPorNombre(String nombre) throws DAOExcepcion {
		return dao.getComunidad(nombre);
	}	

	//Nuevo
	public void mostrarInforme(String nombre){
		dao.run(nombre);
	}	

	public void borraComunidadPorId(String nombre) throws DAOExcepcion{
		lista.remove(getComunidadPorNombre(nombre));
		dao.borrarComunidadPorNombre(nombre);		
	}

	public void vaciarListaComunidades(){
		lista.clear();
		try {
			dao.vaciarTablaComunidades();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}