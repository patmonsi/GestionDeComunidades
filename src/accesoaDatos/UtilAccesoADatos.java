package accesoaDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.DAOConfiguracionExcepcion;
import excepciones.DAOExcepcion;

public final class UtilAccesoADatos {

	  private static Connection con = null;
	  private static final String baseDatosUrl = new String("jdbc:hsqldb:hsql://localhost/comunidades");
  
	  public static void EstablecerConexion() {
	    // comprueba si el driver se puede cargar
	    try {
	      Class.forName("org.hsqldb.jdbcDriver");
	    }
	    catch (Exception e) {
	      System.out.println("Error: No se encuentra el Driver " + e.getMessage() + "\n");
	      throw new DAOConfiguracionExcepcion("Error: no se encuentra el driver " + e.getMessage());
	    }
	    // establece la conexión con la  base de datos.
	    try {
	      con = DriverManager.getConnection(baseDatosUrl);
	    }
	    catch (SQLException e) {
	      con = null;
	      System.out.println("Error al conectar con la base de datos " + e + "\n");
	      throw new DAOConfiguracionExcepcion("Error: no se puede conectar con la base de datos. " + e.getMessage());
	    }
	  }

	  public static void Cerrar(PreparedStatement sentenciaPreparada, ResultSet rs) throws DAOExcepcion 
	  {
		  try {
			cerrarConexion(rs);
			cerrarConexion(sentenciaPreparada);
			  CerrarConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOExcepcion(e);
		}
	  }
	  
	  public static void Cerrar(PreparedStatement sentenciaPreparada) throws DAOExcepcion
	  {
		  try {
			cerrarConexion(sentenciaPreparada);
			CerrarConexion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOExcepcion(e);
		}
	  }
	    
//		METODO GENERAL PARA REALIZAR CUALQUIER CONSULTA SOBRE LA BD
		public static ResultSet consultarBD(String consulta) throws DAOExcepcion
		{
		  ResultSet rs=null;

		  try
		  {
		    Statement st = con.createStatement();
		    rs = st.executeQuery(consulta);
		  }
		  catch(SQLException e)
		  {
		    System.out.println("Error en la consulta  " + e+"\n");
		    throw new DAOExcepcion(e.getMessage());
		  }
		  return(rs);
		}

//		 METODO GENERAL PARA INSERTAR O CAMBIAR INFORMACIÓN SOBRE LA BD
		public static void actualizarBD(String consulta) throws DAOExcepcion
//		 para borrar, actualizar e insertar.
		{
		   try {
			 PreparedStatement actualizarbd = null;
			 actualizarbd = con.prepareStatement(consulta);
			 int filasafectadas  = actualizarbd.executeUpdate();
			 if (filasafectadas == 0) 
				 throw new DAOExcepcion("Fallo en la actualización de la BD");
		       }
		  catch(SQLException e)
		  {
		    System.out.println("Error en la consulta " + e + "\n");
		    throw new DAOExcepcion(e.getMessage());
		  }
		}
		
		// cierre de conexiones, sentencias y resultsets.
		public static void CerrarConexion() {
		    try {
		      if (con!=null) con.close();
		    }catch (Exception e1) 	{
		    	System.out.println("Error al cerrar BD");
		    	throw new DAOConfiguracionExcepcion("Error: no se puede cerrar la base de datos. " + e1.getMessage());}
		  }
		
		public static void cerrarConexion(ResultSet r) throws SQLException
		{
			if (r!=null)
				try {
					r.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error al cerrar el resultset " + e+"\n");
					e.printStackTrace();
					throw e;
				}
		}

		public static void cerrarConexion(Statement st) throws SQLException
		{
			if (st!=null)
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error al cerrar el resultset " + e+"\n");
					e.printStackTrace();
					throw e;
				}
		}

		
		// recibe una sentencia sql y un conjunto de valores, retorna la sentencia
		// preparada con los valores.
		// La notación Object... values indica un número variable de parámetros.
		 public static PreparedStatement prepareStatement(String sql, Object... values)
	            throws SQLException
	    {
	        PreparedStatement preparedStatement = con.prepareStatement(sql);
	        setValues(preparedStatement, values);
	        return preparedStatement;
	    }

		// asigna los valores a la sentencia preparada. 
		public static void setValues(PreparedStatement preparedStatement, Object... values)
        throws SQLException
        {
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }
	
}
