package interfaz;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Concepto;
import excepciones.DAOExcepcion;
import excepciones.ConceptoYaExiste;
import gestion.GestionConceptos;

@SuppressWarnings("all")
public class ModeloTablaConcepto extends DefaultTableModel {
	private GestionConceptos gestion;
	
	public ModeloTablaConcepto (GestionConceptos gestion) throws DAOExcepcion{
		super(null,new String[]{"Clave", "Descripcion"});
		this.gestion=gestion;
		for (Concepto c:gestion.getListaConceptos())
			addToTabla(c);
	}
	public void addConcepto (Concepto c) throws ConceptoYaExiste, DAOExcepcion{
		gestion.addConcepto(c);
		this.addToTabla(c);
	}

	//Nuevo
	public void actualizaConcepto (Concepto c, int row)throws DAOExcepcion{
		gestion.actualizaConcepto(c);
		this.removeRow(row);
		this.addToTabla(c);
	}
	
	public void borraConceptoPorPosicion(int row) throws DAOExcepcion{
		String clave=(String) getValueAt(row, 0);
		gestion.borraConceptoPorClave(clave);
		this.removeRow(row);
	}

	public Concepto recuperaConceptoPorPosicion(int row) throws DAOExcepcion {
		String clave=(String) getValueAt(row, 0);
		return gestion.getConceptoPorClave(clave);
	}
	
	//NUEVO
	public String recuperaIdConceptoPorPosicion(int row) throws DAOExcepcion {
		String clave=(String) getValueAt(row, 0);
		return clave;
	}
	
	//Nuevo
	public ArrayList<Concepto> listaConceptos() throws DAOExcepcion{
		return gestion.getListaConceptos();
	}
	

	private void addToTabla(Concepto c){
		Vector v=new Vector();
		v.add(c.getClaveconcepto());v.add(c.getDescripcion());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
}