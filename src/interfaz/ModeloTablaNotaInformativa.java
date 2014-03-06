package interfaz;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Inmueble;
import dominio.NotaInformativa;
import excepciones.DAOExcepcion;
import excepciones.NotaInformativaYaExiste;
import gestion.GestionNotasInformativas;

@SuppressWarnings("all")
public class ModeloTablaNotaInformativa extends DefaultTableModel {
	private GestionNotasInformativas gestion;
	
	public ModeloTablaNotaInformativa (GestionNotasInformativas gestion) throws DAOExcepcion{
		super(null,new String[]{"Id", "Comunidad", "Fecha Calculo", "Importe"});
		this.gestion=gestion;
		for (NotaInformativa n:gestion.getListaNotasInformativas())
			addToTabla(n);
	}
	
	public void addNotaInformativa (NotaInformativa n) throws NotaInformativaYaExiste, DAOExcepcion{
		gestion.addNotaInformativa(n);
		this.addToTabla(n);
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaNotasInformativas();
	}
	
	public void borraNotaInformativaPorPosicion(int row) throws DAOExcepcion{
		Integer id=(Integer)getValueAt(row, 0);
		gestion.borraNotaInformativaPorId(id);
		this.removeRow(row);
	}
	
	public NotaInformativa recuperaNotaInformativaPorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return gestion.getNotaInformativaPorId(id);
	}
	
	private void addToTabla(NotaInformativa n){
		Vector v=new Vector();
		v.add(n.getIdNota()); v.add(n.getComunidad().getNombre());
		v.add(n.getFechacalculo());
		v.add(n.getImportenota());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	public void mostrarInforme(int row) {
		// TODO Auto-generated method stub
		Integer id=(Integer)getValueAt(row, 0);
		gestion.mostrarInforme(id);
	}
}