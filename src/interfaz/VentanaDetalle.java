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
import dominio.Inmueble;
import dominio.Propietario;
import excepciones.ComunidadYaExiste;
import excepciones.DAOExcepcion;
import excepciones.InmuebleYaExiste;


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
public class VentanaDetalle extends JDialog {

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
	private JLabel escaleraLabel;
	private JTextField escaleraTextField;
	private JTextField pisoTextField;
	private JLabel pisoLabel;
	private JTextField porcentajeTextField;
	private JLabel porcentajeLabel;
	private JComboBox jComboBoxPropietario;
	private JComboBox jComboBoxComunidad;
	private JTextField puertaTextField;
	private JLabel puertaLabel;
	private JLabel propietarioLabel;
	private JLabel comunidadLabel;
	private JPanel formularioPanel;
	private JButton jBGuardar;
	ModeloTablaInmueble modelotablainmueble;
	ModeloTablaPropietario modelotablapropietario;
	ModeloTablaComunidad modelotablacomunidad;
	Inmueble inmueble;
	JLabel accion;
	private boolean modificar=false;
	private int row;
	int idPropietario;
	
	private ArrayList<Propietario> listaPropietarios;
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
	 */
	
	
	public VentanaDetalle(ModeloTablaInmueble modeloinmueble, ModeloTablaPropietario modelopropietario, ModeloTablaComunidad modelocomunidad,JLabel accion/*, ArrayList<String> listaPropietarios*/) {
		super();
		this.modelotablainmueble=modeloinmueble;
		this.modelotablapropietario=modelopropietario;
		this.modelotablacomunidad=modelocomunidad;
		this.accion=accion;

		
		Detalle();
		setModal(true);
	}
	
	public VentanaDetalle() {
		Detalle();
	}
	
	public VentanaDetalle(Inmueble i, JLabel accion, ModeloTablaInmueble modeloinmueble, int row, ModeloTablaPropietario modelopropietario, ModeloTablaComunidad modelocomunidad) {
		super();
		
		modificar=true;
		
		this.accion=accion;
		this.modelotablainmueble=modeloinmueble;
		this.modelotablapropietario=modelopropietario;
		this.modelotablacomunidad=modelocomunidad;
		this.row=row;
		this.inmueble=i;
		
		Detalle();
		setModal(true);
		
		escaleraTextField.setText(i.getEscalera());
		pisoTextField.setText(i.getPiso());
		puertaTextField.setText(i.getPuerta());
		porcentajeTextField.setText(Double.toString(i.getPorcentaje()));
		
		jComboBoxPropietario.setSelectedItem(i.getPropietario());
		jComboBoxComunidad.setSelectedItem(i.getComunidad());


		
	}
	
	
	private void Detalle() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(250, 250, 450, 400);

		this.setTitle("Datos Inmueble");
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
			formularioPanelLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40};
			formularioPanelLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			formularioPanelLayout.columnWidths = new int[] {7, 7, 7, 7};
			formularioPanel.setLayout(formularioPanelLayout);
			{
				escaleraLabel = new JLabel();
				formularioPanel.add(escaleraLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				escaleraLabel.setText("Escalera:");
			}
			{
				escaleraTextField = new JTextField();
				formularioPanel.add(escaleraTextField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				escaleraTextField.setColumns(30);
			}
			{
				pisoLabel = new JLabel();
				formularioPanel.add(pisoLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				pisoLabel.setText("Piso:");
			}
			{
				pisoTextField = new JTextField();
				formularioPanel.add(pisoTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				pisoTextField.setColumns(30);
			}
			{
				puertaLabel = new JLabel();
				formularioPanel.add(puertaLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				puertaLabel.setText("Puerta:");
			}
			{
				puertaTextField = new JTextField();
				formularioPanel.add(puertaTextField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				puertaTextField.setColumns(30);
			}
			{
				porcentajeLabel = new JLabel();
				formularioPanel.add(porcentajeLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				porcentajeLabel.setText("Porcentaje:");
			}
			{
				porcentajeTextField = new JTextField();
				formularioPanel.add(porcentajeTextField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				porcentajeTextField.setColumns(30);
			}
			{
				propietarioLabel = new JLabel();
				formularioPanel.add(propietarioLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				propietarioLabel.setText("Propietario:");
			}
			{

				try {
					listaPropietarios=modelotablapropietario.listaNombresPropietarios();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jComboBoxPropietario = new JComboBox();
				formularioPanel.add(jComboBoxPropietario, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

				jComboBoxPropietario.addItem("Propietario");
				for(int i=0; i<listaPropietarios.size();i++)
					jComboBoxPropietario.addItem(listaPropietarios.get(i));
	
			
			{
				comunidadLabel = new JLabel();
				formularioPanel.add(comunidadLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				comunidadLabel.setText("Comunidad:");
			}
				try {
					listaComunidades=modelotablacomunidad.listaComunidades();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				jComboBoxComunidad = new JComboBox();
				formularioPanel.add(jComboBoxComunidad, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jComboBoxComunidad.addItem("Comunidad");
				for(int i=0; i<listaComunidades.size();i++){
					jComboBoxComunidad.addItem(listaComunidades.get(i));
				    }  
			}
		}
	}
	
	private void jBGuardarActionPerformed(ActionEvent evt) throws InmuebleYaExiste, DAOExcepcion {
		System.out.println("jBGuardar.actionPerformed, event="+evt);
		
		try {

			if(Double.parseDouble(porcentajeTextField.getText())<0 || Double.parseDouble(porcentajeTextField.getText())>100){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "El porcentaje debe estar entre 0 y 100",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(escaleraTextField.getText().contentEquals("") || pisoTextField.getText().contentEquals("") || puertaTextField.getText().contentEquals("") || porcentajeTextField.getText().contentEquals("")){
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe rellenar todos los campos!!!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			} 
			else if(escaleraTextField.getText().length()>10 || pisoTextField.getText().length()>10 || puertaTextField.getText().length()>10) 
				JOptionPane.showMessageDialog(
					   contentPane, 
					   "Los campos escalera, piso y puerta no pueden exceder de los 10 caracteres!",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else if(jComboBoxPropietario.getSelectedIndex()==0 || jComboBoxComunidad.getSelectedIndex()==0 /*jComboBoxComunidad.getSelectedItem()==null*/)
				JOptionPane.showMessageDialog(
						   contentPane, 
						   "Debe seleccionar un Propietario y una Comunidad!",
						   "Error",
						   JOptionPane.ERROR_MESSAGE);
			else if(((Comunidad)jComboBoxComunidad.getSelectedItem()).getEstado().trim().equals("Baja"))
			JOptionPane.showMessageDialog(
					   contentPane, 
					   "La Comunidad seleccionada está en estado de Baja",
					   "Error",
					   JOptionPane.ERROR_MESSAGE);
			else{
				try{		
					if(!modificar){
						inmueble=new Inmueble(escaleraTextField.getText(), pisoTextField.getText(),  puertaTextField.getText(), Double.parseDouble(porcentajeTextField.getText()), (Propietario) jComboBoxPropietario.getSelectedItem(), (Comunidad) jComboBoxComunidad.getSelectedItem());
						modelotablainmueble.addInmueble(inmueble);
						accion.setText("Inmueble añadido con exito");
						
						if(modelotablainmueble.sumaPorcentajes(inmueble.getComunidad().getNombre())==100)
							inmueble.getComunidad().setEstado("Cuadrado");
						else inmueble.getComunidad().setEstado("Sin Cuadrar");
						dispose();
					}
					
					else{
						inmueble=new Inmueble(inmueble.getIdInmueble(), escaleraTextField.getText(), pisoTextField.getText(),  puertaTextField.getText(), Double.parseDouble(porcentajeTextField.getText()), (Propietario) jComboBoxPropietario.getSelectedItem(), (Comunidad) jComboBoxComunidad.getSelectedItem());
						modelotablainmueble.actualizaInmueble(inmueble, row);
						if(modelotablainmueble.sumaPorcentajes(inmueble.getComunidad().getNombre())==100){
							inmueble.getComunidad().setEstado("Cuadrado");
							modelotablacomunidad.actualizaComunidad(inmueble.getComunidad());
						}
						else{ 
							inmueble.getComunidad().setEstado("Sin cuadrar");
							modelotablacomunidad.actualizaComunidad(inmueble.getComunidad());
						}
						accion.setText("Inmueble modificado con exito");		
						dispose();
					}
					
				}catch (InmuebleYaExiste e){
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
