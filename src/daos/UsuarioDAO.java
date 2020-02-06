package daos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import pojos.Subasta;
import pojos.Usuario;

/**
 * Clase DAO de Usuario.
 * @author Jose Manuel Gomez Martinez
 * @since 05/02/2020
 */
public class UsuarioDAO {

	/**
	 * Coleccion que guarda todos los usuarios existentes
	 * en el programa o tomados de la base de datos "simulada".
	 */
	Map<String, Usuario> usuarios;
	/**
	 * SuperDAO donde se guarda la relacion de los tres.
	 */
	SuperDAO superDao;
	
//	CONSTRUCTORES
	/**
	 * Constructor que inicializa todos los atributos de la clase.
	 * @param superDao Clase que guarda la relacion de las tres.
	 * @author Jose Manuel Gomez Martinez
	 * @since 05/02/2020
	 */
	public UsuarioDAO(SuperDAO superDao) {
		usuarios = new HashMap<>();
		this.superDao=superDao;
	}
	
//	METODOS
//	METODOS REFERENTES A LA BASE DE DATOS
	/**
	 * Crea y añade un usuario con todos sus atributos.
	 * @param nombreKey Nombre-Key del usuario.
	 * @param creditoInicial Credito incial del usuario.
	 * @author Jose Manuel Gomez Martinez
	 * @since 06/02/2020
	 */
	public void añadirUsuario(String nombreKey, double creditoInicial) {
		usuarios.put(nombreKey, new Usuario(nombreKey, creditoInicial));
	}
	
	/**
	 * Elimina un usuario buscandolo por la key.
	 * (La key coincide con NAME)
	 * @param nombreKey Nombre-Key del usuario.
	 * @author Jose Manuel Gomez Martinez
	 * @since 06/02/2020
	 */
	public void eliminarUsuario(String nombreKey) {
		usuarios.remove(nombreKey);
	}
	
//	METODOS REFERENTES A UN USUARIO
	/**
	 * Incrementa el credito del usuario en la cantidad dada.
	 * @param key Clave primaria del usuario.
	 * @param cantidad Cantidad a sumar.
	 * @author Manuel Jimenez Jimenez
	 * @since 02/02/2020
	 */
	public void incrementarCredito(String key, double cantidad) {
		// Busco el usuario.
		double credito = usuarios.get(key).getCredito();
		// Incremento en la cantidad.
		credito += cantidad;
		// Guardo dicha cantidad.
		usuarios.get(key).setCredito(credito);
	}
	
	/**
	 * Decrementa el credito del usuario en la cantidad dada.
	 * @param key Clave primaria del usuario.
	 * @param cantidad Cantidad a restar.
	 * @author Manuel Jimenez Jimenez
	 * @since 02/02/2020
	 */
	public void decrementarCredito(String key, double cantidad) {
		// Busco el usuario.
		double credito = usuarios.get(key).getCredito();
		// Incremento en la cantidad.
		credito -= cantidad;
		// Guardo dicha cantidad.
		usuarios.get(key).setCredito(credito);
	}
	
	/**
	 * * Crea una subasta que estara relacionada con el usuario
	 * que la cree como PROPIETARIO.
	 * @param keyPropietario Clave del usuario propietario (NAME)..
	 * @param DESCRIPCION Descripcion de la subasta a crear.
	 * @param fechaLimite Fecha limite de la subasta (cuando se cerrara).
	 * @author Jose Manuel Gomez Martinez
	 * @since 04/02/2020
	 */
	public void crearSubasta(String keyPropietario, String DESCRIPCION, LocalDateTime fechaLimite) {
		// Busco el usuario
		Usuario propietario = usuarios.get(keyPropietario);
		// Creo y añado la subasta.
		superDao.getSubastas().añadirSubasta(propietario, DESCRIPCION, fechaLimite);
	}
	
	/**
	 * Imprime los datos de de las subastas ganadas si existiese alguna.
	 * Para que una subasta este ganada, dicha subasta no puede estar abierta,
	 * y ademas la pujaMayor tiene que coincidir con la puja.
	 * @author Jose Manuel Gomez Martinez
	 * @since 02/02/2020
	 */
	public void consultarSubastasGanadas() {
		// FORMA ANTIGUA DE HACERLO (Anterior al DAO)
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
	
//	SETTERS & GETTERS
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void setUsuarios(Map<String, Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public SuperDAO getSuperDao() {
		return superDao;
	}
	
	public void setSuperDao(SuperDAO superDao) {
		this.superDao = superDao;
	}
	
}
