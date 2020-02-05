package daos;

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
	 * Constructor que inicializa todos los atributos relacionales de la clase.
	 * @param usuarios UsuarioDAO donde se guardan los usuarios.
	 * @param subastas SubastaDAO donde se guardan las subastas.
	 * @param pujas PujaDAO donde se guardan las pujas.
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
