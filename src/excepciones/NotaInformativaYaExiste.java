package excepciones;

@SuppressWarnings("serial")
public class NotaInformativaYaExiste extends Exception {
	
	public NotaInformativaYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="La nota ya existe";
		return s;  
	}
}
