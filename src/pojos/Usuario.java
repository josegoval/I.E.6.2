package pojos;

import java.util.ArrayList;
import java.util.List;

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
	
//	/**
//	 * Coleccion que guarda todas las subastas creadas/generadas por
//	 * el usuario, sea cual sea su estado (ABIERTA, CERRADA, EJECUTADA).
//	 */
//	private List<Subasta> misSubastas = new ArrayList<Subasta>();
	
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
	
	
	
	/**
	 * Metodo que devuelve la informacion de las subastas creadas
	 * por el usuario, si ha creado previamente alguna. En caso contrario
	 * se le avisara de que no ha creado ninguna aun.
	 * @author Jose Manuel Gomez Martinez
	 * @since 04/02/2020
	 */
	public void consultarMisSubastas() {
		// FORMA ANTIGUA DE HACERLO
//		if (misSubastas.isEmpty()) {
//			System.out.println("Aun no has creado ninguna subasta.");
//		} else {
//			for (Subasta subasta : misSubastas) {
//				System.out.println("********");
//				System.out.println("Subasta: " + subasta.getDESCRIPCION());
//				System.out.println("Estado: " + subasta.getEstado().name());
//				System.out.println("Fecha de creacion: " + subasta.getFechaCreacion());
//				System.out.println("Fecha de cierre: " + subasta.getFechaLimite());
//				System.out.println("Puja Mayor: " + subasta.getPujaMayor().getCANTIDAD());
//			}
//		}
		
		// Consulto si ha creado subastas primero...
		if (misSubastas.isEmpty()) {
			System.out.println("Aun no has creado ninguna subasta.");
		} else {
			misSubastas.stream()
			.forEach(s -> {
				System.out.println("********");
				System.out.println("Subasta: " + s.getDESCRIPCION());
				System.out.println("Fecha de creacion: " + s.getFechaCreacion());
				System.out.println("Fecha de cierre: " + s.getFechaLimite());
				System.out.println("Puja Mayor: " + s.getPujaMayor().getCANTIDAD() + "€");
			});
		}
		
	}

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
//	public List<Subasta> getMisSubastas() {
//		return misSubastas;
//	}
			
}
