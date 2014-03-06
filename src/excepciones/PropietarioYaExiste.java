package excepciones;

@SuppressWarnings("serial")
public class PropietarioYaExiste extends Exception {
	
	public PropietarioYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="El propietario ya existe";
		return s;  
	}
}
