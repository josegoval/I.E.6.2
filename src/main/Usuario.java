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
	 * Coleccion que guarda todas las subastas creadas/generadas por
	 * el usuario, sea cual sea su estado (ABIERTA, CERRADA, EJECUTADA).
	 */
	List<Subasta> misSubastas = new ArrayList<Subasta>();
	
//	CONSTRUCTORES
	
//	METODOS
	/**
	 * Imprime los datos de de las subastas ganadas si existiese alguna.
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
			.filter(p -> p.getCANTIDAD() == p.getSUBASTA().getPujaMayor().getCANTIDAD())
			.forEach(s -> System.out.println("*******"
					+ "\nSubasta de " + s.getSUBASTA().getDESCRIPCION()
					+ "\nCreada por " + s.getSUBASTA().getPROPIETARIO() + " en " 
						+ s.getSUBASTA().getFechaCreacion()
						+ " y finalizada en " + s.getSUBASTA().getFechaLimite()
					+ "\nGanada con " + s.getCANTIDAD() + " euros, en " + s.getFECHA()));
		}
		
	}	
	
	public void consultarMisSubastas() {
		// FORMA ANTIGUA DE HACERLO
		if (misSubastas.isEmpty()) {
			System.out.println("Aun no has creado ninguna subasta.");
		} else {
			for (Subasta subasta : misSubastas) {
				System.out.println("********");
				System.out.println("Subasta: " + subasta.getDESCRIPCION());
				System.out.println("Estado: " + subasto.);
			}
		}
		
	}
//	SETTERS & GETTERS
	public List<Subasta> getSubastasGanadas() {
		return subastasGanadas;
	}
			
}
