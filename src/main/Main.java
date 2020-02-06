package main;

import java.time.LocalDateTime;

import daos.PujaDAO;
import daos.SubastaDAO;
import daos.SuperDAO;
import daos.UsuarioDAO;
import pojos.Subasta;

public class Main {

	public static void main(String[] args) {

//		Instancio los DAOs necesarios.
		SuperDAO baseDeDatos = new SuperDAO();
		UsuarioDAO usuarios = baseDeDatos.getUsuarios();
		SubastaDAO subastas = baseDeDatos.getSubastas();
		PujaDAO pujas = baseDeDatos.getPujas();
//		Resto de variables
		Subasta subastaTest;
		
//		Creo los usuario Juan, Pedro y Enrique con el saldo inicial de
//		100, 150 y 300 euros, respectivamente.
		usuarios.añadirUsuario("Juan", 100);
		usuarios.añadirUsuario("Pedro", 150);
		usuarios.añadirUsuario("Enrique", 300);
		
//		Creo una subasta asociada al usuario Juan ofreciendo como producto
//		"Telefono Movil".
		subastaTest = usuarios.crearSubasta("Juan", "Telefono Movil", 
				LocalDateTime.now().plusSeconds(5));

//		El usuario Pedro puja por esa subasta 100 euros.
		subastas.Pujar(subastaTest, usuarios.getUsuarios().get("Pedro"), 100);
//		Muestro por consola la puja mayor de la subasta (producto, usuario y cantidad)
		System.out.println("Producto: " + subastaTest.getDESCRIPCION());
		System.out.println("Usuario: " + subastaTest.getPujaMayor().getUSUARIO());
		System.out.println("Cantidad: " + subastaTest.getPujaMayor().getCANTIDAD());
		
	}

}
