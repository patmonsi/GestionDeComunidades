package interfaz;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Factura;
import dominio.LineaFactura;
import excepciones.DAOExcepcion;
import excepciones.LineaFacturaYaExiste;
import gestion.GestionLineasFactura;

@SuppressWarnings("all")
public class ModeloTablaLineaFactura extends DefaultTableModel {
	private GestionLineasFactura gestion;
	
	public ModeloTablaLineaFactura (GestionLineasFactura gestion, int numfactura) throws DAOExcepcion{
		super(null,new String[]{"Linea", "Concepto", "Importe", "Factura"});
		this.gestion=gestion;
		for (LineaFactura l:gestion.getListaLineasFactura(numfactura))
			addToTabla(l);
	}
	public void addLineaFactura (LineaFactura l) throws LineaFacturaYaExiste, DAOExcepcion{
		gestion.addLineaFactura(l);
		this.addToTabla(l);
	}
	
	//Nuevo
	public void actualizaLineaFactura (LineaFactura l, int row)throws DAOExcepcion{
		gestion.actualizaLineaFactura(l);
		this.removeRow(row);
		this.addToTabla(l);
	}
	
	public void borraLineaFacturaPorPosicion(int row) throws DAOExcepcion{
		Integer id=(Integer)getValueAt(row, 0);
		gestion.borraLineaFacturaPorId(id);
		this.removeRow(row);
	}

	public LineaFactura recuperaLineaFacturaPorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return gestion.getLineaFacturaPorId(id);
	}
	
	//NUEVO
	public int recuperaIdLineaFacturaPorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return id;
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaLineasFactura();
	}

	private void addToTabla(LineaFactura l){
		Vector v=new Vector();
		v.add(l.getIdLinea());v.add(l.getConcepto());
		v.add(l.getImportelinea());
		v.add(l.getFactura().getNumfactura());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

}