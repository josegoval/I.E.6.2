package daos;

import java.util.ArrayList;
import java.util.List;

import pojos.Puja;
import pojos.Subasta;
import pojos.Usuario;

/**
 * Clase DAO de Puja.
 * @author Jose Manuel Gomez Martinez
 * @since 05/02/2020
 */
public class PujaDAO {
	/**
	 * Lista de todas las pujas existentes en el programa.
	 */
	private List<Puja> pujas;
	/**
	 * SuperDAO donde se guarda la relacion de los tres.
	 */
	private SuperDAO superDao;
	
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
//	METODOS REFERENTES A LA BASE DE DATOS
	/**
	 * Crea y añade una puja con todos sus atributos.
	 * @param subasta Subasta a la que pertenece la puja.
	 * @param cantidad Cantidad a pujar.
	 * @param usuario Usuario que realizara dicha puja.
	 * @return (Puja) Devuelve la puja creada para poder referenciarla facilmente.
	 */
	public Puja añadirPuja(Subasta subasta, double cantidad, Usuario usuario) {
		Puja pujaNueva = new Puja(subasta, cantidad, usuario);
		pujas.add(pujaNueva);
		return pujaNueva;
	}  
	
//	METODOS REFERENTES A CADA SUBASTA
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
