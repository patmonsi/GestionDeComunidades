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
import dominio.Propietario;
import excepciones.ComunidadYaExiste;
import excepciones.DAOExcepcion;
import excepciones.FacturaYaExiste;
import excepciones.InmuebleYaExiste;
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
public class VentanaFacturas extends JDialog {

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
	private JButton jBBorrar;
	private JButton jBModificar;
	private JLabel conceptoLabel;
	private JComboBox jComboBoxComunidades;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	ModeloTablaFactura modelotablafactura;
	private ModeloTablaLineaFactura modelotablalineafactura;
	private ModeloTablaComunidad modelotablacomunidad;
	private ModeloTablaConcepto modelotablaconcepto;
	private JTable tablaLineasFactura;
	private GestionLineasFactura gestionlineasfactura=new GestionLineasFactura();
	
	Factura factura;
	Concepto concepto;
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
	
	
	public VentanaFacturas(ModeloTablaFactura modelofactura, ModeloTablaComunidad modelocomunidad, JLabel accion, ModeloTablaConcepto modelotablaconcepto, int row) throws DAOExcepcion {
		super();
		this.modelotablafactura=modelofactura;
		this.accion=accion;
		this.modelotablacomunidad=modelocomunidad;
		this.modelotablaconcepto=modelotablaconcepto;
		this.row=row;

		modelotablalineafactura = new ModeloTablaLineaFactura(gestionlineasfactura, 0);
		
		Detalle();
		setModal(true);
	}
	
	public VentanaFacturas() throws DAOExcepcion {
		Detalle();
	}
	
	public VentanaFacturas(Factura f, ModeloTablaFactura modelofactura, ModeloTablaComunidad modelocomunidad, JLabel accion, int row, ModeloTablaConcepto modelotablaconcepto) throws DAOExcepcion {
		super();
		modelotablalineafactura = new ModeloTablaLineaFactura(gestionlineasfactura, f.getNumfactura());
		
		this.accion=accion;
		this.modelotablafactura=modelofactura;
		this.modelotablacomunidad=modelocomunidad;
		this.modelotablaconcepto=modelotablaconcepto;
		this.row=row;
		this.factura=f;
		
		Detalle();
		setModal(true);
		
		jComboBoxComunidades.setSelectedItem(f.getComunidad());
		
		modificar=true;
		factura_nueva=false;
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
				jBGuardar = new JButton();
				botonesPanel.add(jBGuardar);
				jBGuardar.setText("Guardar");
				jBGuardar.setMnemonic(java.awt.event.KeyEvent.VK_G);
				jBGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							jBGuardarActionPerformed(evt);
						} catch (DAOExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FacturaYaExiste e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
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
				jBLineaNueva = new JButton();
				botonesPanel.add(jBLineaNueva);
				jBLineaNueva.setText("Añadir Linea");
				jBLineaNueva.setMnemonic(java.awt.event.KeyEvent.VK_A);
				jBLineaNueva.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							jBAñadirActionPerformed(evt);
						} catch (FacturaYaExiste e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DAOExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			
			{
				jBBorrar = new JButton();
				botonesPanel.add(jBBorrar);
				jBBorrar.setText("Borrar Linea");
				jBBorrar.setMnemonic(java.awt.event.KeyEvent.VK_B);
				jBBorrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							jBBorrarActionPerformed(evt);
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DAOExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
			
			{
				jBModificar = new JButton();
				botonesPanel.add(jBModificar);
				jBModificar.setText("Modificar");
				jBModificar.setMnemonic(java.awt.event.KeyEvent.VK_M);
				jBModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							jBModificarActionPerformed(evt);
						} catch (DAOExcepcion e) {
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
				tablaLineasFactura = new JTable();
				tablaScrollPane.setViewportView(tablaLineasFactura);
				tablaLineasFactura.setModel(modelotablalineafactura);
			}
			
			tablaScrollPane.setPreferredSize(new java.awt.Dimension(550, 300));
			// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
			tablaLineasFactura.getModel().addTableModelListener(new TableModelListener() {
			      public void tableChanged(TableModelEvent e) {
		
			      }
			    });
			
		}
		
		{
			formularioPanel = new JPanel();
			GridBagLayout formularioPanelLayout = new GridBagLayout();
			contentPane.add(formularioPanel, BorderLayout.CENTER);
			formularioPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1 };
			formularioPanelLayout.rowHeights = new int[] {40, 40, 40 };
			formularioPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			formularioPanel.setLayout(formularioPanelLayout);
			{
				conceptoLabel = new JLabel();
				formularioPanel.add(conceptoLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				conceptoLabel.setText("Comunidad: ");
			}
			{
				
				try {
					listaComunidades=modelotablacomunidad.listaComunidades();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jComboBoxComunidades = new JComboBox();
				formularioPanel.add(jComboBoxComunidades, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jComboBoxComunidades.addItem("Comunidad");
				for(int i=0; i<listaComunidades.size();i++)
					jComboBoxComunidades.addItem(listaComunidades.get(i));
				    
			}
		}
	}
	
	protected void jBModificarActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub

		if(tablaLineasFactura.getSelectedRowCount()==1){
			LineaFactura l= modelotablalineafactura.recuperaLineaFacturaPorPosicion(tablaLineasFactura.getSelectedRow());
			
			VentanaLineasFactura v=new VentanaLineasFactura(l, factura, (ModeloTablaLineaFactura) modelotablalineafactura, (ModeloTablaConcepto) modelotablaconcepto, accion, tablaLineasFactura.getSelectedRow());
			accion.setText("Visualizando los detalles de la Linea de Factura "+ l.getIdLinea());
			v.setVisible(true);
		}
		else{
		JOptionPane.showMessageDialog(
				contentPane, 
				"Debe seleccionar una linea.",
				"Aviso",
				JOptionPane.INFORMATION_MESSAGE);
		}

	modelotablalineafactura.fireTableDataChanged();

	}

	protected void jBBorrarActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaLineasFactura.getSelectedRowCount()==0) {
			accion.setText("No ha seleccionado ninguna linea.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"No ha seleccionado ninguna linea!!!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaLineasFactura.getSelectedRowCount()==1) {
			int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar la linea con id "+modelotablalineafactura.recuperaLineaFacturaPorPosicion(tablaLineasFactura.getSelectedRow()).getIdLinea()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
			
			if (resp==JOptionPane.YES_OPTION) {
				modelotablalineafactura.borraLineaFacturaPorPosicion(tablaLineasFactura.getSelectedRow());
				accion.setText("Linea borrada con exito.");
			}
		}else {
				String listaIdSeleccionados =new String();
				for(int c:tablaLineasFactura.getSelectedRows()) {
					if(c==tablaLineasFactura.getSelectedRows()[tablaLineasFactura.getSelectedRows().length-1])
						listaIdSeleccionados += "y " + modelotablalineafactura.recuperaLineaFacturaPorPosicion(c).getIdLinea();
					else if(c==tablaLineasFactura.getSelectedRows()[tablaLineasFactura.getSelectedRows().length-2])
						listaIdSeleccionados += modelotablalineafactura.recuperaLineaFacturaPorPosicion(c).getIdLinea() + " ";
					else listaIdSeleccionados += modelotablalineafactura.recuperaLineaFacturaPorPosicion(c).getIdLinea() + ", ";
				}
				int resp=JOptionPane.showOptionDialog(null,
						"Va a borrar las lineas con id: "+listaIdSeleccionados+" ¿Está seguro?", 
						"Aviso",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						new Object[] { "Si", "No"},  
		 				"Si");

				if (resp==JOptionPane.YES_OPTION) {
					for(int i=tablaLineasFactura.getSelectedRows().length-1; i>=0; i--){
						modelotablalineafactura.borraLineaFacturaPorPosicion(tablaLineasFactura.getSelectedRows()[i]);
					}
					accion.setText("Lineas borradas con exito.");
				}
			}
		modelotablalineafactura.fireTableDataChanged();
			
	}

	protected void jBAñadirActionPerformed(ActionEvent evt) throws FacturaYaExiste, DAOExcepcion {
		// TODO Auto-generated method stub
		
		if(factura_nueva)
			GuardarFactura();
		
		System.out.println(factura.getNumfactura());
		
		if(jComboBoxComunidades.getSelectedIndex()>0){
			VentanaLineasFactura v=new VentanaLineasFactura((ModeloTablaLineaFactura) modelotablalineafactura, factura, (ModeloTablaConcepto) modelotablaconcepto, accion);
			accion.setText("Añadiendo una nueva linea a la factura...");
			v.setVisible(true);
		}
		
		factura_nueva=false;
		modificar=true;
		
	}

	private void jBGuardarActionPerformed(ActionEvent evt) throws FacturaYaExiste, DAOExcepcion {
		System.out.println("jBGuardar.actionPerformed, event="+evt);
		
		if(jComboBoxComunidades.getSelectedIndex()>0)
			GuardarFactura();
		
		dispose();
	}
	
	
	private void GuardarFactura() throws FacturaYaExiste, DAOExcepcion{
	
		try{
			if(jComboBoxComunidades.getSelectedIndex()==0 /*jComboBoxComunidad.getSelectedItem()==null*/)
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe seleccionar una Comunidad!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			else{
				Date fecha = new Date ();

				if(!modificar){
					factura=new Factura((Comunidad) jComboBoxComunidades.getSelectedItem(), fecha);
					modelotablafactura.addFactura(factura);
					accion.setText("Factura añadida con exito");
				}
				
				else{
					factura=new Factura(factura.getNumfactura(), (Comunidad) jComboBoxComunidades.getSelectedItem(), fecha);
					modelotablafactura.actualizaFactura(factura, row);
					accion.setText("Factura modificada con exito");		
					
				}
				
			}
					
		}catch (FacturaYaExiste e){
			JOptionPane.showMessageDialog(
					   contentPane, 
					   "Ya existe una factura con ese id!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
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
