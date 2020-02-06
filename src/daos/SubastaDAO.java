package daos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import pojos.EstadoSubasta;
import pojos.Puja;
import pojos.Subasta;
import pojos.Usuario;

/**
 * Clase DAO de Subasta.
 * @author Jose Manuel Gomez Martinez
 * @since 05/02/2020
 */
public class SubastaDAO {

	/**
	 * Lista que guarda todas las subastas existentes en
	 * el programa.
	 */
	List<Subasta> subastas;
	/**
	 * SuperDAO donde se guarda la relacion de los tres.
	 */
	SuperDAO superDao;
	
//	CONSTRUCTORES
	/**
	 * Constructor que inicializa todos los atributos de la clase.
	 * @param superDAO Clase que guarda la relacion de los tres.
	 * @author Jose Manuel Gomez Martinez
	 * @since 05/02/2020
	 */
	public SubastaDAO(SuperDAO superDAO) {
		subastas = new ArrayList<>();
		this.superDao = superDAO;
	}

//	METODOS
//	METODOS REFERENTES A LA BASE DE DATOS
	/**
	 * Crea y a�ade una subasta con todos sus atributos.
	 * @param PROPIETARIO Usuario creador de la puja.
	 * @param DESCRIPCION Descripcion asignada a la puja.
	 * @param fechaLimite Fecha limite de la subasta (cuando se cerrara).
	 * @author Jose Manuel Gomez Martinez
	 * @since 06/02/2020
	 */
	public void a�adirSubasta(Usuario PROPIETARIO, String DESCRIPCION, 
			LocalDateTime fechaLimite) {
		subastas.add(new Subasta(PROPIETARIO, DESCRIPCION, fechaLimite));
	}
	
//	METODOS REFERENTES A CADA SUBASTA
 //	METODOS PUBLICOS
	/**
	 * Metodo que realiza una puja en dicha subasta, siempre y cuando
	 * el pujador sea diferente al propietario, tenga suficiente credito,
	 * y sea mayor a la puja mayor (si existen pujas).
	 * Tambien ES IMPORTANTE RECALCAR que si la CANTIDAD = 0 se realizara
	 * una puja con un euro mas de la cantidad de la pujaMayor. Si no
	 * existiese dicha puja, el valor seria de 1.
	 * @param subasta Subasta donde se realizara la puja.
	 * @param pujador Usuario que realizara la puja.
	 * @param cantidad Cantidad por la que pujara. Si cantidad = 0, pujara
	 * por 1 euro mas de la cantidad de la pujaMayor si existiese, en su 
	 * defecto, se realizara una puja de 1 euro.
	 * @see #realizarPujaConValor(Subasta, Usuario, double)
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020 
	 */
	public void Pujar(Subasta subasta, Usuario pujador, double cantidad) {
		// Comprueba el estado de la subasta primero.
		if (subasta.getEstado()==EstadoSubasta.ABIERTA) {
			// Comprueba si la cantidad pujada es mayor a 0
			if (cantidad > 0) {
				// Comprueba si el pujador y el propietario de la subasta son diferentes
				// usuarios.
				if (subasta.getPROPIETARIO() != pujador) {
					// Si el usuario tiene suficiente credito...
					if (pujador.getCredito() >= cantidad) {
						// Si aun nadie ha pujado...
						if (subasta.getPujaMayor()==null) {
							// Si ha pujado sin cantidad, se le asigna 1 euro al valor
							// de entrada.
							if (cantidad == 0) {
								realizarPujaConValor(subasta, pujador, 1);
							} else {
								// Se crea una nueva puja, asignada a la mayor y se 
								// guarda en el registro de la coleccion.
								realizarPujaConValor(subasta, pujador, cantidad);
							}
						} else {
							// Si ya existen pujas...
							// Si se ha pujado sin cantidad, se le asinga 1 euro mas
							// de la puja mayor.
							if (cantidad == 0) {
								// Si tiene suficiente credito...
								if (subasta.getPujaMayor().getCANTIDAD()+1 <= pujador.getCredito()) {
									realizarPujaConValor(subasta, pujador, subasta.getPujaMayor().getCANTIDAD()+1);
								} else {
									System.out.println("No tiene suficiente credito"
											+ " para realizar dicha puja.");
								}
							} else {
								// Comprueba si la cantidad es superior a la de la puja
								// mayor.
								if (cantidad > subasta.getPujaMayor().getCANTIDAD()) {
									realizarPujaConValor(subasta, pujador, cantidad);
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
			System.out.println("La subasta esta " + subasta.getEstado().name() + ". No puede"
					+ " pujar en ella.");
		}
	}
	
	/**
	 * Consulta e imprime por consola todas las pujas que ha recibido
	 * la subasta hasta el momento actual, mostrando en cada una de ellas
	 * todos sus datos (Pujador, cantidad y fecha).
	 * @param subasta Subasta sobre la que consultar.
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020
	 */
	public void consultarPujas(Subasta subasta) {
//		FORMAS ANTIGUAS DE HACERLO (Anterior al DAO)
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
		// Busco las pujas de esa subasta
		List<Puja> busqueda = superDao.getPujas().getPujas().stream()
		.filter(puja -> puja.getSUBASTA()==subasta)
		.collect(Collectors.toList());
		
		// Imrpimo los resultados de la busqueda
		if (busqueda.isEmpty()) {
			System.out.println("Esta subasta aun no tiene pujas.");
		} else {
			busqueda.stream()
			.forEach(p -> System.out.println("Puja numero " + i.incrementAndGet() + ":"
				+ "\nPujador: " + p.getUSUARIO().getNAME()
				+ "\nCantidad: " + p.getCANTIDAD()
				+ "\nFecha: " + p.getFECHA()));
		}
		
	}
	
	
 //	METODOS PRIVADOS
	/**
	 * Metodo privado utilizado exclusivamente en pujar() para realizar
	 * definitivamente una puja con la cantidad dada y guardar dicha
	 * puja, en la coleccion de PujaDAO. Su objetivo es la 
	 * reduccion de codigo repetido.
	 * @param subasta Subasta donde se realizara la puja.
	 * @param pujador Usuario que realizara la puja.
	 * @param cantidad Cantidad por la que pujara.
	 * @see #Pujar(Subasta, Usuario, double)
	 * @author Jose Manuel Gomez Martinez
	 * @since 01/02/2020
	 */
	private void realizarPujaConValor(Subasta subasta, Usuario pujador, double cantidad) {
		// Se crea y a�ade la nueva puja, y se asigna a pujaMayor de la subasta.
		subasta.setPujaMayor(superDao.getPujas().a�adirPuja(subasta, cantidad, pujador));
	}
	
//	SETTERS & GETTERS
	public List<Subasta> getSubastas() {
		return subastas;
	}
	
	public void setSubastas(List<Subasta> subastas) {
		this.subastas = subastas;
	}
	
	public SuperDAO getSuperDAO() {
		return superDao;
	}
	
	public void setSuperDAO(SuperDAO superDAO) {
		this.superDao = superDAO;
	}
	
}
