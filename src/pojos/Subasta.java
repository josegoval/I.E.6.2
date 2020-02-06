package pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
//	SETTERS & GETTERS
	/**
	 * @return Lista de pujas de la subasta.
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
