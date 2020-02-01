package main;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase de Subasta.
 * 
 * @author Jose Manuel Gomez Martinez
 * @since 31/01/2020
 */
public class Subasta {
	
	/**
	 * Usuario propietario de la subasta.
	 */
	private final Usuario PROPIETARIO;
	/**
	 * Descripcion de la subasta.
	 */
	private final String DESCRIPCION;
	/**
	 * Estado actual de la subasta.
	 */
	private EstadoSubasta estado;
	/**
	 * La puja mayor que va ganando en la subasta,
	 * es decir, la ultima puja aceptada.
	 */
	private Puja pujaMayor;
	/**
	 * Coleccion (ArrayList) que guarda todas las pujas aceptadas
	 * que ha recibido la subasta.
	 */
	private List<Puja> pujas = new ArrayList<Puja>();
	/**
	 * Fecha de creacion de la subasta.
	 */
	private LocalDateTime fechaCreacion;
	/**
	 * Fecha limite de la subasta. Una vez sobrepasada,
	 * se cerrara.
	 */
	private LocalDateTime fechaLimite;
	
	
//	CONSTRUCTORES
	/**
	 * Constructor que inicializa todos los parametros de la subasta.
	 * Ha de ser usado a traves de un usuario que cree la subasta en su
	 * respectivo metodo crearSubasta()
	 * @param propietario Usuario propietario de la subasta.
	 * @param descripcion Descripcion de la subasta.
	 * @param fechaLimite Fecha limite de la subasta.
	 * @since 31/01/2020
	 * @author Jose Manuel Gomez Martinez
	 */
	public Subasta(Usuario propietario, String descripcion, LocalDateTime fechaLimite) {
		PROPIETARIO = propietario;
		DESCRIPCION = descripcion;
		estado = EstadoSubasta.ABIERTA;
		fechaCreacion = LocalDateTime.now();
		this.fechaLimite = fechaLimite;
		
	}
	
	//IDEA... 
	/*
	 * Se podrian crear constructores que añadiese dias, horas o minutos,
	 * en vez de hacerlo totalmente con todo datetime a lo bestia.
	 */

//	METODOS
// METODOS PUBLICOS
	/**
	 * Metodo que realiza una puja en dicha subasta, siempre y cuando
	 * el pujador sea diferente al propietario, tenga suficiente credito,
	 * y sea mayor a la puja mayor (si existen pujas).
	 * Tambien ES IMPORTANTE RECALCAR que si la CANTIDAD = 0 se realizara
	 * una puja con un euro mas de la cantidad de la pujaMayor. Si no
	 * existiese dicha puja, el valor seria de 1.
	 * @param pujador Usuario que realizara la puja.
	 * @param cantidad Cantidad por la que pujara. Si cantidad = 0, pujara
	 * por 1 euro mas de la cantidad de la pujaMayor si existiese, en su 
	 * defecto, se realizara una puja de 1 euro.
	 * @see #realizarPujaConValor(Usuario, double)
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020 
	 */
	public void Pujar(Usuario pujador, double cantidad) {
		// Comprueba el estado de la subasta primero.
		if (estado==EstadoSubasta.ABIERTA) {
			// Comprueba si la cantidad pujada es mayor a 0
			if (cantidad > 0) {
				// Comprueba si el pujador y el propietario de la subasta son diferentes
				// usuarios.
				if (PROPIETARIO != pujador) {
					// Si el usuario tiene suficiente credito...
					if (pujador.getCredito() >= cantidad) {
						// Si aun nadie ha pujado...
						if (pujas.isEmpty()) {
							// Si ha pujado sin cantidad, se le asigna 1 euro al valor
							// de entrada.
							if (cantidad == 0) {
								realizarPujaConValor(pujador, 1);
							} else {
								// Se crea una nueva puja, asignada a la mayor y se 
								// guarda en el registro de la coleccion.
								realizarPujaConValor(pujador, cantidad);
							}
						} else {
							// Si ya existen pujas...
							// Si se ha pujado sin cantidad, se le asinga 1 euro mas
							// de la puja mayor.
							if (cantidad == 0) {
								// Si tiene suficiente credito...
								if (pujaMayor.getCANTIDAD()+1 <= pujador.getCredito()) {
									realizarPujaConValor(pujador, pujaMayor.getCANTIDAD()+1);
								} else {
									System.out.println("No tiene suficiente credito"
											+ " para realizar dicha puja.");
								}
							} else {
								// Comprueba si la cantidad es superior a la de la puja
								// mayor.
								if (cantidad > pujaMayor.getCANTIDAD()) {
									realizarPujaConValor(pujador, cantidad);
								} else {
									// Puja no aceptada.
									System.out.println("Puja no aceptada.");
								}
							}
						}
					} else {
						System.out.println("No tiene suficiente credito para realizar"
								+ " dicha puja.");
						// Se podria implementar en un futuro preguntarle si quiere 
						// introducir mas credito.
					}
				} else {
					System.out.println("No puedes pujar en su propia subasta.");
				}
			} else {
				System.out.println("No puedes pujar en negativo.");
			}
		} else {
			System.out.println("La subasta esta " + estado.name() + ". No puede"
					+ " pujar en ella.");
		}
	}
	
	/**
	 * Consulta e imprime por consola todas las pujas que ha recibido
	 * la subasta hasta el momento actual, mostrando en cada una de ellas
	 * todos sus datos (Pujador, cantidad y fecha).
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020
	 */
	public void consultarPujas() {
//		FORMAS ANTIGUAS DE HACERLO
//		// Se recorren las pujas por for
//		System.out.println("*** *** *** *** *** *** ***");
//		for (int i = 0; i < pujas.size(); i++) {
//			System.out.println("Puja numero " + i + ":");
//			System.out.println("Pujador: " + pujas.get(i).getUSUARIO().getNAME());
//			System.out.println("Cantidad: " + pujas.get(i).getUSUARIO().getCANTIDAD());
//			System.out.println("Fecha: " + pujas.get(i).getFECHA());
//		}
//		System.out.println("*** *** *** *** *** *** ***");
		
		// Se recorren por foreach
//		System.out.println("*** *** *** *** *** *** ***");
//		int contador = 0;
//		for (Puja puja : pujas) {
//			contador++;
//			System.out.println("Puja numero " + contador + ":");
//			System.out.println("Pujador: " + puja.getUSUARIO().getNAME());
//			System.out.println("Cantidad: " + puja.getCANTIDAD());
//			System.out.println("Fecha: " + puja.getFECHA());
//		}
//		System.out.println("*** *** *** *** *** *** ***");
		
		AtomicInteger i = new AtomicInteger(0);
		pujas.stream()
		.forEach(p -> System.out.println("Puja numero " + i.getAndIncrement() + ":"
				+ "/nPujador: " + p.getUSUARIO().getNAME()
				+ "/nCantidad: " + p.getCANTIDAD()
				+ "/nFecha: " + p.getFECHA()));
	}
	
	/**
	 * Cierra la subasta si ha pasado la fechaLimite, de lo contrario,
	 * avisara de que aun no se puede cerrar la subasta.
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020
	 */
	public void cerrarSubasta() {
		// Si ha pasado la fecha limite se cerrara la subasta
		if (LocalDateTime.now().isAfter(fechaLimite)) {
			estado = EstadoSubasta.CERRADA;
		} else {
			System.out.println("Aun no se puede cerrar la subasta.");
		}
	}
	
	/**
	 * Pregunta si la subasta esta o no cerrada.
	 * @return true = cerrada, false = no esta cerrada.
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020
	 */
	public boolean subastaCerrada() {
		if (estado==EstadoSubasta.CERRADA) {
			return true;
		} else {
			return false;
		}
	}
	
// METODOS PRIVADOS
	/**
	 * Metodo privado utilizado exclusivamente en pujar() para realizar
	 * definitivamente una puja con la cantidad dada. Su objetivo es la 
	 * reduccion de codigo repetido.
	 * @param pujador Usuario que realizara la puja.
	 * @param cantidad Cantidad por la que pujara.
	 * @see #Pujar(Usuario, double)
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020
	 */
	private void realizarPujaConValor(Usuario pujador, double cantidad) {
		pujaMayor = new Puja(this, cantidad, pujador);
		pujas.add(pujaMayor);
	}
	
//	SETTERS & GETTERS
	public List<Puja> getPujas() {
		return pujas;
	}
	
	public EstadoSubasta getEstado() {
		return estado;
	}


	public void setEstado(EstadoSubasta estado) {
		this.estado = estado;
	}

	public Puja getPujaMayor() {
		return pujaMayor;
	}

	public void setPujaMayor(Puja pujaMayor) {
		this.pujaMayor = pujaMayor;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDateTime fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Usuario getPROPIETARIO() {
		return PROPIETARIO;
	}

	public String getDESCRIPCION() {
		return DESCRIPCION;
	}
	
}
