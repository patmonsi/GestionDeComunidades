package interfaz;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Comunidad;
import dominio.Inmueble;
import dominio.Propietario;
import excepciones.DAOExcepcion;
import excepciones.ComunidadYaExiste;
import gestion.GestionComunidades;

@SuppressWarnings("all")
public class ModeloTablaComunidadCuadrada extends DefaultTableModel {
	private GestionComunidades gestion;
	
	public ModeloTablaComunidadCuadrada (GestionComunidades gestion) throws DAOExcepcion{
		super(null,new String[]{"Comunidad", "Calle", "Max recibos pendientes", "Estado", "Presidente"});
		this.gestion=gestion;
		for (Comunidad c:gestion.getListaComunidadesCuadrados())
			addToTabla(c);
	}
		
	public void addComunidad (Comunidad c) throws ComunidadYaExiste, DAOExcepcion{
		boolean anyadir=true;
		for(int row=0;row<this.getRowCount();row++){
			if(c.getNombre().equals(this.getValueAt(row, 0)))
				anyadir=false;
		}
		if(anyadir)
			this.addToTabla(c);
	}

	//Nuevo
	public void actualizaComunidad (Comunidad c, int row)throws DAOExcepcion{
		this.removeRow(row);
		this.addToTabla(c);
	}
	
	//Nuevo
	public void borraComunidad(Comunidad c) throws DAOExcepcion{
		int row;
		for(row=0;row<this.getRowCount();row++){
			if(c.getNombre().equals(this.getValueAt(row, 0)))
				this.removeRow(row);
		}
	}
	
	public void borraComunidadPorPosicion(int row) throws DAOExcepcion{
		String nombre=(String)getValueAt(row, 0);
//		gestion.borraComunidadPorId(nombre);
		this.removeRow(row);
	}

	public Comunidad recuperaComunidadPorPosicion(int row) throws DAOExcepcion {
		String nombre=(String)getValueAt(row, 0);
		return gestion.getComunidadPorNombre(nombre);
	}
	
	//NUEVO
	public String recuperaIdComunidadPorPosicion(int row) throws DAOExcepcion {
		String nombre=(String)getValueAt(row, 0);
		return nombre;
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaComunidades();
	}
	

	//Nuevo
	public void mostrarInforme(int row){
		String nombre=(String)getValueAt(row, 0);
		gestion.mostrarInforme(nombre);
	}
	
	private void addToTabla(Comunidad c){
		Vector v=new Vector();
		v.add(c.getNombre());v.add(c.getCalle());
		v.add(c.getMaxRecibosPendientes());v.add(c.getEstado());
		v.add(c.getPresidente());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
}