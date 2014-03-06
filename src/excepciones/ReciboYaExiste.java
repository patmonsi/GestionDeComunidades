package excepciones;

@SuppressWarnings("serial")
public class ReciboYaExiste extends Exception {
	
	public ReciboYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="El Recibo ya existe";
		return s;  
	}
}
