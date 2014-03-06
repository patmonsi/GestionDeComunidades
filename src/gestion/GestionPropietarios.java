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

import accesoaDatos.PropietarioDAO;
import accesoaDatos.PropietarioDAOImp;

import dominio.Inmueble;
import dominio.Propietario;

import excepciones.DAOExcepcion;
import excepciones.PropietarioYaExiste;

@SuppressWarnings("all")
public class GestionPropietarios{
	private PropietarioDAOImp dao;
	private ArrayList<Propietario> lista = new ArrayList<Propietario>();

	public ArrayList<Propietario> getListaPropietarios() throws DAOExcepcion {
		for(Propietario p : dao.getListaPropietariosDAO()) {
			lista.add(p);
		}
		return dao.getListaPropietariosDAO();
	}
	
	public String getNIFPropietarioPorNombre(String nombre)throws DAOExcepcion{
		String aux = null;
		for(Propietario p : lista) {
			if(p.getNombre().equals(nombre)){ 
				aux=p.getNif();
			}
		}
		return aux;	
	}
	
	public GestionPropietarios() {
		dao = new PropietarioDAOImp();
		lista = new ArrayList<Propietario>();
	}	

	public void setLista(ArrayList<Propietario> lista) {
		this.lista = lista;
	}
	
	public void addPropietario(Propietario Propietario) throws PropietarioYaExiste{
		Propietario aux = null;
		for(Propietario p : lista) {
			if(p.getNif().equals(Propietario.getNif())){ 
				throw new PropietarioYaExiste();	
			}
		}
		try {
			dao.anyadiromodificarPropietario(Propietario);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(Propietario);	
	}
	
	//Nuevo
	public void actualizaPropietario(Propietario propietario){
		try {
			dao.anyadiromodificarPropietario(propietario);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.remove(getPropietarioPorNIF(propietario.getNif()));
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

	public Propietario getPropietarioPorNIF(String nif) throws DAOExcepcion {
		return dao.getPropietario(nif);
	}	

	public void borraPropietarioPorNIF(String nif) throws DAOExcepcion{
		lista.remove(getPropietarioPorNIF(nif));
		dao.borrarPropietarioPorNIF(nif);		
	}
	
	public void vaciarListaPropietarios(){
		lista.clear();
		try {
			dao.vaciarTablaPropietarios();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarInforme() {
		// TODO Auto-generated method stub
		dao.generar();
	}

	public void mostrarListadoRecibos(String nif) {
		// TODO Auto-generated method stub
		Date fecha = new Date ();
		dao.generarListadoRecibos(nif, fecha);
	}

}

