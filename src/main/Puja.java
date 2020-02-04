package main;

import java.time.LocalDateTime;

/**
 * Clase de Puja
 * 
 * @author Jose Manuel Gomez Martinez
 * @since 31/01/2020
 */
public class Puja {

	/**
	 * Subasta a la que pertenece la puja.
	 */
	private final Subasta SUBASTA;
	/**
	 * Cantidad por la que se ha pujado en dihca puja.
	 */
	private final double CANTIDAD;
	/**
	 * Usuario a la que pertenece dicha Puja.
	 */
	private final Usuario USUARIO;
	/**
	 * Fecha (momento exacto) en el que se realizo la Puja.
	 */
	private final LocalDateTime FECHA;
	
//	CONSTRUCTORES
	/**
	 * Constructor que inicializa todos los atributos de la clase.
	 * @param subasta Subasta a la que pertenece.
	 * @param cantidad Cantidad Pujada.
	 * @param usuario Usuario autor de la Puja.
	 * @author Jose Manuel Gomez Martinez
	 * @since 31/01/2020
	 */
	public Puja(Subasta subasta, double cantidad, Usuario usuario) {
		SUBASTA = subasta;
		CANTIDAD = 	cantidad;
		USUARIO = usuario;
		FECHA = LocalDateTime.now();
	}
	
//	METODOS
//	SETTERS & GETTERS
	public Subasta getSUBASTA() {
		return SUBASTA;
	}

	public double getCANTIDAD() {
		return CANTIDAD;
	}

	public Usuario getUSUARIO() {
		return USUARIO;
	}

	public LocalDateTime getFECHA() {
		return FECHA;
	}
	
}
