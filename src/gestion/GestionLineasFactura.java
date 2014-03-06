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

import accesoaDatos.LineaFacturaDAO;
import accesoaDatos.LineaFacturaDAOImp;

import dominio.LineaFactura;

import excepciones.DAOExcepcion;
import excepciones.LineaFacturaYaExiste;

@SuppressWarnings("all")
public class GestionLineasFactura{
	private LineaFacturaDAOImp dao;
	private ArrayList<LineaFactura> lista = new ArrayList<LineaFactura>();

	public ArrayList<LineaFactura> getListaLineasFactura(int numfactura) throws DAOExcepcion {
		for(LineaFactura l : dao.getListaLineasFacturaDAO(numfactura)) {
			lista.add(l);
		}
		return dao.getListaLineasFacturaDAO(numfactura);
	}
	
	public boolean comprobarConceptos(String concepto) throws DAOExcepcion {
		// TODO Auto-generated method stub
		Boolean presente=false;
		for(LineaFactura l : dao.getListaLineasFacturaDAO()){
			if(l.getConcepto()!=null && l.getConcepto().getClaveconcepto().equals(concepto))
				presente=true;
		}
		return presente;
	}
	
	public GestionLineasFactura() {
		dao = new LineaFacturaDAOImp();
		lista = new ArrayList<LineaFactura>();
	}	
	
	public void setLista(ArrayList<LineaFactura> lista) {
		this.lista = lista;
	}
	
	public void addLineaFactura(LineaFactura lineafactura) throws LineaFacturaYaExiste{
		LineaFactura aux = null;
		for(LineaFactura l : lista) {
			if(l.getIdLinea() == lineafactura.getIdLinea()){ 
				throw new LineaFacturaYaExiste();	
			}
		}
		try {
			dao.anyadiromodificarLineaFactura(lineafactura);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lista.add(lineafactura);	
	}
	
	//Nuevo
	public void actualizaLineaFactura(LineaFactura lineafactura){
		try {
			dao.anyadiromodificarLineaFactura(lineafactura);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.remove(getLineaFacturaPorId(lineafactura.getIdLinea()));
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	public LineaFactura getLineaFacturaPorId(int id) throws DAOExcepcion {
		return dao.getLineaFactura(id);
	}	

	public void borraLineaFacturaPorId(int id) throws DAOExcepcion{
		lista.remove(getLineaFacturaPorId(id));
		dao.borrarLineaFacturaPorId(id);		
	}

	public void vaciarListaLineasFactura(){
		lista.clear();
		try {
			dao.vaciarTablaLineasFactura();
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}