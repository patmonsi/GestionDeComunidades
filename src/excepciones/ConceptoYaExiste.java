package excepciones;

@SuppressWarnings("serial")
public class ConceptoYaExiste extends Exception {

	public ConceptoYaExiste() {
		
	}
	
	@Override
	public String getMessage(){
		String s="El concepto ya existe";
		return s;  
	}
}
