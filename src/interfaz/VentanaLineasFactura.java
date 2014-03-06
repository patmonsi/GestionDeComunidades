package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dominio.Comunidad;
import dominio.Concepto;
import dominio.Factura;
import dominio.Inmueble;
import dominio.LineaFactura;
import dominio.Propietario;
import excepciones.ComunidadYaExiste;
import excepciones.DAOExcepcion;
import excepciones.InmuebleYaExiste;
import excepciones.LineaFacturaYaExiste;


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
public class VentanaLineasFactura extends JDialog {

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
	private JButton jBVolver;
	private JLabel idLabel;
	private JTextField idTextField;
	private JLabel importeLabel;
	private JTextField importeTextField;
	private JTextField observacionTextField;
	private JLabel observacionLabel;
	private JLabel conceptoLabel;
	private JComboBox jComboBoxConcepto;
	private JLabel comunidadLabel;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	ModeloTablaLineaFactura modelotablalineafactura;
	ModeloTablaConcepto modelotablaconcepto;
	
	LineaFactura lineafactura;
	Factura factura;
	JLabel accion;
	private boolean modificar=false;
	private int row;
	
	private ArrayList<Concepto> listaConceptos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLineasFactura frame = new VentanaLineasFactura();
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
	 */
	
	
	public VentanaLineasFactura(ModeloTablaLineaFactura modelolineafactura, Factura factura, ModeloTablaConcepto modeloconcepto, JLabel accion) {
		super();
		this.modelotablalineafactura=modelolineafactura;
		this.modelotablaconcepto=modeloconcepto;
		this.accion=accion;
		this.factura=factura;
		
		Detalle();
		setModal(true);
	}
	
	public VentanaLineasFactura() {
		Detalle();
	}
	
	public VentanaLineasFactura(LineaFactura l, Factura factura, ModeloTablaLineaFactura modelolineafactura, ModeloTablaConcepto modeloconcepto, JLabel accion, int row) {
		super();
		
		this.accion=accion;
		this.modelotablalineafactura=modelolineafactura;
		this.modelotablaconcepto=modeloconcepto;
		this.factura=factura;
		this.row=row;
		
		Detalle();
		setModal(true);
		
		importeTextField.setText(Double.toString(l.getImportelinea()));
		observacionTextField.setText(l.getObservacion());

		modificar=true;
	}
	
	
	private void Detalle() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(250, 250, 450, 400);

		this.setTitle("Datos Linea Factura");
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
							try {
								jBGuardarActionPerformed(evt);
							} catch (DAOExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (InmuebleYaExiste e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				jBVolver = new JButton();
				botonesPanel.add(jBVolver);
				jBVolver.setText("Volver");
				jBVolver.setMnemonic(java.awt.event.KeyEvent.VK_V);
				jBVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jBVolverActionPerformed(evt);
					}
				});
			}
		}
		{
			formularioPanel = new JPanel();
			GridBagLayout formularioPanelLayout = new GridBagLayout();
			contentPane.add(formularioPanel, BorderLayout.CENTER);
			formularioPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.rowHeights = new int[] {40, 40, 40, 40};
			formularioPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			formularioPanel.setLayout(formularioPanelLayout);
			{
				conceptoLabel = new JLabel();
				formularioPanel.add(conceptoLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				conceptoLabel.setText("Concepto:");
			}
				try {
					listaConceptos=modelotablaconcepto.listaConceptos();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jComboBoxConcepto = new JComboBox();
				formularioPanel.add(jComboBoxConcepto, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jComboBoxConcepto.addItem("Concepto");
				for(int i=0; i<listaConceptos.size();i++){
					jComboBoxConcepto.addItem(listaConceptos.get(i));
				    }  
			}

			{
				importeLabel = new JLabel();
				formularioPanel.add(importeLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				importeLabel.setText("Importe:");
			}
			{
				importeTextField = new JTextField();
				formularioPanel.add(importeTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				importeTextField.setColumns(30);
			}
			{
				observacionLabel = new JLabel();
				formularioPanel.add(observacionLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				observacionLabel.setText("Observaciones:");
			}
			{
				observacionTextField = new JTextField();
				formularioPanel.add(observacionTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				observacionTextField.setColumns(30);
			}
		
	}
	
	private void jBGuardarActionPerformed(ActionEvent evt) throws InmuebleYaExiste, DAOExcepcion {
		System.out.println("jBGuardar.actionPerformed, event="+evt);
		
		try {
			
			if(Double.parseDouble(importeTextField.getText())<0){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "El importe debe ser mayor o igual que 0",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(importeTextField.getText().contentEquals("") || observacionTextField.getText().contentEquals("")){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe rellenar todos los campos!!!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(observacionTextField.getText().length()>20) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "El campo observacion no puede exceder de los 20 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else if(jComboBoxConcepto.getSelectedIndex()==0 )
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe seleccionar un Concepto!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			else{
				try{
					lineafactura=new LineaFactura(factura, (Concepto) jComboBoxConcepto.getSelectedItem(), Double.parseDouble(importeTextField.getText()), observacionTextField.getText());
					
					if(!modificar){
						modelotablalineafactura.addLineaFactura(lineafactura);

						accion.setText("Linea añadida con exito a la factura");
						dispose();
					}
					
					else{
						modelotablalineafactura.actualizaLineaFactura(lineafactura, row);
						accion.setText("Linea modificada con exito");		
						dispose();
					}
					
				}catch (LineaFacturaYaExiste e){
					JOptionPane.showMessageDialog(
							   contentPane, 
							   "Ya existe un inmueble con ese id!",
							   "Error",
							   JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(
					   contentPane, 
					   "El id debe ser un valor numerico!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void jBVolverActionPerformed(ActionEvent evt) {
		System.out.println("jBVolver.actionPerformed, event="+evt);
		Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		accion.setText("Fecha: " +formateador.format(ahora));
		dispose();
	}
	
}
