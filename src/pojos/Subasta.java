package pojos;

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
	 * @deprecated Con el sistema DAO ya no tiene sentido.
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
	 * respectivo metodo crearSubasta().
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
	
//	METODOS
// METODOS PUBLICOS
	
	
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
				+ "\nPujador: " + p.getUSUARIO().getNAME()
				+ "\nCantidad: " + p.getCANTIDAD()
				+ "\nFecha: " + p.getFECHA()));
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
	
	
	
//	SETTERS & GETTERS
	/**
	 * @deprecated
	 */
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

	public LocalDateTime getFechaLimite() {
		return fechaLimite;
	}

	public Usuario getPROPIETARIO() {
		return PROPIETARIO;
	}

	public String getDESCRIPCION() {
		return DESCRIPCION;
	}
	
}
