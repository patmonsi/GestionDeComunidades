package excepciones;

@SuppressWarnings("serial")
public class ComunidadYaExiste extends Exception {
	
	public ComunidadYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="La comunidad ya existe";
		return s;  
	}
}
