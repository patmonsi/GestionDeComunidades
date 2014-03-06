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
import dominio.Inmueble;
import dominio.Propietario;
import excepciones.DAOExcepcion;
import excepciones.ComunidadYaExiste;


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
public class VentanaComunidades extends JDialog {

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
	private JLabel calleLabel;
	private JTextField calleTextField;
	private JTextField estadoTextField;
	private JLabel estadoLabel;
	private JComboBox jComboBoxEstado;
	private JTextField max_recibosTextField;
	private JLabel max_recibosLabel;
	private JTextField idTextField;
	private JLabel idLabel;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	ModeloTablaComunidad modelotablacomunidad;
	ModeloTablaComunidadCuadrada modelotablacomunidadcuadrada;
	Comunidad comunidad;
	JLabel accion;
	
	private JComboBox jComboBoxPresidente;
	private JLabel propietarioLabel;
	private ArrayList<Propietario> listaPropietarios;
	
	ModeloTablaInmueble modelotablainmueble;
	private ArrayList<Inmueble> listaInmuebles;
	
	private boolean modificar=false;
	private int row;
	
	private int id_comunidad;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComunidades frame = new VentanaComunidades();
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
	
	
	public VentanaComunidades(ModeloTablaComunidad modelocomunidad, ModeloTablaInmueble modeloinmueble, JLabel accion) {
		super();
		this.modelotablacomunidad=modelocomunidad;
		this.modelotablainmueble=modeloinmueble;
		this.accion=accion;
		
		Detalle();
		setModal(true);
	}
	
	
	
	public VentanaComunidades() {
		Detalle();
	}
	
	
	
	public VentanaComunidades(Comunidad c, JLabel accion,  ModeloTablaComunidad modelo, ModeloTablaInmueble modeloinmueble, int row, ModeloTablaComunidadCuadrada modelotablacomunidadcuadrada) {
		super();
		
		this.accion=accion;
		this.modelotablacomunidad=modelo;
		this.modelotablainmueble=modeloinmueble;
		this.row=row;
		this.modelotablacomunidadcuadrada=modelotablacomunidadcuadrada;
		
		modificar=true;
		
		try {
			listaPropietarios=modelotablainmueble.listarPropietariosPorComunidad(c);
		} catch (DAOExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Detalle();
		setModal(true);
		
		idTextField.setText(c.getNombre());
		calleTextField.setText(c.getCalle());
		max_recibosTextField.setText(Integer.toString(c.getMaxRecibosPendientes()));
		
		jComboBoxEstado.setSelectedItem(c.getEstado());
		
		idTextField.setEditable(false);
		modificar=true;
	}
	
	
	private void Detalle() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(250, 300, 450, 250);
		this.setTitle("Alta nueva Comunidad");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0,0));
		setContentPane(contentPane);
		contentPane.setPreferredSize(new java.awt.Dimension(455, 234));
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
						} catch (ComunidadYaExiste e) {
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
				idLabel = new JLabel();
				formularioPanel.add(idLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				idLabel.setText("Nombre: ");
			}
			{
				idTextField = new JTextField();
				formularioPanel.add(idTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				idTextField.setColumns(30);
			}
			{
				calleLabel = new JLabel();
				formularioPanel.add(calleLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				calleLabel.setText("Calle:");
			}
			{
				calleTextField = new JTextField();
				formularioPanel.add(calleTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				calleTextField.setColumns(30);
			}
			{
				max_recibosLabel = new JLabel();
				formularioPanel.add(max_recibosLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				max_recibosLabel.setText("Max recibos pendientes:");
			}
			{
				max_recibosTextField = new JTextField();
				formularioPanel.add(max_recibosTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				max_recibosTextField.setColumns(30);
			}
			{
				estadoLabel = new JLabel();
				formularioPanel.add(estadoLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				estadoLabel.setText("estado:");
			}

			{
				String[] estados={"Sin cuadrar", "Cuadrado", "Moroso", "Baja"};
				jComboBoxEstado = new JComboBox(estados);
				formularioPanel.add(jComboBoxEstado, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			}
		
			
			
			{
				propietarioLabel = new JLabel();
				formularioPanel.add(propietarioLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				propietarioLabel.setText("Presidente:");
			}
			{

				jComboBoxPresidente = new JComboBox();
				formularioPanel.add(jComboBoxPresidente, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jComboBoxPresidente.addItem("Presidente");
	
				if(modificar){		
					for(int i=0; i<listaPropietarios.size();i++)
						jComboBoxPresidente.addItem(listaPropietarios.get(i));
				}
					    
			}
		}
	}
	
	private void jBGuardarActionPerformed(ActionEvent evt) throws ComunidadYaExiste, DAOExcepcion {
		System.out.println("jBGuardar.actionPerformed, event="+evt);

		try {
			if(idTextField.getText().length()>10) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "El nombre de la comunidad no puede exceder de los 10 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else if(Integer.parseInt(max_recibosTextField.getText())<0){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "El numero maximo de recibos pendientes no puede ser negativo!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(idTextField.getText().contentEquals("") || max_recibosTextField.getText().contentEquals("") || calleTextField.getText().contentEquals("")){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe rellenar todos los campos!!!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(calleTextField.getText().length()>40) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "Los campos estado y calle no pueden exceder de los 40 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else if(jComboBoxEstado.getSelectedItem().equals("Cuadrado") && modelotablainmueble.sumaPorcentajes(idTextField.getText())!=100) 
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "La suma de los porcentajes no es igual a 100, por tanto el estado no puede ser 'Cuadrado'",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			else{
				try{
					if(jComboBoxPresidente.getSelectedIndex()==0){
						comunidad=new Comunidad(idTextField.getText(), calleTextField.getText(),  Integer.parseInt(max_recibosTextField.getText()), (String) jComboBoxEstado.getSelectedItem(), null);
					}else {
						comunidad=new Comunidad(idTextField.getText(), calleTextField.getText(),  Integer.parseInt(max_recibosTextField.getText()), (String) jComboBoxEstado.getSelectedItem(), (Propietario) jComboBoxPresidente.getSelectedItem());
					}
					
					if(!modificar){
						modelotablacomunidad.addComunidad(comunidad);
						accion.setText("Comunidad añadida con exito");
						dispose();
					}
					else{
						if(comunidad.getEstado().trim().equals("Cuadrado"))
							modelotablacomunidadcuadrada.addComunidad(comunidad);
						else
							modelotablacomunidadcuadrada.borraComunidad(comunidad);
						
						modelotablacomunidad.actualizaComunidad(comunidad, row);
						accion.setText("Comunidad modificada con exito");
						dispose();
						}
					
				}catch (ComunidadYaExiste e){
					JOptionPane.showMessageDialog(
							   contentPane, 
							   "Ya existe una comunidad con ese id!",
							   "Error",
							   JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(
					   contentPane, 
					   "El numero maximo de recibos pendientes debe ser un valor numerico!",
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