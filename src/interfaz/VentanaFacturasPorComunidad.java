package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import dominio.Comunidad;
import dominio.Concepto;
import dominio.Factura;
import dominio.Inmueble;
import dominio.LineaFactura;
import dominio.NotaInformativa;
import dominio.Propietario;
import dominio.ReciboInmueble;
import excepciones.ComunidadYaExiste;
import excepciones.DAOExcepcion;
import excepciones.FacturaYaExiste;
import excepciones.InmuebleYaExiste;
import excepciones.NotaInformativaYaExiste;
import excepciones.ReciboYaExiste;
import gestion.GestionFacturas;
import gestion.GestionLineasFactura;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

// En vez de JFrame lo hemos creado JDialog para que pueda ser modal
@SuppressWarnings({ "serial", "unused" })
public class VentanaFacturasPorComunidad extends JDialog {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private JPanel contentPane;
	private JPanel botonesPanel;
	private JButton jBSalir;
	private JButton jBLineaNueva;
	private JButton jBGenerar;
	private JLabel conceptoLabel;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	private ModeloTablaFactura modelotablafactura;
	private ModeloTablaLineaFactura modelotablalineafactura;
	private ModeloTablaComunidadCuadrada modelotablacomunidadcuadrada;
	private ModeloTablaInmueble modelotablainmueble;
	private ModeloTablaRecibo modelotablarecibo;
	private ModeloTablaNotaInformativa modelotablanotainformativa;
	private JTable tablaFactura;
	private GestionFacturas gestionfactura=new GestionFacturas();
	
	Factura factura;
	Comunidad comunidad;
	ReciboInmueble recibo;
	NotaInformativa notainformativa;
	
	JLabel accion;
	private boolean modificar=false;
	private boolean factura_nueva=true;
	private int row;
	
	private boolean ins=false;
	

	private JScrollPane tablaScrollPane;
	
	private ArrayList<Comunidad> listaComunidades;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDetalle frame = new VentanaDetalle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param tableModel 
	 * @throws DAOExcepcion 
	 */
	
	
	public VentanaFacturasPorComunidad(ModeloTablaComunidadCuadrada modelocomunidad, Comunidad comunidad, JLabel accion, ModeloTablaRecibo modelotablarecibo, ModeloTablaInmueble modelotablainmueble, ModeloTablaNotaInformativa modelotablanotainformativa) throws DAOExcepcion {
		super();
		this.accion=accion;
		this.modelotablacomunidadcuadrada=modelocomunidad;
		this.modelotablarecibo=modelotablarecibo;
		this.modelotablainmueble=modelotablainmueble;
		this.modelotablanotainformativa=modelotablanotainformativa;
		this.comunidad=comunidad;
		

		modelotablafactura = new ModeloTablaFactura(gestionfactura, comunidad.getNombre());
		
		Detalle();
		setModal(true);
	}
	
	public VentanaFacturasPorComunidad() throws DAOExcepcion {
//		modelotablalineafactura = new ModeloTablaLineaFactura(gestionlineasfactura);
		Detalle();
	}

	
	private void Detalle() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(200, 150, 600, 450);
		
		this.setTitle("Datos Factura");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		contentPane.setPreferredSize(new java.awt.Dimension(484, 380));
		{
			botonesPanel = new JPanel();
			contentPane.add(botonesPanel, BorderLayout.SOUTH);

			{
				jBSalir = new JButton();
				botonesPanel.add(jBSalir);
				jBSalir.setText("Salir");
				jBSalir.setMnemonic(java.awt.event.KeyEvent.VK_S);
				jBSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jBSalirActionPerformed(evt);
					}
				});
			}
			
			{
				jBGenerar = new JButton();
				botonesPanel.add(jBGenerar);
				jBGenerar.setText("Generar Recibos");
				jBGenerar.setMnemonic(java.awt.event.KeyEvent.VK_G);
				jBGenerar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						
						try {
							jBGenerarActionPerformed(evt);
						} catch (DAOExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ReciboYaExiste e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
			}
		}
		
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.NORTH);
			
			tablaScrollPane = new JScrollPane();
			panel.add(tablaScrollPane, BorderLayout.CENTER);
			{
				tablaFactura = new JTable();
				tablaScrollPane.setViewportView(tablaFactura);
				tablaFactura.setModel(modelotablafactura);
			}
			
			tablaScrollPane.setPreferredSize(new java.awt.Dimension(550, 300));
			// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
			tablaFactura.getModel().addTableModelListener(new TableModelListener() {
			      public void tableChanged(TableModelEvent e) {
			      }
			    });
		}
		
	}
	
	protected void jBGenerarActionPerformed(ActionEvent evt) throws DAOExcepcion, ReciboYaExiste {
		// TODO Auto-generated method stub
		if(tablaFactura.getSelectedRowCount()==0){
			JOptionPane.showMessageDialog(
					   contentPane, 
					   "Debe seleccionar al menos una factura para poder generar una nota y los recibos para cada inmueble.",
					   "Aviso",
					   JOptionPane.WARNING_MESSAGE);
		}
		
		else{
		Double total=0.0;
		for(int i=tablaFactura.getSelectedRows().length-1; i>=0; i--){
			total+=(Double)modelotablafactura.getValueAt(tablaFactura.getSelectedRows()[i], 3);
		}
		Date fecha = new Date ();
		
		notainformativa = new NotaInformativa(comunidad, fecha, total);
		try {
			modelotablanotainformativa.addNotaInformativa(notainformativa);
		} catch (NotaInformativaYaExiste e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=tablaFactura.getSelectedRows().length-1; i>=0; i--){
			modelotablafactura.recuperaFacturaPorPosicion(tablaFactura.getSelectedRows()[i]).setNotaInformativa(notainformativa);
			factura=new Factura(modelotablafactura.recuperaFacturaPorPosicion(tablaFactura.getSelectedRows()[i]).getNumfactura(), modelotablafactura.recuperaFacturaPorPosicion(tablaFactura.getSelectedRows()[i]).getComunidad(), modelotablafactura.recuperaFacturaPorPosicion(tablaFactura.getSelectedRows()[i]).getFechafactura(), notainformativa);
			modelotablafactura.actualizaFactura(factura, tablaFactura.getSelectedRows()[i]);
		}
		
		for(Inmueble i:modelotablainmueble.listarInmuebles()){
			if(i.getComunidad().getNombre().trim().equals(comunidad.getNombre().trim())){
				recibo = new ReciboInmueble(i, total*i.getPorcentaje()/100, notainformativa);
				modelotablarecibo.addRecibo(recibo);
				accion.setText("Recibos creados con exito");
			}
		}
		
		System.out.println("Recibos creados con exito.");
		dispose();
		}
		
	}


	
	private void jBSalirActionPerformed(ActionEvent evt) {
		System.out.println("jBVolver.actionPerformed, event="+evt);
		Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		accion.setText("Fecha: " +formateador.format(ahora));
		dispose();
	}
	
}