package daos;

import java.util.HashMap;
import java.util.Map;

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
