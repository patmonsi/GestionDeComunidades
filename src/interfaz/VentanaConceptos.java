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
import dominio.Inmueble;
import dominio.Propietario;
import excepciones.ComunidadYaExiste;
import excepciones.ConceptoYaExiste;
import excepciones.DAOExcepcion;
import excepciones.InmuebleYaExiste;
import gestion.GestionConceptos;


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
public class VentanaConceptos extends JDialog {

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
	private JLabel descripcionLabel;
	private JTextField descripcionTextField;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	JLabel accion;
	private boolean modificar=false;
	
	private ModeloTablaConcepto modelotablaconcepto;
	private GestionConceptos gestionconceptos;
	Concepto concepto;
	private int row;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConceptos frame = new VentanaConceptos();
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
	
	
	public VentanaConceptos(ModeloTablaConcepto modelotablaconcepto, JLabel accion) {
		super();
		this.accion=accion;
		this.modelotablaconcepto=modelotablaconcepto;
		
		Detalle();
		setModal(true);
	}
	
	public VentanaConceptos() {
		Detalle();
	}
	
	public VentanaConceptos(Concepto c, ModeloTablaConcepto modelotablaconcepto, JLabel accion, int row) {
		super();
		
		this.modelotablaconcepto=modelotablaconcepto;
		this.row=row;
		this.accion=accion;
		Detalle();
		setModal(true);
		
		idTextField.setText(c.getClaveconcepto());
		descripcionTextField.setText(c.getDescripcion());

		idTextField.setEditable(false);
		
		modificar=true;
	}
	
	
	private void Detalle() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(250, 300, 450, 250);

		this.setTitle("Datos Inmueble");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
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
						} catch (ConceptoYaExiste e) {
							e.printStackTrace();
						}
					}
				});
			}
			{
				jBVolver = new JButton();
				botonesPanel.add(jBVolver);
				jBVolver.setText("Cancelar");
				jBVolver.setMnemonic(java.awt.event.KeyEvent.VK_C);
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
			formularioPanelLayout.rowWeights = new double[] {0.0, 0.0, 0.0};
			formularioPanelLayout.rowHeights = new int[] {48, 48, 48};
			formularioPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			formularioPanel.setLayout(formularioPanelLayout);
			{
				idLabel = new JLabel();
				formularioPanel.add(idLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				idLabel.setText("Clave: ");
			}
			{
				idTextField = new JTextField();
				formularioPanel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				idTextField.setColumns(30);
			}
			{
				descripcionLabel = new JLabel();
				formularioPanel.add(descripcionLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				descripcionLabel.setText("Descripcion:");
			}
			{
				descripcionTextField = new JTextField();
				formularioPanel.add(descripcionTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				descripcionTextField.setColumns(30);
			}
			
			
		}
	}
	
	private void jBGuardarActionPerformed(ActionEvent evt) throws ConceptoYaExiste, DAOExcepcion {
		System.out.println("jBGuardar.actionPerformed, event="+evt);
		
		try {
			
			if(idTextField.getText().length()>5) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "El campo descripcion no puede exceder de los 5 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else if(idTextField.getText().contentEquals("") || descripcionTextField.getText().contentEquals("")){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe rellenar todos los campos!!!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(descripcionTextField.getText().length()>20) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "El campo descripcion no puede exceder de los 20 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else{
				try{
					concepto=new Concepto(idTextField.getText(), descripcionTextField.getText());

					if(!modificar){
						modelotablaconcepto.addConcepto(concepto);
						accion.setText("Concepto añadido con exito");
						dispose();
					}
					
					else{
						modelotablaconcepto.actualizaConcepto(concepto, row);
						accion.setText("Concepto modificado con exito");		
						dispose();
					}
					
				}catch (ConceptoYaExiste e){
					JOptionPane.showMessageDialog(
							   contentPane, 
							   "Ya existe un concepto con esa clave!",
							   "Error",
							   JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(
					   contentPane, 
					   "La clave debe ser un valor numerico!",
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
