package interfaz;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Propietario;
import excepciones.DAOExcepcion;
import excepciones.PropietarioYaExiste;
import gestion.GestionPropietarios;

@SuppressWarnings("all")
public class ModeloTablaPropietario extends DefaultTableModel {
	private GestionPropietarios gestion;
	
	public ModeloTablaPropietario (GestionPropietarios gestion) throws DAOExcepcion{
		super(null,new String[]{"NIF", "Nombre", "Direccion", "Fecha alta"});
		this.gestion=gestion;
		for (Propietario p:gestion.getListaPropietarios())
			addToTabla(p);
	}
	
	//Nuevo
	public ArrayList<Propietario> listaNombresPropietarios () throws DAOExcepcion{
		return gestion.getListaPropietarios();
	}
	
	//Nuevo
	public String ObtenerIdPropietarioPorNombre(String nombre)throws DAOExcepcion{
		return gestion.getNIFPropietarioPorNombre(nombre);
	}
	
	public void addPropietario (Propietario p) throws PropietarioYaExiste, DAOExcepcion{
		gestion.addPropietario(p);
		this.addToTabla(p);
	}
	
	//Nuevo
	public void actualizaPropietario (Propietario p, int row)throws DAOExcepcion{
		gestion.actualizaPropietario(p);
		this.removeRow(row);
		this.addToTabla(p);
	}
	
	public void borraPropietarioPorPosicion(int row) throws DAOExcepcion{
		String nif=(String)getValueAt(row, 0);
		gestion.borraPropietarioPorNIF(nif);
		this.removeRow(row);
	}

	public Propietario recuperaPropietarioPorPosicion(int row) throws DAOExcepcion {
		String nif=(String)getValueAt(row, 0);
		return gestion.getPropietarioPorNIF(nif);
	}
	
	//NUEVO
	public String recuperaNIFPropietarioPorPosicion(int row) throws DAOExcepcion {
		String nif=(String)getValueAt(row, 0);
		return nif;
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaPropietarios();
	}
	
	private void addToTabla(Propietario p){
		Vector v=new Vector();
		v.add(p.getNif()); v.add(p.getNombre());
		v.add(p.getDireccion()); v.add(p.getFechaAlta());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void mostrarInforme() {
		// TODO Auto-generated method stub
		gestion.mostrarInforme();
	}

	public void mostrarListadoRecibos(String nif) {
		// TODO Auto-generated method stub
		gestion.mostrarListadoRecibos(nif);
	}
}