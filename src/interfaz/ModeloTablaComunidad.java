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
public class ModeloTablaComunidad extends DefaultTableModel {
	private GestionComunidades gestion;
	
	public ModeloTablaComunidad (GestionComunidades gestion) throws DAOExcepcion{
		super(null,new String[]{"Nombre", "Calle", "Max recibos pendientes", "Estado", "Presidente"});
		this.gestion=gestion;
		for (Comunidad c:gestion.getListaComunidades())
			addToTabla(c);
	}
	public void addComunidad (Comunidad c) throws ComunidadYaExiste, DAOExcepcion{
		gestion.addComunidad(c);
		this.addToTabla(c);
	}
	
	//Nuevo
	public void actualizaComunidad (Comunidad c, int row)throws DAOExcepcion{
		gestion.actualizaComunidad(c);
		this.removeRow(row);
		this.addToTabla(c);
	}
	
	public void borraComunidadPorPosicion(int row) throws DAOExcepcion{
		String nombre=(String)getValueAt(row, 0);
		gestion.borraComunidadPorId(nombre);
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
	public ArrayList<Comunidad> listaComunidades() throws DAOExcepcion{
		return gestion.getListaComunidades();
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
	public void actualizaComunidad(Comunidad comunidad) {
		// TODO Auto-generated method stub
		gestion.actualizaComunidad(comunidad);
		
		int row;
		for(row=0;row<this.getRowCount();row++){
			if(comunidad.getNombre().trim().equals(((String)this.getValueAt(row, 0)).trim())){
				this.removeRow(row);
			}
		}
		this.addToTabla(comunidad);
	}

}