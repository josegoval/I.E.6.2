package daos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import pojos.EstadoSubasta;
import pojos.Puja;
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
	 * Crea una subasta que estara relacionada con el usuario
	 * que la cree como PROPIETARIO.
	 * @param keyPropietario Clave del usuario propietario (NAME).
	 * @param DESCRIPCION Descripcion de la subasta a crear.
	 * @param fechaLimite Fecha limite de la subasta (cuando se cerrara).
	 * @return (Subasta) Devuelve la subasta creada para poder referenciarla
	 * facilmente.
	 * @author Manuel Jimenez Jimenez
	 * @since 04/02/2020
	 */
	public Subasta crearSubasta(String keyPropietario, String DESCRIPCION, 
			LocalDateTime fechaLimite) {
		Subasta subastaCreada;
		// Busco el usuario
		Usuario propietario = usuarios.get(keyPropietario);
		// Creo y añado la subasta.
		return superDao.getSubastas().añadirSubasta(propietario, DESCRIPCION, fechaLimite);
	}
	
	/**
	 * Imprime los datos de de las subastas ganadas si existiese alguna.
	 * Para que una subasta este ganada, dicha subasta no puede estar abierta,
	 * y ademas la pujaMayor tiene que haber estado realizada con el usuario.
	 * @author Jose Manuel Gomez Martinez
	 * @since 02/02/2020
	 */
	public void consultarSubastasGanadas(String usuarioKey) {
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
		
		// Busco el usuario sobre el que analizar
		Usuario usuario = usuarios.get(usuarioKey);
		
		
		// Busca todas las subastas primero y las paso a Stream
		List<Subasta> busqueda = superDao.getSubastas().getSubastas().stream()
		// Busca las subastas que no esten en el estado ABIERTA
		.filter(subasta -> subasta.getEstado() != EstadoSubasta.ABIERTA)
		// Busca aquellas que tienen una pujaMayor asignada
		.filter(subasta -> subasta.getPujaMayor() != null)
		// Comprueba que los dos usuarios sean el mismo, es decir, que sea el ganador
		// de la pujaMayor, lo que implica que gano la subasta.
		.filter(subasta -> subasta.getPujaMayor().getUSUARIO() == usuario)
		.collect(Collectors.toList());
		
		// Imprime los resultados de la busqueda
		System.out.println("Subastas ganadas del usuario " + usuarioKey);
		if (busqueda.isEmpty()) {
			System.out.println("No has ganado ninguna subasta aun.");
		} else {
			busqueda.forEach(s -> System.out.println("*******"
				+ "\nSubasta de " + s.getDESCRIPCION()
				+ "\nCreada por " + s.getPROPIETARIO().getNAME() + " en " 
				+ s.getFechaCreacion()
				+ " y finalizada en " + s.getFechaLimite()
				+ "\nGanada con " + s.getPujaMayor().getCANTIDAD() + " euros."));
		}
		
	}	
	
	/**
	 * Metodo que devuelve la informacion de las subastas creadas
	 * por el usuario, si ha creado previamente alguna. En caso contrario
	 * se le avisara de que no ha creado ninguna aun.
	 * @author Jose Manuel Gomez Martinez
	 * @since 04/02/2020
	 */
	public void consultarMisSubastas(String usuarioKey) {
		// FORMA ANTIGUA DE HACERLO (Anterior al DAO)
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
		
		// Busco el usuario sobre el que analizar
		Usuario usuario = usuarios.get(usuarioKey);
		
		// Busca primero todas las subastas y las pasa a stream
		List<Subasta> busqueda = superDao.getSubastas().getSubastas().stream()
		// Busca las subastas que pertenezcan al usuario
		.filter(subasta -> subasta.getPROPIETARIO() == usuario)
		.collect(Collectors.toList());
		// Imprime los resultados de la busqueda.
		System.out.println("Subastas del usuario " + usuarioKey);
		if (busqueda.isEmpty()) {
			System.out.println("Aun no has creado ninguna subasta.");
		} else {
			busqueda.stream()
			.forEach(s -> {
				System.out.println("********");
				System.out.println("Subasta: " + s.getDESCRIPCION());
				System.out.println("Fecha de creacion: " + s.getFechaCreacion());
				System.out.println("Fecha de cierre: " + s.getFechaLimite());
				if (s.getPujaMayor()==null) {
					System.out.println("Puja Mayor: Sin Puja Mayor.");
				} else {
					System.out.println("Puja Mayor: " + s.getPujaMayor().getCANTIDAD() + "€");
				}
			}); 
		}
		
	}
	
	/**
	 * Consulta e imprime la informacion de las pujas realizadas por el usuario.
	 * @param usuarioKey ClavePrimaria-NAME del usuario al que consultar.
	 * @author Jose Manuel Gomez Martinez
	 * @since 06/02/2020
	 */
	public void consultarPujas(String usuarioKey) {
		Usuario usuario = usuarios.get(usuarioKey);
		// Busca las pujas existentes
		List<Puja> busqueda = superDao.getPujas().getPujas().stream()
		// Busca las pujas del usuario
		.filter(puja -> puja.getUSUARIO() == usuario)
		.collect(Collectors.toList());
		// Imprimo los resultados de la busqueda
		System.out.println("Pujas del usuario: " + usuarioKey);
		if (busqueda.isEmpty()) {
			System.out.println("No has realizado ninguna puja aun.");
		} else {
			AtomicInteger contador = new AtomicInteger(0);
			busqueda.stream()
			.forEach(puja -> {
				System.out.println("*** PUJA " + contador.incrementAndGet() + " ***");
				System.out.println("Subasta en la que se realizo: " + puja.getSUBASTA().getDESCRIPCION());
				System.out.println("Creador de la subasta: " + puja.getSUBASTA().getPROPIETARIO().getNAME());
				System.out.println("Fecha de la puja: " + puja.getFECHA());
				System.out.println("Cantidad de la puja: " + puja.getCANTIDAD() + "€");
			});
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
