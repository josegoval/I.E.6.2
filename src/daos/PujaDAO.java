package daos;

import java.util.ArrayList;
import java.util.List;

import pojos.Puja;

/**
 * Clase DAO de Puja.
 * @author Jose Manuel Gomez Martinez
 * @since 05/02/2020
 */
public class PujaDAO {
	/**
	 * Lista de todas las pujas existentes en el programa.
	 */
	List<Puja> pujas;
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
	public PujaDAO(SuperDAO superDAO) {
		pujas = new ArrayList<Puja>();
		this.superDao = superDAO;
	}

//	METODOS
//	SETTERS & GETTERS
	public List<Puja> getPujas() {
		return pujas;
	}
	
	public void setPujas(List<Puja> pujas) {
		this.pujas = pujas;
	}
	
	public SuperDAO getSuperDao() {
		return superDao;
	}
	
	public void setSuperDao(SuperDAO superDao) {
		this.superDao = superDao;
	}
	
}
