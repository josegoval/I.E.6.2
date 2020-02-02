package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Usuario.
 * 
 * @author Jose
 * @since 02/02/2020
 */
public class Usuario {

	/**
	 * Coleccion que guarda todas las subastas que gano el usuario, es
	 * decir, aquellas que cuando se cerraron, el poseia la pujaMayor.
	 */
	List<Subasta> subastasGanadas = new ArrayList<Subasta>();
	
//	CONSTRUCTORES
	
//	METODOS
	/**
	 * Imprime los datos de de las subastas ganadas si existiese alguna.
	 * @author Jose Manuel Gomez Martinez
	 * @since 02/02/2020
	 */
	public void consultarSubastasGanadas() {
		// FORMA ANTIGUA DE HACERLO
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
		
		// Comprueb si ha ganado alguna subasta
		if (subastasGanadas.isEmpty()) {
			System.out.println("No has ganado ninguna subasta aun");
		} else {
			// Se imprimen los datos de las subastas ganadas.
			subastasGanadas.stream()
			.forEach(s -> System.out.println("*******"
					+ "\nSubasta de " + s.getDESCRIPCION()
					+ "\nCreada por " + s.getPROPIETARIO() + " en " + s.getFechaCreacion()
					+ " y finalizada en " + s.getFechaLimite()
					+ "\nGanada con " + s.getPujaMayor().getCANTIDAD() + " euros, en "
					+ s.getPujaMayor().getFECHA()));
		}
//		HAY UNA FORMA DIFERENTE, QUE SERIA CONSULTAR TODAS MIS PUJAS, Y VER
//		CUALES DE ELLAS TIENEN EL MISMO VALOR QUE LA PUJA MAYOR DE ESA SUBASTA. 
//		ESO AHORRARIA UNA COLECCION, PERO HAY QUE PREGUNTAR.
	}	
	
//	SETTERS & GETTERS
	public List<Subasta> getSubastasGanadas() {
		return subastasGanadas;
	}
			
}
