package daos;

/**
 * Clase que contiene los 3 DAO del programa para relacionarlos entre sí,
 * y así poder acceder todos a los datos de todos.
 * @author Jose Manuel Gomez Martinez
 * @since 05/02/2020
 */
public class SuperDAO {

//	REFERENCIAS DE GRADO 3
	/**
	 * Referencia a UsuarioDAO
	 */
	private UsuarioDAO usuarios;
	/**
	 * Referencia a SubastaDAO
	 */
	private SubastaDAO subastas;
	/**
	 * Referencia a PujaDAO
	 */
	private PujaDAO pujas;
	
//	CONSTRUCTORES
	/**
	 * Constructor que inicializa por completo todos los DAO, es decir,
	 * que hara nuevos objetos y los asignara a los respectivos DAO,
	 * asignando a su vez SuperDAO a todos los DAO "hijos".
	 * @author Jose Manuel Gomez Martinez
	 * @since 05/02/2020
	 */
	public SuperDAO() {
		usuarios = new UsuarioDAO(this);
		subastas = new SubastaDAO(this);
		pujas = new PujaDAO(this);
	}
	
	/**
	 * Constructor que inicializa todos los atributos relacionales de la clase.
	 * @param usuarios UsuarioDAO donde se guardan los usuarios.
	 * @param subastas SubastaDAO donde se guardan las subastas.
	 * @param pujas PujaDAO donde se guardan las pujas.
	 * @author Jose Manuel Gomez Martinez
	 * @since 05/02/2020
	 */
	public SuperDAO(UsuarioDAO usuarios, SubastaDAO subastas, PujaDAO pujas) {
		this.usuarios=usuarios;
		this.subastas=subastas;
		this.pujas=pujas;
	}

//	METODOS
//	SETTERS & GETTERS
	public UsuarioDAO getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(UsuarioDAO usuarios) {
		this.usuarios = usuarios;
	}

	public SubastaDAO getSubastas() {
		return subastas;
	}

	public void setSubastas(SubastaDAO subastas) {
		this.subastas = subastas;
	}

	public PujaDAO getPujas() {
		return pujas;
	}

	public void setPujas(PujaDAO pujas) {
		this.pujas = pujas;
	}
	
}
