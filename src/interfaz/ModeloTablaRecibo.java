package interfaz;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import dominio.ReciboInmueble;
import excepciones.DAOExcepcion;
import excepciones.ReciboYaExiste;
import gestion.GestionRecibos;

@SuppressWarnings("all")
public class ModeloTablaRecibo extends DefaultTableModel {
	private GestionRecibos gestion;
	
	public ModeloTablaRecibo (GestionRecibos gestion) throws DAOExcepcion{
		super(null,new String[]{"Id", "Comunidad", "Inmueble", "Nota", "Importe", "Fecha Pago"});
		this.gestion=gestion;
		for (ReciboInmueble r:gestion.getListaRecibos())
			addToTabla(r);
	}
	
	public void addRecibo (ReciboInmueble r) throws ReciboYaExiste, DAOExcepcion{
		gestion.addRecibo(r);
		this.addToTabla(r);
	}
	
	//Nuevo
	public void actualizaRecibo (ReciboInmueble r, int row)throws DAOExcepcion{
		gestion.actualizaRecibo(r);
		this.removeRow(row);
		this.addToTabla(r);
	}
	
	public void borraReciboPorPosicion(int row) throws DAOExcepcion{
		Integer id=(Integer)getValueAt(row, 0);
		gestion.borraReciboPorId(id);
		this.removeRow(row);
	}
	
	//Nuevo
	public void borraRecibosPorIdNota(int idNota) throws DAOExcepcion{
		for (ReciboInmueble r:gestion.getListaRecibos()){
			if(r.getNotaInformativa().getIdNota()==idNota){
				int row;
				for(row=0;row<this.getRowCount();row++){
					if(r.getIdRecibo().intValue()==((Integer)this.getValueAt(row, 0)).intValue()){
						this.removeRow(row);
					}
				}
				gestion.borraReciboPorId(r.getIdRecibo());
			}
		}
	}

	public ReciboInmueble recuperaReciboPorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return gestion.getReciboPorId(id);
	}
	
	//NUEVO
	public Integer recuperaIdReciboPorPosicion(int row) throws DAOExcepcion {
		Integer id=(Integer)getValueAt(row, 0);
		return id;
	}
	
	public void vaciarModelo() {
		// TODO Auto-generated method stub
		this.getDataVector().clear();
		gestion.vaciarListaRecibos();
	}
	
	private void addToTabla(ReciboInmueble r){
		Vector v=new Vector();
		v.add(r.getIdRecibo()); v.add(r.getInmueble().getComunidad());
		v.add(r.getInmueble().getIdInmueble()); v.add(r.getNotaInformativa().getIdNota());
		v.add(r.getImporte()); v.add(r.getFechapago());
		this.addRow(v);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		return false;
	}

	public void marcarComoPagado(int row) throws DAOExcepcion {
		// TODO Auto-generated method stub
		Date fecha = new Date ();
		ReciboInmueble r=this.recuperaReciboPorPosicion(row);
		r.setFechapago(fecha);
		this.actualizaRecibo(r, row);
	}
	
	public void mostrarInforme(int row) throws DAOExcepcion {
		// TODO Auto-generated method stub
		Integer id=(Integer)getValueAt(row, 0);
		ReciboInmueble r=this.recuperaReciboPorPosicion(row);
		gestion.mostrarInforme(id, r.getNotaInformativa().getFechacalculo(), r.getFechapago());
	}

	public boolean comprobarInmuebles(int idInmueble) throws DAOExcepcion {
		// TODO Auto-generated method stub
		Boolean presente=false;
		for (ReciboInmueble r:gestion.getListaRecibos()){
			if(r.getIdRecibo()==idInmueble)
				presente=true;
		}
		return presente;
	}
	
}