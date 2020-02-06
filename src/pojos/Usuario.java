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
	 * Incrementa el credito del usuario en la cantidad dada.
	 * @param cantidad Cantidad a sumar.
	 * @author Manuel Jimenez Jimenez
	 * @since 02/02/2020
	 */
	public void incrementarCredito(double cantidad) {
		credito += cantidad;
	}
	
	/**
	 * Decrementa el credito del usuario en la cantidad dada.
	 * @param cantidad Cantidad a restar.
	 * @author Manuel Jimenez Jimenez
	 * @since 02/02/2020
	 */
	public void decrementarCredito(double cantidad) {
		credito -= cantidad
	}
	
	/**
	 * @author Jose Manuel Gomez Martinez
	 * @since 04/02/2020
	 */
	public void crearSubasta() {
		// Esta primera variable aun queda a la espera del desarrollo de Manuel
		Subasta subastaCreada = new Subasta(propietario, descripcion, fechaLimite);
		// Guarda la subasta, en las subastas creadas por el usuario
		misSubastas.add(subastaCreada);
		
//		PODRIA SER QUE TUVIESE RETURN SUBASTA PARA UNA MAYOR ACCESIBILIDAD
		
	}
	
	/**
	 * Imprime los datos de de las subastas ganadas si existiese alguna.
	 * Para que una subasta este ganada, dicha subasta no puede estar abierta,
	 * y ademas la pujaMayor tiene que coincidir con la puja.
	 * @author Jose Manuel Gomez Martinez
	 * @since 02/02/2020
	 */
	public void consultarSubastasGanadas() {
		// FORMA ANTIGUA DE HACERLO
//		Esta forma tenia en cuenta la anterior coleccion de subastasGanadas que 
//		se suprimio por hacerlo todo con el metodo Stream solo con 2 colecciones
//		todo.
//		// Comprueba si ha ganado subastas...
//		if (subastasGanadas.isEmpty()) {
//			System.out.println("No has ganado ninguna subasta aun.");
//		} else {
//			// Imprime los datos de las subastas ganadas...
//			for (Subasta subasta : subastasGanadas) {
//				System.out.println("*******");
//				System.out.println("Subasta de " + subasta.getDESCRIPCION());
//				System.out.println("Creada por " + subasta.getPROPIETARIO()
//				+ " en " + subasta.getFechaCreacion() + " y finalizada en "
//				+ subasta.getFechaLimite());
//				System.out.println("Ganada con " + subasta.getPujaMayor().getCANTIDAD()
//						+ " euros, en " + subasta.getPujaMayor().getFECHA());
//			}
//		}
		
		// Comprueba si ha ganado alguna subasta
		if (pujasAceptadas.isEmpty()) {
			System.out.println("No has ganado ninguna subasta aun");
		} else {
			// Se imprimen los datos de las subastas ganadas.
			pujasAceptadas.stream()
			.filter(p -> p == p.getSUBASTA().getPujaMayor() &&
					p.getSUBASTA().getEstado()!=EstadoSubasta.ABIERTA)
			.forEach(s -> System.out.println("*******"
					+ "\nSubasta de " + s.getSUBASTA().getDESCRIPCION()
					+ "\nCreada por " + s.getSUBASTA().getPROPIETARIO() + " en " 
						+ s.getSUBASTA().getFechaCreacion()
						+ " y finalizada en " + s.getSUBASTA().getFechaLimite()
					+ "\nGanada con " + s.getCANTIDAD() + " euros, en " + s.getFECHA()));
		}
		
	}	
	
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
