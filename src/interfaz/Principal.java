package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;


import dominio.Comunidad;
import dominio.Concepto;
import dominio.Factura;
import dominio.Inmueble;
import dominio.Propietario;
import excepciones.DAOExcepcion;
import excepciones.PropietarioYaExiste;
import gestion.GestionComunidades;
import gestion.GestionConceptos;
import gestion.GestionFacturas;
import gestion.GestionInmuebles;
import gestion.GestionLineasFactura;
import gestion.GestionNotasInformativas;
import gestion.GestionPropietarios;
import gestion.GestionRecibos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

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
**/

@SuppressWarnings("all")
public class Principal extends JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JPanel contentPane;
	private JTable tablaInmuebles;
	private JTable tablaPropietarios;
	private JTable tablaComunidades;
	private JTable tablaConceptos;
	private JTable tablaFacturas;
	private JTable tablaComunidadesCuadradas;
	private JTable tablaRecibos;
	private JTable tablaNotasInformativas;
	private ModeloTablaInmueble modelotablainmueble;
	private ModeloTablaPropietario modelotablapropietario;
	private ModeloTablaComunidad modelotablacomunidad;
	private ModeloTablaConcepto modelotablaconcepto;
	private ModeloTablaFactura modelotablafactura;
//	private ModeloTablaLineaFactura modelotablalineafactura;
	private ModeloTablaComunidadCuadrada modelotablacomunidadcuadrada;
	private ModeloTablaRecibo modelotablarecibo;
	private ModeloTablaNotaInformativa modelotablanotainformativa;
	private GestionInmuebles gestioninmuebles=new GestionInmuebles();
	private GestionPropietarios gestionpropietarios=new GestionPropietarios();
	private GestionComunidades gestioncomunidades=new GestionComunidades();
	private GestionConceptos gestionconceptos=new GestionConceptos();
	private GestionFacturas gestionfacturas=new GestionFacturas();
	private GestionLineasFactura gestionlineasfactura=new GestionLineasFactura();
	private GestionRecibos gestionrecibos=new GestionRecibos();
	private GestionNotasInformativas gestionnotasinformativas=new GestionNotasInformativas();
	static public File fichero=null;
	private Boolean modificado=false;
	
	//Los declaramos aqui para despues usar el setForeground y el setBackground
	private JMenuBar menuBar;
	private JMenu archivoMenu;
	private JMenuItem guardarMenuItem;
	private JMenu aspectoMenu;
	
	private JMenu informeMenu;
	private JMenuItem CrearInformeMenuItem;
	
	private JMenu conceptoMenu;
	private JMenuItem altaConceptoMenuItem;
	private JMenuItem bajaConceptoMenuItem;
	private JMenuItem modificacionConceptoMenuItem;
	
	private JScrollPane tablaScrollPane;
	private JScrollPane tablaScrollPane2;
	private JScrollPane tablaScrollPane3;
	private JScrollPane tablaScrollPane4;
	private JScrollPane tablaScrollPane5;
	private JScrollPane tablaScrollPane6;
	private JScrollPane tablaScrollPane6_2;
	private JScrollPane tablaScrollPane7;
	private JPanel posicionBotonesPanel;
	private JPanel posicionBotonesPanel2;
	private JPanel posicionBotonesPanel3;
	private JPanel posicionBotonesPanel4;
	private JPanel posicionBotonesPanel5;
	private JPanel posicionBotonesPanel6;
	private JPanel posicionBotonesPanel7;
	private JToolBar toolBar;
	private JLabel accion;
	private JMenuItem salirMenuItem;
	
	private ArrayList<String> listaPropietarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DAOExcepcion 
	 */
	public Principal() throws DAOExcepcion {
		super();
		modelotablainmueble = new ModeloTablaInmueble(gestioninmuebles);
		modelotablapropietario = new ModeloTablaPropietario(gestionpropietarios);
		modelotablacomunidad = new ModeloTablaComunidad(gestioncomunidades);
		modelotablaconcepto = new ModeloTablaConcepto(gestionconceptos);
		modelotablafactura = new ModeloTablaFactura(gestionfacturas);
		modelotablacomunidadcuadrada = new ModeloTablaComunidadCuadrada(gestioncomunidades);
		modelotablarecibo = new ModeloTablaRecibo(gestionrecibos);
		modelotablanotainformativa = new ModeloTablaNotaInformativa(gestionnotasinformativas);
		ventana();
	}
	
	
	public void ventana() {
		setTitle("Gestion Inmuebles");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("home.png")));		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		// Posicion de la ventana
		setBounds(100, 75, 1000, 600);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				thisWindowClosing(evt);
			}
		});
		
		
		// Creamos la barra del menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//Menu archivo con sus submenus
		archivoMenu = new JMenu("Archivo");
		menuBar.add(archivoMenu);
		archivoMenu.setMnemonic(java.awt.event.KeyEvent.VK_R); // letra subrayada para acceso rapido con alt

		guardarMenuItem = new JMenuItem("Guardar");
		archivoMenu.add(guardarMenuItem);
		guardarMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_G); // letra subrayada para acceso rapido con alt
		guardarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//Desactivado para la práctica 5 y posteriores
//				guardarMenuItemActionPerformed(evt);
			}
		});

		JMenuItem guardarComoMenuItem = new JMenuItem("Guardar Como...");
		archivoMenu.add(guardarComoMenuItem);
		guardarComoMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_C); // letra subrayada para acceso rapido con alt
		guardarComoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//Desactivado para la práctica 5 y posterior
//				guardarComoMenuItemActionPerformed();
			}
		});

		JMenuItem cargarMenuItem = new JMenuItem("Cargar de Disco");
		archivoMenu.add(cargarMenuItem);
		cargarMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_D); // letra subrayada para acceso rapido con alt
		cargarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
/*				try {
//Desactivado para la práctica 5 y posteriores
//					cargarMenuItemActionPerformed(evt);
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			}
		});

		JSeparator separadorMenuItem = new JSeparator();
		archivoMenu.add(separadorMenuItem);
		
		salirMenuItem = new JMenuItem("Salir");
		archivoMenu.add(salirMenuItem);
		salirMenuItem.setMnemonic(java.awt.event.KeyEvent.VK_S); // letra subrayada para acceso rapido con alt
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				salirMenuItemActionPerformed(evt);
			}
		});
	
		// Menu aspecto con sus submenus
		aspectoMenu = new JMenu("Aspecto");
		menuBar.add(aspectoMenu);
		aspectoMenu.setMnemonic(java.awt.event.KeyEvent.VK_S); // letra subrayada para acceso rapido con alt

		JMenuItem colorFuente = new JMenuItem("Color Fuente");
		aspectoMenu.add(colorFuente);
		colorFuente.setMnemonic(java.awt.event.KeyEvent.VK_C); // letra subrayada para acceso rapido con alt
		colorFuente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				colorFuenteActionPerformed(evt);
			}
		});

		JMenuItem colorFondo = new JMenuItem("Color Fondo");
		aspectoMenu.add(colorFondo);
		colorFondo.setMnemonic(java.awt.event.KeyEvent.VK_F); // letra subrayada para acceso rapido con alt
		colorFondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				colorFondoActionPerformed(evt);
			}
		});
		// Hasta aqui el menu
		
		
		// Aqui empieza el panel
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		// toolbar con dos botones, uno para cargar y otro para guardar
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton abrirButton = new JButton("");
		abrirButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/openFile.png")));
		toolBar.add(abrirButton);
		abrirButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				/*
	//			abrirButtonActionPerformed(evt);
				if(fichero!=null && modificado){
					int seleccion = JOptionPane.showOptionDialog(
			                 null,
			                 "Si carga un nuevo archivo, perderá los cambios que no se han guardado. ¿Que desea hacer?",
			                 "¡Aviso!",
			                 JOptionPane.YES_NO_CANCEL_OPTION,        
			                 JOptionPane.QUESTION_MESSAGE,             
			                 null,                                   
			                 new Object[] { "Guardar Cambios", "Cargar archivo", "Cancelar" },  
			         			"Cargar archivo");  
					if(seleccion==JOptionPane.YES_OPTION){
						guardarMenuItemActionPerformed(evt);
					}
					else if(seleccion==JOptionPane.NO_OPTION){
						try {
							cargarMenuItemActionPerformed(evt);
						} catch (DAOExcepcion e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else
					try {
						cargarMenuItemActionPerformed(evt);
					} catch (DAOExcepcion e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
			}
		});

		JButton guardarButton = new JButton("");
		guardarButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/saveFile.png")));
		toolBar.add(guardarButton);
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
	//			guardarComoMenuItemActionPerformed();
			}
		});
		
		// Inicializamos accion con la fecha y hora, aunque mas adelante mostrará las acciones realizadas por el usuario
		Date fecha= new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("EEEEEEEE, dd MMMMMMMMMM yyyy hh:mm:ss");
		accion = new JLabel("Fecha: " +formateador.format(fecha));
		contentPane.add(accion, BorderLayout.SOUTH);

		
		
		
		  // se crea el panel de pestañas
		JTabbedPane panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
		// se posiciona en el panel
		contentPane.add(panelDePestanas);
		
		// PANEL 1
		JPanel panel1 = new JPanel();
		panelDePestanas.addTab("INMUEBLES", null, panel1, null);
//		panel1.setLayout(null);
		
		// En el centro ponemos la tabla
		tablaScrollPane = new JScrollPane();
		panel1.add(tablaScrollPane, BorderLayout.CENTER);
		{
			tablaInmuebles = new JTable();
			tablaScrollPane.setViewportView(tablaInmuebles);
			tablaInmuebles.setModel(modelotablainmueble);
		}
		// Definimos el tamaño del scrollpane:
		tablaScrollPane.setPreferredSize(new java.awt.Dimension(870, 430));
		// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
		tablaInmuebles.getModel().addTableModelListener(new TableModelListener() {
		      public void tableChanged(TableModelEvent e) {
	//	         modificado=true;
		      }
		    });
		
		tablaInmuebles.setAutoCreateRowSorter(true);

		// Panel derecho con las opciones añadir, borrar, y ver detalle
		posicionBotonesPanel = new JPanel();
		panel1.add(posicionBotonesPanel, BorderLayout.EAST);
	//	contentPane.add(posicionBotonesPanel, BorderLayout.EAST);
		posicionBotonesPanel.setLayout(new GridLayout(6, 1, 0, 5));
	//	posicionBotonesPanel.setSize(95, 453);
		posicionBotonesPanel.setPreferredSize(new java.awt.Dimension(95, 432));

		JButton addButton = new JButton("Añadir");
		posicionBotonesPanel.add(addButton);
		addButton.setMnemonic(java.awt.event.KeyEvent.VK_A);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		JButton delButton = new JButton("Borrar");
		posicionBotonesPanel.add(delButton);
		delButton.setMnemonic(java.awt.event.KeyEvent.VK_B);
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					delButtonActionPerformed(evt);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton detalleButton = new JButton("Modificar");
		posicionBotonesPanel.add(detalleButton);
		detalleButton.setMnemonic(java.awt.event.KeyEvent.VK_M);
		detalleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				detalleButtonActionPerformed(evt);
			}
		});
		
		
		
		  // PANEL 2
		JPanel panel2 = new JPanel();
		panelDePestanas.addTab("PROPIETARIOS", null, panel2, null);
		// En el centro ponemos la tabla Propietarios
		tablaScrollPane2 = new JScrollPane();
		panel2.add(tablaScrollPane2, BorderLayout.CENTER);
	//	contentPane.add(tablaScrollPane, BorderLayout.CENTER);
		{
			tablaPropietarios = new JTable();
			tablaScrollPane2.setViewportView(tablaPropietarios);
			tablaPropietarios.setModel(modelotablapropietario);
		}
		// Definimos el tamaño del scrollpane:
		tablaScrollPane2.setPreferredSize(new java.awt.Dimension(870, 430));
		// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
		tablaPropietarios.getModel().addTableModelListener(new TableModelListener() {
		      public void tableChanged(TableModelEvent e) {
		      }
		    });
		
		tablaPropietarios.setAutoCreateRowSorter(true);

		// Panel derecho con las opciones añadir, borrar, y modificar para Propietarios
		posicionBotonesPanel2 = new JPanel();
		panel2.add(posicionBotonesPanel2, BorderLayout.EAST);
		posicionBotonesPanel2.setLayout(new GridLayout(6, 1, 0, 5));
		posicionBotonesPanel2.setPreferredSize(new java.awt.Dimension(95, 432));

		JButton addButtonPropietario = new JButton("Añadir");
		posicionBotonesPanel2.add(addButtonPropietario);
		addButtonPropietario.setMnemonic(java.awt.event.KeyEvent.VK_A);
		addButtonPropietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addPropietarioButtonActionPerformed(evt);
			}
		});

		JButton delButtonPropietario = new JButton("Borrar");
		posicionBotonesPanel2.add(delButtonPropietario);
		delButtonPropietario.setMnemonic(java.awt.event.KeyEvent.VK_B);
		delButtonPropietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					delPropietarioButtonActionPerformed(evt);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton modificarButtonPropietario = new JButton("Modificar");
		posicionBotonesPanel2.add(modificarButtonPropietario);
		modificarButtonPropietario.setMnemonic(java.awt.event.KeyEvent.VK_M);
		modificarButtonPropietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				modificarPropietarioButtonActionPerformed(evt);
			}
		});
		
		JButton listadoButtonPropietario = new JButton("<html><p>Listar</p><p>Propietarios</p></html>");
		posicionBotonesPanel2.add(listadoButtonPropietario);
		listadoButtonPropietario.setMnemonic(java.awt.event.KeyEvent.VK_L);
		listadoButtonPropietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				listarPropietarioButtonActionPerformed(evt);
			}
		});
		
		JButton listadoRecibosButtonPropietario = new JButton("<html><p>Listar</p><p>Recibos</p><p>Impagados</p></html>");
		posicionBotonesPanel2.add(listadoRecibosButtonPropietario);
		listadoRecibosButtonPropietario.setMnemonic(java.awt.event.KeyEvent.VK_R);
		listadoRecibosButtonPropietario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					listarRecibosPropietarioButtonActionPerformed(evt);
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		  // PANEL 3
		JPanel panel3 = new JPanel();
		panelDePestanas.addTab("COMUNIDADES", null, panel3, null);
		
		// En el centro ponemos la tabla Propietarios
		tablaScrollPane3 = new JScrollPane();
		panel3.add(tablaScrollPane3, BorderLayout.CENTER);
	//	contentPane.add(tablaScrollPane, BorderLayout.CENTER);
		{
			tablaComunidades = new JTable();
			tablaScrollPane3.setViewportView(tablaComunidades);
			tablaComunidades.setModel(modelotablacomunidad);
		}
		// Definimos el tamaño del scrollpane:
		tablaScrollPane3.setPreferredSize(new java.awt.Dimension(870, 430));
		// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
		tablaComunidades.getModel().addTableModelListener(new TableModelListener() {
		      public void tableChanged(TableModelEvent e) {
		      }
		    });
		
		tablaComunidades.setAutoCreateRowSorter(true);
		
		// Panel derecho con las opciones añadir, borrar, y modificar para Comunidades
		posicionBotonesPanel3 = new JPanel();
		panel3.add(posicionBotonesPanel3, BorderLayout.EAST);
		posicionBotonesPanel3.setLayout(new GridLayout(6, 1, 0, 5));
		posicionBotonesPanel3.setPreferredSize(new java.awt.Dimension(95, 432));

		JButton addButtonComunidad = new JButton("Añadir");
		posicionBotonesPanel3.add(addButtonComunidad);
		addButtonComunidad.setMnemonic(java.awt.event.KeyEvent.VK_A);
		addButtonComunidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addComunidadButtonActionPerformed(evt);
			}
		});

		JButton delButtonComunidad = new JButton("Borrar");
		posicionBotonesPanel3.add(delButtonComunidad);
		delButtonComunidad.setMnemonic(java.awt.event.KeyEvent.VK_B);
		delButtonComunidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					delComunidadButtonActionPerformed(evt);
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		JButton modificarButtonComunidad = new JButton("Modificar");
		posicionBotonesPanel3.add(modificarButtonComunidad);
		modificarButtonComunidad.setMnemonic(java.awt.event.KeyEvent.VK_M);
		modificarButtonComunidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				detalleComunidadButtonActionPerformed(evt);
			}
		});		
		
		JButton informeButtonComunidad = new JButton("<html><p>Informe</p><p>Inmuebles</p></html>");
		posicionBotonesPanel3.add(informeButtonComunidad);
		informeButtonComunidad.setMnemonic(java.awt.event.KeyEvent.VK_I);
		informeButtonComunidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				informeComunidadButtonActionPerformed(evt);
			}
		});		

	
	
	  // PANEL 4
	JPanel panel4 = new JPanel();
	panelDePestanas.addTab("CONCEPTOS", null, panel4, null);
	// En el centro ponemos la tabla Conceptos
	tablaScrollPane4 = new JScrollPane();
	panel4.add(tablaScrollPane4, BorderLayout.CENTER);
	{
		tablaConceptos = new JTable();
		tablaScrollPane4.setViewportView(tablaConceptos);
		tablaConceptos.setModel(modelotablaconcepto);
	}
	// Definimos el tamaño del scrollpane:
	tablaScrollPane4.setPreferredSize(new java.awt.Dimension(870, 430));
	// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
	tablaConceptos.getModel().addTableModelListener(new TableModelListener() {
	      public void tableChanged(TableModelEvent e) {
	      }
	    });
	
	tablaConceptos.setAutoCreateRowSorter(true);	

	// Panel derecho con las opciones añadir, borrar, y modificar para Comunidades
	posicionBotonesPanel4 = new JPanel();
	panel4.add(posicionBotonesPanel4, BorderLayout.EAST);
	posicionBotonesPanel4.setLayout(new GridLayout(6, 1, 0, 5));
	posicionBotonesPanel4.setPreferredSize(new java.awt.Dimension(95, 432));

	JButton addButtonConcepto = new JButton("Añadir");
	posicionBotonesPanel4.add(addButtonConcepto);
	addButtonConcepto.setMnemonic(java.awt.event.KeyEvent.VK_A);
	addButtonConcepto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			addConceptoButtonActionPerformed(evt);
		}
	});

	JButton delButtonConcepto = new JButton("Borrar");
	posicionBotonesPanel4.add(delButtonConcepto);
	delButtonConcepto.setMnemonic(java.awt.event.KeyEvent.VK_B);
	delButtonConcepto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				delConceptoButtonActionPerformed(evt);
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

	JButton modificarButtonConcepto = new JButton("Modificar");
	posicionBotonesPanel4.add(modificarButtonConcepto);
	modificarButtonConcepto.setMnemonic(java.awt.event.KeyEvent.VK_M);
	modificarButtonConcepto.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			modificarConceptoButtonActionPerformed(evt);
		}
	});		
	
	

	  // PANEL 5
	JPanel panel5 = new JPanel();
	panelDePestanas.addTab("FACTURAS", null, panel5, null);
	
	
	// En el centro ponemos la tabla Facturas
	tablaScrollPane5 = new JScrollPane();
	panel5.add(tablaScrollPane5, BorderLayout.CENTER);
	{
		tablaFacturas = new JTable();
		tablaScrollPane5.setViewportView(tablaFacturas);
		tablaFacturas.setModel(modelotablafactura);
	}
	
	tablaFacturas.setAutoCreateRowSorter(true);	
	
	// Definimos el tamaño del scrollpane:
	tablaScrollPane5.setPreferredSize(new java.awt.Dimension(870, 430));
	// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
	tablaFacturas.getModel().addTableModelListener(new TableModelListener() {
	      public void tableChanged(TableModelEvent e) {
//	         modificado=true;
	      }
	    });
	

	// Panel derecho con las opciones añadir, borrar, y modificar para Facturas
	posicionBotonesPanel5 = new JPanel();
	panel5.add(posicionBotonesPanel5, BorderLayout.EAST);
	posicionBotonesPanel5.setLayout(new GridLayout(6, 1, 0, 5));
	posicionBotonesPanel5.setPreferredSize(new java.awt.Dimension(95, 432));

	JButton addButtonFactura = new JButton("Añadir");
	posicionBotonesPanel5.add(addButtonFactura);
	addButtonFactura.setMnemonic(java.awt.event.KeyEvent.VK_A);
	addButtonFactura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				addFacturaButtonActionPerformed(evt);
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

	JButton delButtonFactura = new JButton("Borrar");
	posicionBotonesPanel5.add(delButtonFactura);
	delButtonFactura.setMnemonic(java.awt.event.KeyEvent.VK_B);
	delButtonFactura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				delFacturaButtonActionPerformed(evt);
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

	JButton modificarButtonFactura = new JButton("Modificar");
	posicionBotonesPanel5.add(modificarButtonFactura);
	modificarButtonFactura.setMnemonic(java.awt.event.KeyEvent.VK_M);
	modificarButtonFactura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				modificarFacturaButtonActionPerformed(evt);
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});		
	

	  // PANEL 6
	JPanel panel6 = new JPanel();
	panelDePestanas.addTab("RECIBOS", null, panel6, null);
	JPanel panel6_2 = new JPanel();
	panel6.add(panel6_2, BorderLayout.CENTER);
	panel6_2.setLayout(new GridLayout(2, 1));
	
	// En el centro ponemos la tabla Facturas
	tablaScrollPane6 = new JScrollPane();
	panel6_2.add(tablaScrollPane6);
	{
		tablaComunidadesCuadradas = new JTable();
		tablaScrollPane6.setViewportView(tablaComunidadesCuadradas);
		tablaComunidadesCuadradas.setModel(modelotablacomunidadcuadrada);
	}
	
	tablaComunidadesCuadradas.setAutoCreateRowSorter(true);	
	// Definimos el tamaño del scrollpane:
	tablaScrollPane6.setPreferredSize(new java.awt.Dimension(870, 230));
	// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
	tablaComunidadesCuadradas.getModel().addTableModelListener(new TableModelListener() {
	      public void tableChanged(TableModelEvent e) {
	      }
	    });
	
	tablaScrollPane6_2 = new JScrollPane();
	panel6_2.add(tablaScrollPane6_2);
	{
		tablaRecibos = new JTable();
		tablaScrollPane6_2.setViewportView(tablaRecibos);
		tablaRecibos.setModel(modelotablarecibo);
	}
	
	tablaRecibos.setAutoCreateRowSorter(true);	
	// Definimos el tamaño del scrollpane:
	tablaScrollPane6_2.setPreferredSize(new java.awt.Dimension(870, 200));
	// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
	tablaRecibos.getModel().addTableModelListener(new TableModelListener() {
	      public void tableChanged(TableModelEvent e) {
	      }
	    });
	
	// Panel derecho con las opciones añadir, borrar, y modificar para Comunidades
	posicionBotonesPanel6 = new JPanel();
	panel6.add(posicionBotonesPanel6, BorderLayout.EAST);
	posicionBotonesPanel6.setLayout(new GridLayout(6, 1, 0, 5));
	posicionBotonesPanel6.setPreferredSize(new java.awt.Dimension(95, 432));

	JButton generarButtonFactura = new JButton("<html><p>Generar</p><p> Recibos y Notas</p></html>");
	posicionBotonesPanel6.add(generarButtonFactura);
	generarButtonFactura.setMnemonic(java.awt.event.KeyEvent.VK_G);
	generarButtonFactura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				generarReciboButtonActionPerformed(evt);
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	JButton generarResguardoButtonFactura = new JButton("<html><p>Generar</p><p>Resguardo o</p><p>Justificante</p></html>");
	posicionBotonesPanel6.add(generarResguardoButtonFactura);
	generarResguardoButtonFactura.setMnemonic(java.awt.event.KeyEvent.VK_R);
	generarResguardoButtonFactura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				generarResguardoReciboButtonActionPerformed(evt);
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	JButton marcarComoPagadoButtonFactura = new JButton("<html><p>Marcar</p><p>Recibo como</p><p>Pagado</p></html>");
	posicionBotonesPanel6.add(marcarComoPagadoButtonFactura);
	marcarComoPagadoButtonFactura.setMnemonic(java.awt.event.KeyEvent.VK_M);
	marcarComoPagadoButtonFactura.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			marcarPagadoReciboButtonActionPerformed(evt);
		}
	});
	

	
	  // PANEL 7
	JPanel panel7 = new JPanel();
	panelDePestanas.addTab("NOTAS INFORMATIVAS", null, panel7, null);
	
	// En el centro ponemos la tabla Notas Informativas
	tablaScrollPane7 = new JScrollPane();
	panel7.add(tablaScrollPane7, BorderLayout.CENTER);
	{
		tablaNotasInformativas = new JTable();
		tablaScrollPane7.setViewportView(tablaNotasInformativas);
		tablaNotasInformativas.setModel(modelotablanotainformativa);
	}
	
	tablaNotasInformativas.setAutoCreateRowSorter(true);	
	
	// Definimos el tamaño del scrollpane:
	tablaScrollPane7.setPreferredSize(new java.awt.Dimension(870, 430));
	// Aqui comprobamos si han habido modificaciones en la tabla, para preguntar si guardar antes de cerrar
	tablaNotasInformativas.getModel().addTableModelListener(new TableModelListener() {
	      public void tableChanged(TableModelEvent e) {
	      }
	    });

	// Panel derecho 
	posicionBotonesPanel7 = new JPanel();
	panel7.add(posicionBotonesPanel7, BorderLayout.EAST);
	posicionBotonesPanel7.setLayout(new GridLayout(6, 1, 0, 5));
	posicionBotonesPanel7.setPreferredSize(new java.awt.Dimension(95, 432));

	JButton delButtonNota = new JButton("Borrar");
	posicionBotonesPanel7.add(delButtonNota);
	delButtonNota.setMnemonic(java.awt.event.KeyEvent.VK_B);
	delButtonNota.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				delNotaButtonActionPerformed(evt);
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	JButton generarButtonNota = new JButton("<html><p>Generar</p><p>Listado de Notificacion</p></html>");
	posicionBotonesPanel7.add(generarButtonNota);
	generarButtonNota.setMnemonic(java.awt.event.KeyEvent.VK_G);
	generarButtonNota.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			try {
				generarNotaButtonActionPerformed(evt);
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

}
	
	protected void listarRecibosPropietarioButtonActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaPropietarios.getSelectedRowCount()==1){
			modelotablapropietario.mostrarListadoRecibos(modelotablapropietario.recuperaPropietarioPorPosicion(tablaPropietarios.getSelectedRow()).getNif());
		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un propietario.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
	}

	protected void listarPropietarioButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(tablaPropietarios.getRowCount()>0){
			modelotablapropietario.mostrarInforme();
		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"No hay propietarios que listar.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
	}

	protected void generarResguardoReciboButtonActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaRecibos.getSelectedRowCount()==1){
			modelotablarecibo.mostrarInforme(tablaRecibos.getSelectedRow());
		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un recibo.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
	}

	protected void marcarPagadoReciboButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(tablaRecibos.getSelectedRowCount()==1){
			try {
				modelotablarecibo.marcarComoPagado(tablaRecibos.getSelectedRow());
			} catch (DAOExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un recibo.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
	}

	protected void generarNotaButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(tablaNotasInformativas.getSelectedRowCount()==1){
			modelotablanotainformativa.mostrarInforme(tablaNotasInformativas.getSelectedRow());
		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar una nota.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
	}

	//Borra una nota informativa y sus recibos
	protected void delNotaButtonActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaNotasInformativas.getSelectedRowCount()==0) {
			accion.setText("No ha seleccionado ninguna Nota.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"No ha seleccionado ninguna Nota!!!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaNotasInformativas.getSelectedRowCount()==1) {
			int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar la nota con id "+modelotablanotainformativa.recuperaNotaInformativaPorPosicion(tablaNotasInformativas.getSelectedRow()).getIdNota()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
			
			if (resp==JOptionPane.YES_OPTION) {
				modelotablarecibo.borraRecibosPorIdNota(modelotablanotainformativa.recuperaNotaInformativaPorPosicion(tablaNotasInformativas.getSelectedRow()).getIdNota());
				modelotablafactura.actualizarFacturaSinNotaInformativa(modelotablanotainformativa.recuperaNotaInformativaPorPosicion(tablaNotasInformativas.getSelectedRow()).getIdNota());
				modelotablanotainformativa.borraNotaInformativaPorPosicion(tablaNotasInformativas.getSelectedRow());
				accion.setText("Nota borrada con exito.");
			}
		}
		modelotablarecibo.fireTableDataChanged();
		modelotablanotainformativa.fireTableDataChanged();
	}

	protected void generarReciboButtonActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaComunidadesCuadradas.getSelectedRowCount()==1){
			Comunidad c= modelotablacomunidadcuadrada.recuperaComunidadPorPosicion(tablaComunidadesCuadradas.getSelectedRow());
			
			VentanaFacturasPorComunidad v=new VentanaFacturasPorComunidad((ModeloTablaComunidadCuadrada) modelotablacomunidadcuadrada, c, accion, (ModeloTablaRecibo) modelotablarecibo, (ModeloTablaInmueble) modelotablainmueble, (ModeloTablaNotaInformativa) modelotablanotainformativa);
			accion.setText("Visualizando las facturas de la comunidad "+ c.getNombre());
			v.setVisible(true);
	}
		
	else{
		JOptionPane.showMessageDialog(
				contentPane, 
				"Debe seleccionar una comunidad.",
				"Aviso",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void modificarFacturaButtonActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub

			if(tablaFacturas.getSelectedRowCount()==1){
				Factura f= modelotablafactura.recuperaFacturaPorPosicion(tablaFacturas.getSelectedRow());
				
				VentanaFacturas v=new VentanaFacturas(f, (ModeloTablaFactura) modelotablafactura, (ModeloTablaComunidad) modelotablacomunidad, accion, tablaFacturas.getSelectedRow(), (ModeloTablaConcepto) modelotablaconcepto);
				accion.setText("Visualizando los detalles de la factura "+ f.getNumfactura());
				v.setVisible(true);
			}
			else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar una factura.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
		modelotablaconcepto.fireTableDataChanged();
	}

	protected void delFacturaButtonActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaFacturas.getSelectedRowCount()==0) {
			accion.setText("No ha seleccionado ninguna Factura.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"No ha seleccionado ninguna Factura!!!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaFacturas.getSelectedRowCount()==1) {
			int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar la factura con id "+modelotablafactura.recuperaFacturaPorPosicion(tablaFacturas.getSelectedRow()).getNumfactura()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
			
			if (resp==JOptionPane.YES_OPTION) {
				//Borrar lineas.....
				
				modelotablafactura.borraFacturaPorPosicion(tablaFacturas.getSelectedRow());
				accion.setText("Factura borrada con exito.");
			}
		}
		
		else {
				String listaIdSeleccionados =new String();
				for(int f:tablaFacturas.getSelectedRows()) {
					if(f==tablaFacturas.getSelectedRows()[tablaFacturas.getSelectedRows().length-1])
						listaIdSeleccionados += "y " + modelotablafactura.recuperaFacturaPorPosicion(f).getNumfactura();
					else if(f==tablaFacturas.getSelectedRows()[tablaFacturas.getSelectedRows().length-2])
						listaIdSeleccionados += modelotablafactura.recuperaFacturaPorPosicion(f).getNumfactura() + " ";
					else listaIdSeleccionados += modelotablafactura.recuperaFacturaPorPosicion(f).getNumfactura() + ", ";
				}
				int resp=JOptionPane.showOptionDialog(null,
						"Va a borrar las facturas con id: "+listaIdSeleccionados+" ¿Está seguro?", 
						"Aviso",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						new Object[] { "Si", "No"},  
		 				"Si");

				if (resp==JOptionPane.YES_OPTION) {
					for(int i=tablaFacturas.getSelectedRows().length-1; i>=0; i--){
						modelotablafactura.borraFacturaPorPosicion(tablaFacturas.getSelectedRows()[i]);
					}
					accion.setText("Facturas borradas con exito.");
				}
			}
		modelotablafactura.fireTableDataChanged();
	}

	protected void addFacturaButtonActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub
		System.out.println("altaFacturaMenuItem.actionPerformed, event="+evt);
		VentanaFacturas v=new VentanaFacturas((ModeloTablaFactura) modelotablafactura, (ModeloTablaComunidad) modelotablacomunidad, accion, (ModeloTablaConcepto) modelotablaconcepto, modelotablafactura.getRowCount());
		accion.setText("Añadiendo una nueva factura...");
		v.setVisible(true);
	}
	
	//Botones Conceptos
	private void modificarConceptoButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("modificarConceptoMenuItem.actionPerformed, event="+evt);
		try{
			if(tablaConceptos.getSelectedRowCount()==1){
				Concepto c= modelotablaconcepto.recuperaConceptoPorPosicion(tablaConceptos.getSelectedRow());
				VentanaConceptos v=new VentanaConceptos(c, (ModeloTablaConcepto) modelotablaconcepto, accion, tablaConceptos.getSelectedRow());
				accion.setText("Visualizando los detalles del concepto "+ c.getClaveconcepto());
				v.setVisible(true);
			}
			else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un concepto.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(
					contentPane, 
					"Error cualquiera...",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			accion.setText("No se ha podido ver el detalle de ningun concepto.");

		}
		modelotablaconcepto.fireTableDataChanged();
		modelotablafactura.fireTableDataChanged();
	}

	private void delConceptoButtonActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		// TODO Auto-generated method stub
		if(tablaConceptos.getSelectedRowCount()==0) {
			accion.setText("No ha seleccionado ningun concepto.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"No ha seleccionado ningun concepto!!!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaConceptos.getSelectedRowCount()==1) {
			int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar el concepto con clave "+modelotablaconcepto.recuperaConceptoPorPosicion(tablaConceptos.getSelectedRow()).getClaveconcepto()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
			
			if (resp==JOptionPane.YES_OPTION) {
				try{
					if(!gestionlineasfactura.comprobarConceptos(modelotablaconcepto.recuperaConceptoPorPosicion(tablaConceptos.getSelectedRow()).getClaveconcepto())){
						modelotablaconcepto.borraConceptoPorPosicion(tablaConceptos.getSelectedRow());
						accion.setText("Concepto borrado con exito.");
					}
					else{
						JOptionPane.showMessageDialog(
								contentPane, 
								"El concepto está siendo utilizado por una línea de factura",
								"Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (Exception e){
					JOptionPane.showMessageDialog(
							contentPane, 
							"Error cualquier...",
							"Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un concepto!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
		}
		modelotablaconcepto.fireTableDataChanged();
	}

	private void addConceptoButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("altaConceptoMenuItem.actionPerformed, event="+evt);
//		VentanaConceptos v=new VentanaConceptos((GestionConceptos) gestionconceptos, accion);
		VentanaConceptos v=new VentanaConceptos((ModeloTablaConcepto) modelotablaconcepto, accion);
		accion.setText("Añadiendo un nuevo Concepto...");
		v.setVisible(true);
	}

	
	// Boton guardar. Si es la primera vez, abre el fileChooser, sino, guarda directamente
	@SuppressWarnings("unused")
	private void guardarMenuItemActionPerformed(ActionEvent evt) {
		System.out.println("guardarMenuItem.actionPerformed, event="+evt);
		
		if(tablaInmuebles.getRowCount()==0){
			JOptionPane.showMessageDialog(
					contentPane, 
					"No hay ningún inmueble que guardar.",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			accion.setText("Guardando fichero...");
			if(fichero!=null){
//				modelotablainmueble.guardaInmuebles(fichero.getAbsolutePath());
			}
			else{
				JFileChooser fileChooser = new JFileChooser();
				Component areaTexto = null;
				int seleccion = fileChooser.showSaveDialog(areaTexto);
				if (seleccion == JFileChooser.APPROVE_OPTION){
			
				fichero = fileChooser.getSelectedFile();
//				modelotablainmueble.guardaInmuebles(fichero.getAbsolutePath());	   
				}
			}
			accion.setText("Se han guardado los datos en el fichero "+fichero.getName());
			modificado=false;
		}
	}
	
	// Guardar como: siempre abre fileChooser
	private void guardarComoMenuItemActionPerformed() {
		if(tablaInmuebles.getRowCount()==0){
			JOptionPane.showMessageDialog(
					contentPane, 
					"No hay ningún inmueble que guardar.",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			accion.setText("Guardando fichero...");
			fichero=null;
			JFileChooser fileChooser = new JFileChooser();
			Component areaTexto = null;
			int status = fileChooser.showSaveDialog(areaTexto);
			if (status == JFileChooser.APPROVE_OPTION){
				fichero = fileChooser.getSelectedFile();
//				modelotablainmueble.guardaInmuebles(fichero.getAbsolutePath());
				accion.setText("Se han guardado los datos en el fichero "+fichero.getName());
				modificado=false;
			}
			else if (status == JFileChooser.CANCEL_OPTION) {
				accion.setText("Guardar fichero cancelado");
			}
		}
	}
	
	// Cargar desde un fichero del disco
	private void cargarMenuItemActionPerformed(ActionEvent evt) throws DAOExcepcion {
		System.out.println("cargarMenuItem.actionPerformed, event="+evt);
		
		accion.setText("Cargando fichero...");
		JFileChooser fileChooser = new JFileChooser();
			int status = fileChooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION){
			   fichero = fileChooser.getSelectedFile();
//			   modelotablainmueble.cargaInmuebles(fichero.getAbsolutePath());;
			   accion.setText("Se han cargado los datos del fichero "+fichero.getName());
			   modificado=false;
			}
			else if (status == JFileChooser.CANCEL_OPTION) {
			      System.out.println("Cancelado");
				  accion.setText("Cargar Fichero cancelado");
			    }
	}
	
	//Salir
	private void salirMenuItemActionPerformed(ActionEvent evt) {
		System.out.println("salirMenuItem.actionPerformed, event="+evt);
		windowclosing();
	}
	

	// Para cambiar el color de fuente
	private void colorFuenteActionPerformed(ActionEvent evt) {
		System.out.println("colorFuente.actionPerformed, event="+evt);
		
		Color fuente = JColorChooser.showDialog(this, "Seleccione un color", this.getForeground());

		// Cambia el color a todos los componentes de menuBar (archivoMenu y aspectoMenu)
		for(Component comp : menuBar.getComponents()) 
		    if(comp instanceof JComponent) 
		       ((JComponent)comp).setForeground(fuente);
		       
		// Cambia el color a todos los componentes de menuBar (addButton, delButton y detalleButton
		for(Component comp : posicionBotonesPanel.getComponents()) 
		    if(comp instanceof JComponent) 
		       ((JComponent)comp).setForeground(fuente);
		    
		accion.setForeground(fuente);
		tablaInmuebles.setForeground(fuente);
		salirMenuItem.setForeground(fuente);
		// Solo hemos puesto unos cuantos ya que hay muchos componentes
		
		accion.setText("Se ha cambiado el color fuente.");

	}
	
	// Para cambiar el color de fondo
	private void colorFondoActionPerformed(ActionEvent evt) {
		System.out.println("colorFondo.actionPerformed, event="+evt);
		
		Color fondo = JColorChooser.showDialog(this, "Seleccione un color", this.getBackground());
	
		for(Component comp : tablaScrollPane.getComponents()) 
		    if(comp instanceof JComponent) 
		       ((JComponent)comp).setBackground(fondo);
		       
		for(Component comp : toolBar.getComponents()) 
		    if(comp instanceof JComponent) 
		       ((JComponent)comp).setBackground(fondo);
		       
		for(Component comp : contentPane.getComponents()) 
		    if(comp instanceof JComponent) 
		       ((JComponent)comp).setBackground(fondo);
		        
		menuBar.setBackground(fondo);
		tablaInmuebles.setBackground(fondo);
        contentPane.setBackground(fondo);
        tablaScrollPane.setBackground(fondo);

  		accion.setText("Se ha cambiado el color de fondo.");
	}


	private void addButtonActionPerformed(ActionEvent evt) {
		System.out.println("addButton.actionPerformed, event="+evt);

		VentanaDetalle v=new VentanaDetalle((ModeloTablaInmueble) tablaInmuebles.getModel(), (ModeloTablaPropietario) tablaPropietarios.getModel(), (ModeloTablaComunidad) tablaComunidades.getModel(), accion/*, listaPropietarios*/);
		accion.setText("Añadiendo un nuevo Inmueble...");
		v.setVisible(true);
	}

	private void delButtonActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		System.out.println("delButton.actionPerformed, event="+evt);

		if(tablaInmuebles.getSelectedRowCount()!=1) {
			accion.setText("Debe seleccionar un inmueble.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un inmueble!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaInmuebles.getSelectedRowCount()==1) {
			int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar el inmueble con id "+modelotablainmueble.recuperaInmueblePorPosicion(tablaInmuebles.getSelectedRow()).getIdInmueble()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
			
			if (resp==JOptionPane.YES_OPTION) {
				if(!modelotablarecibo.comprobarInmuebles(modelotablainmueble.recuperaInmueblePorPosicion(tablaInmuebles.getSelectedRow()).getIdInmueble())){
					Comunidad c = modelotablainmueble.recuperaInmueblePorPosicion(tablaInmuebles.getSelectedRow()).getComunidad();
					modelotablainmueble.borraInmueblePorPosicion(tablaInmuebles.getSelectedRow());
					if(modelotablainmueble.sumaPorcentajes(c.getNombre())==100){
						modelotablacomunidad.actualizaComunidad(c);
					}
					else{ 
						modelotablacomunidad.actualizaComunidad(c);
					}
					accion.setText("Inmueble borrado con exito.");
				}
				else{
					JOptionPane.showMessageDialog(
							contentPane, 
							"No se puede borrar un inmueble que tenga algún recibo.",
							"Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		/*
		else if(tablaInmuebles.getRowCount()==tablaInmuebles.getSelectedRowCount()) {
			int resp=JOptionPane.showOptionDialog(null,
					"Va a borrar todos los inmuebles de la tabla. ¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,                                     
		            new Object[] { "Si", "No"},  
		 			"Si");
			if (resp==JOptionPane.YES_OPTION) {
				modelotablainmueble.vaciarModelo();
				modelotablainmueble.fireTableDataChanged();  
				
				accion.setText("Se han borrado todos los inmuebles con exito.");
			}
				
		 }
		else {
				String listaIdSeleccionados =new String();
				for(int i:tablaInmuebles.getSelectedRows()) {
					if(i==tablaInmuebles.getSelectedRows()[tablaInmuebles.getSelectedRows().length-1])
						listaIdSeleccionados += "y " + modelotablainmueble.recuperaInmueblePorPosicion(i).getIdInmueble();
					else if(i==tablaInmuebles.getSelectedRows()[tablaInmuebles.getSelectedRows().length-2])
						listaIdSeleccionados += modelotablainmueble.recuperaInmueblePorPosicion(i).getIdInmueble() + " ";
					else listaIdSeleccionados += modelotablainmueble.recuperaInmueblePorPosicion(i).getIdInmueble() + ", ";
				}
				int resp=JOptionPane.showOptionDialog(null,
						"Va a borrar los inmuebles con id: "+listaIdSeleccionados+" ¿Está seguro?", 
						"Aviso",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						new Object[] { "Si", "No"},  
		 				"Si");

				if (resp==JOptionPane.YES_OPTION) {
					for(int i=tablaInmuebles.getSelectedRows().length-1; i>=0; i--){
						modelotablainmueble.borraInmueblePorPosicion(tablaInmuebles.getSelectedRows()[i]);
					}
					accion.setText("Inmuebles borrados con exito.");
				}
			}
			*/
		modelotablainmueble.fireTableDataChanged();	
	}
	       
	private void detalleButtonActionPerformed(ActionEvent evt) {
		System.out.println("detalleButton.actionPerformed, event="+evt);
		
		try{
			if(tablaInmuebles.getSelectedRowCount()==1){
				Inmueble i= modelotablainmueble.recuperaInmueblePorPosicion (tablaInmuebles.getSelectedRow());	
				VentanaDetalle v=new VentanaDetalle(i, accion, (ModeloTablaInmueble) tablaInmuebles.getModel(), tablaInmuebles.getSelectedRow(), (ModeloTablaPropietario) tablaPropietarios.getModel(), (ModeloTablaComunidad) tablaComunidades.getModel());
				accion.setText("Visualizando los detalles del inmueble "+ Integer.toString(i.getIdInmueble()));
				v.setVisible(true);
			}
			else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un inmueble.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(
					contentPane, 
					"Error cualquiera...",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			accion.setText("No se ha podido ver el detalle de ningun inmueble.");

		}
		modelotablainmueble.fireTableDataChanged();
	}
	
	private void addPropietarioButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("altaPropietarioMenuItem.actionPerformed, event="+evt);
		VentanaPropietarios vp=new VentanaPropietarios((ModeloTablaPropietario) tablaPropietarios.getModel(), accion);
		accion.setText("Añadiendo un nuevo Propietario...");
		vp.setVisible(true);
	}
	
	private void delPropietarioButtonActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		// TODO Auto-generated method stub
		
		if(tablaPropietarios.getSelectedRowCount()==0) {
			accion.setText("No ha seleccionado ningun propietario.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"No ha seleccionado ningun propietario!!!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaPropietarios.getSelectedRowCount()==1) {
			int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar el propietario con nif "+modelotablapropietario.recuperaPropietarioPorPosicion(tablaPropietarios.getSelectedRow()).getNif()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
			
			if (resp==JOptionPane.YES_OPTION)  
				if(modelotablainmueble.ComprobarPropietariosPorNIF(modelotablapropietario.recuperaNIFPropietarioPorPosicion(tablaPropietarios.getSelectedRow()))) {
				JOptionPane.showMessageDialog(
						contentPane, 
						"No puede eliminar un propietario que tenga algun inmueble!",
						"Error",
						JOptionPane.ERROR_MESSAGE);
				}else{
					modelotablapropietario.borraPropietarioPorPosicion(tablaPropietarios.getSelectedRow());
					accion.setText("Propietario borrado con exito.");
				}

		}else{
			JOptionPane.showMessageDialog(
					contentPane, 
					"Debe seleccionar un propietario.",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	
	private void modificarPropietarioButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("modificarPropietarioButton.actionPerformed, event="+evt);
		
		try{
			if(tablaPropietarios.getSelectedRowCount()==1){
				Propietario p= modelotablapropietario.recuperaPropietarioPorPosicion (tablaPropietarios.getSelectedRow());
				VentanaPropietarios v=new VentanaPropietarios(p, accion, (ModeloTablaPropietario) tablaPropietarios.getModel(), tablaPropietarios.getSelectedRow());
				accion.setText("Visualizando los detalles del propietarios "+ p.getNif());
				v.setVisible(true);
			}else
				JOptionPane.showMessageDialog(
						contentPane, 
						"Debe seleccionar un propietario.",
						"Aviso",
						JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e){
			JOptionPane.showMessageDialog(
					contentPane, 
					"Error cualquiera...",
					"Aviso",
					JOptionPane.INFORMATION_MESSAGE);
			accion.setText("No se ha podido ver el detalle de ningun propietario.");

		}
		modelotablapropietario.fireTableDataChanged();
		modelotablainmueble.fireTableDataChanged();
	}

	private void addComunidadButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		System.out.println("altaComunidadMenuItem.actionPerformed, event="+evt);
		VentanaComunidades vc=new VentanaComunidades((ModeloTablaComunidad) tablaComunidades.getModel(),(ModeloTablaInmueble) tablaInmuebles.getModel(), accion);
		accion.setText("Añadiendo un nuevo Propietario...");
		vc.setVisible(true);
	}

	private void delComunidadButtonActionPerformed(ActionEvent evt) throws HeadlessException, DAOExcepcion {
		// TODO Auto-generated method stub
		
		if(tablaComunidades.getSelectedRowCount()==0) {
			accion.setText("No ha seleccionado ninguna comunidad.");
			JOptionPane.showMessageDialog(
					contentPane, 
					"No ha seleccionado ninguna comunidad!!!",
					"Notificación",
					JOptionPane.INFORMATION_MESSAGE);
			
		}else if(tablaComunidades.getSelectedRowCount()==1) {
				int resp=JOptionPane.showOptionDialog(
					null,  
					"Está a punto de borrar la comunidad con id "+modelotablacomunidad.recuperaComunidadPorPosicion(tablaComunidades.getSelectedRow()).getNombre()+
					"¿Está seguro?", 
					"Aviso",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, 
					null,                                     
	                new Object[] { "Si", "No"},  
    		 		"Si");
		
			if (resp==JOptionPane.YES_OPTION) {
				if(modelotablainmueble.ComprobarComunidadesPorNombre(modelotablacomunidad.recuperaIdComunidadPorPosicion(tablaComunidades.getSelectedRow()))){
					JOptionPane.showMessageDialog(
							contentPane, 
							"No puede eliminar una comunidad que tenga algun inmueble!",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}else{
					modelotablacomunidad.borraComunidadPorPosicion(tablaComunidades.getSelectedRow());
					accion.setText("Propietario borrado con exito.");	
				}
			}
		}
	}

	private void detalleComunidadButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		try{
			if(tablaComunidades.getSelectedRowCount()==1){
				Comunidad c= modelotablacomunidad.recuperaComunidadPorPosicion (tablaComunidades.getSelectedRow());
				VentanaComunidades v=new VentanaComunidades(c, accion, (ModeloTablaComunidad) tablaComunidades.getModel(), (ModeloTablaInmueble) tablaInmuebles.getModel(),tablaComunidades.getSelectedRow(), (ModeloTablaComunidadCuadrada) tablaComunidadesCuadradas.getModel());
				accion.setText("Visualizando los detalles de la Comunidad "+ c.getNombre());
				v.setVisible(true);
			}else
				JOptionPane.showMessageDialog(
						contentPane, 
						"Debe seleccionar una Comunidad!!!",
						"Aviso",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e){
				JOptionPane.showMessageDialog(
						contentPane, 
						"Error cualquiera...",
						"Aviso",
						JOptionPane.INFORMATION_MESSAGE);
				accion.setText("No se ha podido ver el detalle de ninguna Comunidad.");

			}
			modelotablacomunidad.fireTableDataChanged();
	}
	
	
	private void informeComunidadButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
	if(tablaComunidades.getSelectedRowCount()==1)
		modelotablacomunidad.mostrarInforme(tablaComunidades.getSelectedRow());
	else{
		JOptionPane.showMessageDialog(
				contentPane, 
				"Debe seleccionar una comunidad.",
				"Aviso",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/*
	private void recibosynotasComunidadButtonActionPerformed(ActionEvent evt) throws DAOExcepcion {
		// TODO Auto-generated method stub
	if(tablaComunidades.getSelectedRowCount()==1 && ((String) modelotablacomunidad.getValueAt(tablaComunidades.getSelectedRow(), 3)).trim().equals("Cuadrado")){
		Comunidad c= modelotablacomunidadcuadrada.recuperaComunidadPorPosicion(tablaComunidades.getSelectedRow());
	
		VentanaFacturasPorComunidad v=new VentanaFacturasPorComunidad((ModeloTablaComunidad) modelotablacomunidad, c, accion, (ModeloTablaRecibo) modelotablarecibo, (ModeloTablaInmueble) modelotablainmueble, (ModeloTablaNotaInformativa) modelotablanotainformativa);
		accion.setText("Visualizando las facturas de la comunidad "+ c.getNombre());
		v.setVisible(true);
	}else{
		JOptionPane.showMessageDialog(
				contentPane, 
				"Debe seleccionar una comunidad cuyo porcentaje esté cuadrado.",
				"Aviso",
				JOptionPane.INFORMATION_MESSAGE);
		}
	}
*/
	
	private void thisWindowClosing(WindowEvent evt) {
		System.out.println("this.windowClosing, event="+evt);
		windowclosing();
	}
	
	
	private void windowclosing(){
/*		if(modificado==true){
			int seleccion = JOptionPane.showOptionDialog(
	                 contentPane,
	                 "Está a punto de salir sin guardar los cambios. ¿Está seguro?", 
	                 "¡Aviso!",
	                 JOptionPane.YES_NO_CANCEL_OPTION,         
	                 JOptionPane.QUESTION_MESSAGE,              
	                 null,                                     
	                 new Object[] { "Guardar y Salir", "Salir sin Guardar", "Cancelar"},  
	         		 "Guardar y Salir");       
			if(seleccion==JOptionPane.YES_OPTION){
				guardarComoMenuItemActionPerformed();
				System.exit(0);
			}
			else if(seleccion==JOptionPane.NO_OPTION)
				System.exit(0);
		}
		else{
			// Confirmamos antes de salir, por si alquien le da a cerrar sin querer :)
			int resp=JOptionPane.showConfirmDialog(null,  "¿Está seguro de que desea salir?", "Aviso", JOptionPane.YES_NO_OPTION); 
			if (resp==JOptionPane.YES_OPTION)
			System.exit(0);
		}	
		*/
		System.exit(0);
	}
}
