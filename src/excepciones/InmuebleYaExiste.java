package excepciones;

@SuppressWarnings("serial")
public class InmuebleYaExiste extends Exception {
	
	public InmuebleYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="El inmueble ya existe";
		return s;  
	}
}
