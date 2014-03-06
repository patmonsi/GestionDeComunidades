package excepciones;

@SuppressWarnings("serial")
public class FacturaYaExiste extends Exception {
	
	public FacturaYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="La factura ya existe";
		return s;  
	}
}
