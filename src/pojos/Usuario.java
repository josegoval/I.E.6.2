package pojos;

/**
 * Clase de Usuario.
 * 
 * @author Jose Manuel Gomez Martinez
 * @author Manuel Jimenez Jimenez
 * @since 02/02/2020
 */
public class Usuario {

	/**
	 * Nombre fijo del usuario.
	 */
	private final String NAME;
	/**
	 * Credito actual del usuario.
	 */
	private double credito;
	
//	CONSTRUCTORES
	/**
	 * Constructor que inicializa todos los atributos de la clase.
	 * @author Manuel Jimenez Jimenez
	 * @since 02/02/2020
	 */
	public Usuario(String nombre, double creditoInicial) {
		NAME = nombre;
		credito = creditoInicial;
	}
	
//	METODOS
//	SETTERS & GETTERS
	public double getCredito() {
		return credito;
	}
	
	public void setCredito(double credito) {
		this.credito = credito;
	}
	
	public String getNAME() {
		return NAME;
	}
			
}
