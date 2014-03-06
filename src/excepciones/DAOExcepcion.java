package excepciones;

// Esta clase representa una excepción genérica DAO. Enmascara
// aquellas excepciones de bajo nivel como SQLException
public class DAOExcepcion extends Exception {
	    // Constructors -------------------------------------------------------------------------------

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/**
	     * Constructs a DAOException with the given detail message.
	     * @param message The detail message of the DAOException.
	     */
	    public DAOExcepcion(String message) {
	        super(message);
	    }

	    /**
	     * Constructs a DAOException with the given root cause.
	     * @param cause The root cause of the DAOException.
	     */
	    public DAOExcepcion(Throwable cause) {
	        super(cause);
	    }

	    /**
	     * Constructs a DAOException with the given detail message and root cause.
	     * @param message The detail message of the DAOException.
	     * @param cause The root cause of the DAOException.
	     */
	    public DAOExcepcion(String message, Throwable cause) {
	        super(message, cause);
	    }


	
	
}
