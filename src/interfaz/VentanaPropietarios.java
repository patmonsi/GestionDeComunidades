package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import dominio.Propietario;
import excepciones.DAOExcepcion;
import excepciones.PropietarioYaExiste;


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
public class VentanaPropietarios extends JDialog {

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
	private JLabel NIFLabel;
	private JTextField nombreTextField;
	private JLabel nombreLabel;
	private JTextField direccionTextField;
	private JLabel direccionLabel;
	private JTextField poblacionTextField;
	private JLabel poblacionLabel;
	private JTextField NIFTextField;
	private JTextField idTextField;
	private JLabel idLabel;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	ModeloTablaPropietario modelo;
	Propietario propietario;
	JLabel accion;

	private boolean modificar=false;
	private int row;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPropietarios frame = new VentanaPropietarios();
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
	
	
	public VentanaPropietarios(ModeloTablaPropietario modelo, JLabel accion) {
		super();
		this.modelo=modelo;
		this.accion=accion;
		Detalle();
		setModal(true);
	}
	
	public VentanaPropietarios() {
		Detalle();
	}
	
	public VentanaPropietarios(Propietario p, JLabel accion, ModeloTablaPropietario modelo, int row) {
		super();
		this.accion=accion;
		this.modelo=modelo;
		this.row=row;
		
		modificar=true;
		
		Detalle();
		setModal(true);
		
		NIFTextField.setText(p.getNif());
		direccionTextField.setText(p.getDireccion());
		nombreTextField.setText(p.getNombre());

		NIFTextField.setEditable(false);
		
		}
	
	
	private void Detalle() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(250, 300, 450, 250);

		this.setTitle("Alta nuevo Propietario");
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
						} catch (PropietarioYaExiste e) {
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
			formularioPanelLayout.rowHeights = new int[] {7, 7, 7, 7};
			formularioPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			formularioPanel.setLayout(formularioPanelLayout);
			
			{
				NIFLabel = new JLabel();
				formularioPanel.add(NIFLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				NIFLabel.setText("NIF:");
			}
			{
				NIFTextField = new JTextField();
				formularioPanel.add(NIFTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				NIFTextField.setColumns(30);
			}
			{
				nombreLabel = new JLabel();
				formularioPanel.add(nombreLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				nombreLabel.setText("Nombre:");
			}
			{
				nombreTextField = new JTextField();
				formularioPanel.add(nombreTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				nombreTextField.setColumns(30);
			}
			{
				direccionLabel = new JLabel();
				formularioPanel.add(direccionLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				direccionLabel.setText("Direccion:");
			}
			{
				direccionTextField = new JTextField();
				formularioPanel.add(direccionTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				direccionTextField.setColumns(30);
			}
		}
	}
	
	private void jBGuardarActionPerformed(ActionEvent evt) throws PropietarioYaExiste, DAOExcepcion {
		System.out.println("jBGuardar.actionPerformed, event="+evt);
		
		try {
			if(NIFTextField.getText().contentEquals("") || direccionTextField.getText().contentEquals("") || nombreTextField.getText().contentEquals("")){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe rellenar todos los campos!!!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(NIFTextField.getText().length()>10 || direccionTextField.getText().length()>40 || nombreTextField.getText().length()>40) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "Los campos NIF, direccion y nombre no pueden exceder de los 10 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else{
				try{
					Date fecha = new Date ();
					
					propietario=new Propietario(NIFTextField.getText(), nombreTextField.getText(), direccionTextField.getText(), null, null, null, fecha, null, null);
				
					if(!modificar){
						modelo.addPropietario(propietario);
						accion.setText("Propietario añadido con exito");
						dispose();
					}
					else{
						modelo.actualizaPropietario(propietario, row);
						accion.setText("Propietario añadido con exito");
						dispose();
					}
					
				}catch (PropietarioYaExiste e){
					JOptionPane.showMessageDialog(
							   contentPane, 
							   "Ya existe un propietario con ese id!",
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
