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

import accesoaDatos.FacturaDAO;
import accesoaDatos.FacturaDAOImp;

import dominio.Factura;
import dominio.LineaFactura;

import excepciones.DAOExcepcion;
import excepciones.FacturaYaExiste;

@SuppressWarnings("all")
public class GestionFacturas{
	private FacturaDAOImp dao;
	private ArrayList<Factura> lista = new ArrayList<Factura>();
	

	public ArrayList<Factura> getListaFacturas() throws DAOExcepcion {
		for(Factura f : dao.getListaFacturasDAO()) {
			lista.add(f);
		}
		return dao.getListaFacturasDAO();
	}
	
	public ArrayList<Factura> getListaFacturas(String comunidad) throws DAOExcepcion {
		for(Factura f : dao.getListaFacturasDAO()) {
			if(f.getNotaInformativa()==null && f.getComunidad().getNombre().equals(comunidad))
				lista.add(f);
		}
		return lista;
	}
	
	public GestionFacturas() {
		dao = new FacturaDAOImp();
		lista = new ArrayList<Factura>();
		
	}	

	public void setLista(ArrayList<Factura> lista) {
		this.lista = lista;
	}
	
	public void addFactura(Factura factura) throws FacturaYaExiste{
		Factura aux = null;
	
		for(Factura f : lista) {
			if(f.getNumfactura() == factura.getNumfactura()){ 
				System.out.println("Factura ya existe\n."); 
				throw new FacturaYaExiste();
				
			}
		}
		try {
			dao.anyadiromodificarFactura(factura);
			
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(factura);	
	}

	//Nuevo
	public void actualizaFactura(Factura factura){

		try {
			dao.anyadiromodificarFactura(factura);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public Factura getFacturaPorId(int id) throws DAOExcepcion {
		return dao.getFactura(id);
	}	

	public void borraFacturaPorId(int id) throws DAOExcepcion{
		
		lista.remove(getFacturaPorId(id));
		dao.borrarFacturaPorId(id);	

	}
	
	public void vaciarListaFacturas(){
		lista.clear();
		try {
			dao.vaciarTablaFacturas();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}