package interfaz;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.Concepto;
import dominio.Factura;
import dominio.LineaFactura;
import excepciones.DAOExcepcion;
import excepciones.FacturaYaExiste;
import gestion.GestionFacturas;

@SuppressWarnings("all")
public class ModeloTablaFactura extends DefaultTableModel {
	private GestionFacturas gestion;
		
	public ModeloTablaFactura (GestionFacturas gestion) throws DAOExcepcion{
		super(null,new String[]{"numFactura", "Comunidad", "Fecha", "Importe Total"});
		this.gestion=gestion;
		
		for (Factura f:gestion.getListaFacturas()){
			double total=0.0;
			for (LineaFactura l : f.getLineaFacturas()){
				total+=l.getImportelinea();
			}
			addToTabla(f,total);
		}
	}
	
	public ModeloTablaFactura (GestionFacturas gestion, String comunidad) throws DAOExcepcion{
		super(null,new String[]{"numFactura", "Comunidad", "Fecha", "Importe Total"});
		this.gestion=gestion;
		
		for (Factura f:gestion.getListaFacturas(comunidad)){
			double total=0.0;
			for (LineaFactura l : f.getLineaFacturas()){
				total+=l.getImportelinea();
			}
			addToTabla(f,total);
		}
	}
	
	public void addFactura (Factura f) throws FacturaYaExiste, DAOExcepcion{
		gestion.addFactura(f);
		double total=0.0;
		for (LineaFactura l : f.getLineaFacturas()){
			total+=l.getImportelinea();
		}
		this.addToTabla(f, total);
	}
	
	//Nuevo
	public void actualizaFactura (Factura f, int row)throws DAOExcepcion{
		gestion.actualizaFactura(f);
		this.removeRow(row);
		double total=0.0;
		for (LineaFactura l :gestion.getFacturaPorId(f.getNumfactura()).getLineaFacturas()){
			total+=l.getImportelinea();
		}
		this.addToTabla(f, total);
	}
	
	//Nuevo
	public void actualizarFacturaSinNotaInformativa (int idNota)throws DAOExcepcion{
		
		for (Factura f:gestion.getListaFacturas()){
			
			if(f.getNotaInformativa()!=null && f.getNotaInformativa().getIdNota()==idNota){
				f.setNotaInformativa(null);
				gestion.actualizaFactura(f);
			}
		}
	}
	
	public void borraFacturaPorPosicion(int row) throws DAOExcepcion{
		Integer id=(Integer)getValueAt(row, 0);
		gestion.borraFacturaPorId(id);
		this.removeRow(row);
	}
	
	public Factura recuperaFacturaPorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return gestion.getFacturaPorId(id);
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaFacturas();
	}
	
	private void addToTabla(Factura f, double total){
		Vector v=new Vector();
		v.add(f.getNumfactura()); 
		v.add(f.getComunidad().getNombre());
		v.add(f.getFechafactura()); 
		v.add(total);
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}
}