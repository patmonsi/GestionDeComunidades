package excepciones;

@SuppressWarnings("serial")
public class LineaFacturaYaExiste extends Exception {
	
	public LineaFacturaYaExiste() {
		
	}
	
	
	@Override
	public String getMessage(){
		String s="La Linea de Factura ya existe";
		return s;  
	}
}
