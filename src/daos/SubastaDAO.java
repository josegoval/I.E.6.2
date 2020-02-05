package daos;

import java.util.ArrayList;
import java.util.List;

import pojos.Subasta;

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
