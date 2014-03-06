package interfaz;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Comunidad;
import dominio.Inmueble;
import dominio.Propietario;
import excepciones.DAOExcepcion;
import excepciones.InmuebleYaExiste;
import gestion.GestionInmuebles;

@SuppressWarnings("all")
public class ModeloTablaInmueble extends DefaultTableModel {
	private GestionInmuebles gestion;
	private ArrayList<Propietario> listapropietarios = new ArrayList<Propietario>();
	
	public ModeloTablaInmueble (GestionInmuebles gestion) throws DAOExcepcion{
		super(null,new String[]{"Id", "Comunidad", "Escalera", "Piso", " Puerta", "Propietario", "Porcentaje"});
		this.gestion=gestion;
		for (Inmueble i:gestion.getListaInmuebles())
			addToTabla(i);
	}
	
	//Nuevo
	public ArrayList<Propietario> listarPropietariosPorComunidad (Comunidad c) throws DAOExcepcion{
		listapropietarios.clear();
		for (Inmueble i:c.getInmuebles()){
				listapropietarios.add(i.getPropietario());
		}
		return listapropietarios;
	}
	
	
	public void addInmueble (Inmueble i) throws InmuebleYaExiste, DAOExcepcion{
		gestion.addInmueble(i);
		this.addToTabla(i);
	}
	
	//Nuevo
	public void actualizaInmueble (Inmueble i, int row)throws DAOExcepcion{
		gestion.actualizaInmueble(i);
		this.removeRow(row);
		this.addToTabla(i);
	}
	
	public void borraInmueblePorPosicion(int row) throws DAOExcepcion{
		Integer id=(Integer)getValueAt(row, 0);
		gestion.borraInmueblePorId(id);
		this.removeRow(row);
	}

	public boolean ComprobarPropietariosPorNIF(String nif)throws DAOExcepcion{
		
		for (Inmueble i:gestion.getListaInmuebles()){
			if(i.getPropietario().getNif().equals(nif))
				return true;
		}
		
		return false;
	}
	
	public boolean ComprobarComunidadesPorNombre(String nombre)throws DAOExcepcion{
		
		for (Inmueble i:gestion.getListaInmuebles()){
			if(i.getComunidad().getNombre().equals(nombre))
				return true;
		}
		
		return false;
	}
	
	public Inmueble recuperaInmueblePorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return gestion.getInmueblePorId(id);
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaInmuebles();
	}
	
	private void addToTabla(Inmueble i){
		Vector v=new Vector();
		v.add(i.getIdInmueble());
		v.add(i.getComunidad().getNombre());
		v.add(i.getEscalera());
		v.add(i.getPiso()); v.add(i.getPuerta());
		v.add(i.getPropietario().getNombre());
		v.add(i.getPorcentaje());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public double sumaPorcentajes(String nombre){
		return gestion.sumaPorcentajes(nombre);
	}

	//Nuevo
	public ArrayList<Inmueble> listarInmuebles () throws DAOExcepcion{
		return gestion.getListaInmuebles();
	}
}