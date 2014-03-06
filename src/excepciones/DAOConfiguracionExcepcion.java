package excepciones;

// Representa excepciones en tiempo de ejecución que no pueden ser
// resueltas, como el fallo en la conexión a la base de datos.
// es una excepción no verificable, i.e no necesita un manejador.

public class DAOConfiguracionExcepcion extends RuntimeException {

	    // Constructors -------------------------------------------------------------------------------

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
	     * Constructs a DAOConfigurationException with the given detail message.
	     * @param message The detail message of the DAOConfigurationException.
	     */
	    public DAOConfiguracionExcepcion(String message) {
	        super(message);
	    }

	    /**
	     * Constructs a DAOConfigurationException with the given root cause.
	     * @param cause The root cause of the DAOConfigurationException.
	     */
	    public DAOConfiguracionExcepcion(Throwable cause) {
	        super(cause);
	    }

	    /**
	     * Constructs a DAOConfigurationException with the given detail message and root cause.
	     * @param message The detail message of the DAOConfigurationException.
	     * @param cause The root cause of the DAOConfigurationException.
	     */
	    public DAOConfiguracionExcepcion(String message, Throwable cause) {
	        super(message, cause);
	    }

}
