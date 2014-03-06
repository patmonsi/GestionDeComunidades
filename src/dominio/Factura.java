package dominio;

// Generated 06-may-2013 23:18:13 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Factura generated by hbm2java
 */
@SuppressWarnings("serial")
public class Factura implements java.io.Serializable {

	private int numfactura;
	private Comunidad comunidad;
	private Date fechafactura;
	private Set<LineaFactura> lineaFacturas = new HashSet<LineaFactura>(0);
//	private Set<NotaInformativa> notaInformativas = new HashSet<NotaInformativa>(0);
	private NotaInformativa notaInformativa;
	
	public Factura() {
	}

	public Factura(int numfactura, Comunidad comunidad) {
		this.numfactura = numfactura;
		this.comunidad = comunidad;
	}

	public Factura(int numfactura, NotaInformativa notaInformativa,
			Comunidad comunidad) {
		this.numfactura = numfactura;
		this.notaInformativa = notaInformativa;
		this.comunidad = comunidad;
	}

	public Factura(int numfactura, NotaInformativa notaInformativa,
			Comunidad comunidad, Date fechafactura,
			Set<LineaFactura> lineaFacturas) {
		this.numfactura = numfactura;
		this.notaInformativa = notaInformativa;
		this.comunidad = comunidad;
		this.fechafactura = fechafactura;
		this.lineaFacturas = lineaFacturas;
	}
	
	public Factura(int numfactura, Comunidad comunidad, Date fechafactura, 
			NotaInformativa notaInformativa) {
		this.numfactura = numfactura;
		this.notaInformativa = notaInformativa;
		this.comunidad = comunidad;
		this.fechafactura = fechafactura;
	}
	
	public Factura(int numfactura, Comunidad comunidad, Date fechafactura) {
		this.numfactura = numfactura;
		this.comunidad = comunidad;
		this.fechafactura = fechafactura;
	}
	
	public Factura(Comunidad comunidad, Date fechafactura) {
		this.comunidad = comunidad;
		this.fechafactura = fechafactura;
	}
	
	public int getNumfactura() {
		return this.numfactura;
	}

	public void setNumfactura(int numfactura) {
		this.numfactura = numfactura;
	}

	public Comunidad getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Date getFechafactura() {
		return this.fechafactura;
	}

	public void setFechafactura(Date fechafactura) {
		this.fechafactura = fechafactura;
	}

	public Set<LineaFactura> getLineaFacturas() {
		return this.lineaFacturas;
	}

	public void setLineaFacturas(Set<LineaFactura> lineaFacturas) {
		this.lineaFacturas = lineaFacturas;
	}


	public NotaInformativa getNotaInformativa() {
		return this.notaInformativa;
	}

	public void setNotaInformativa(NotaInformativa notaInformativa) {
		this.notaInformativa = notaInformativa;
	}

}
