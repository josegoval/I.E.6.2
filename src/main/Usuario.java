package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase de Usuario.
 * 
 * @author Jose 
 * @author Manuel
 * @since 02/02/2020
 */
public class Usuario {
//ATRIBUTOS
	//Los escritos en mayúscula son constantes (y por tanto FINAL)
	private final String NAME;
	private double credito;
	private static Scanner src;

	/**
	 * Coleccion que guarda todas las subastas creadas/generadas por
	 * el usuario, sea cual sea su estado (ABIERTA, CERRADA, EJECUTADA).
	 */
	List<Subasta> misSubastas; //Esto se instancia en el constructor
	
//	CONSTRUCTORES
	public Usuario (String NAME, double credito) { //Las instanciaciones no requieren declarar variables dentro del paréntesis
		this.NAME = NAME;
		this.credito = credito;
		src=new Scanner(System.in);
		misSubastas = new ArrayList<Subasta>();
	//Los atributos con "new" como el Scanner o el ArrayList hay que instanciarlos en el constructor, sino no se inician
	}
	
//	MÉTODOS
	/**
	 * Imprime los datos de de las subastas ganadas si existiese alguna.
	 * @author Jose Manuel Gomez Martinez
	 * @author Manuel Jimenez Jimenez
	 * @since 02/02/2020
	 */
	public void incrementarCredito (double cantidad) {
		credito += cantidad;
	}
	public void decrementarCredito (double cantidad) {
		credito -= cantidad();
	}
	/**
	 * OTRA FORMA (MENOS SIMPLE):
	public void incrementarCredito () {
		System.out.println("Introduzca cantidad que aumentar al crédito:");
		credito += src.nextDouble();
	}
	public void decrementarCredito () {
		System.out.println("Introduzca cantidad que aumentar al crédito:");
		credito -= src.nextDouble();
	}
	*/
	public double getCredito () {
		return credito;
	}
	public void setCredito () {
		this.credito = credito;
		
	}
	public void crearSubasta () {
		System.out.println("Escribe una descripción del objeto de la puja:");
		NAME = src.nextLine();
		//Para la duración de la subasta necesitamos poder controlar tanto la fecha como la hora,
		// para lo cual está la api "LocalDateTime", utilizada a continuación:
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println("Introduce duración de la subasta:");
		System.out.println(dateTime);
	}
	public void consultarPujas () {
		
	}
	public void consultarMisSubastas () {
		
	}
	
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

