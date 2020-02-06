package main;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import daos.PujaDAO;
import daos.SubastaDAO;
import daos.SuperDAO;
import daos.UsuarioDAO;
import pojos.Subasta;

public class Main {

	public static void main(String[] args) throws InterruptedException {

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
		subastas.mostrarPujaMayor(subastaTest);
		
		separador();
//		El usuario Enrique puja por esa subasta 50 euros.
		subastas.Pujar(subastaTest, usuarios.getUsuarios().get("Enrique"), 50);
		separador();
		
//		Muestro por consola la puja mayor, y se comprueba que la puja de Enrique no 
//		ha sido aceptada, ya que es menor que la primera.
		subastas.mostrarPujaMayor(subastaTest);
		separador();
		subastas.consultarPujas(subastaTest);
		
//		Intento cerrar la subasta antes de tiempo pero no me deja porque aun no ha pasado
//		el tiempo suficiente.
		separador();
		subastas.cerrarSubasta(subastaTest);
//		Esperamos 6 segundos para poder cerrar la subasta.
		Thread.sleep(6000);
//		Se cierra la subasta exitosamente.
		separador();
		subastas.cerrarSubasta(subastaTest);
		
//		El usuario Enrique puja de nuevo por esa subasta con 200 euros.
		separador();
		subastas.Pujar(subastaTest, usuarios.getUsuarios().get("Enrique"), 200);
//		Comprobamos que la puja no ha sido aceptada ya que la subasta ha sido cerrada.
		separador();
		subastas.consultarPujas(subastaTest);
		
//		Ejecuto la subasta.
		subastas.ejecutarSubasta(subastaTest);
//		Muestra los creditos de los tres usuarios, observando que los creditos de
//		Juan y Pedro han cambiado.
		separador();
		System.out.println("Credito de Juan: " 
				+ usuarios.getUsuarios().get("Juan").getCredito());
		System.out.println("Credito de Pedro: " 
				+ usuarios.getUsuarios().get("Pedro").getCredito());
		System.out.println("Credito de Enrique: " 
				+ usuarios.getUsuarios().get("Enrique").getCredito());
		
//		Para cada usuario muestro por consola las subastas de las que son propietarios,
//		subastas ganadoras, y pujas realizadas.
		// Juan
		separador();
		usuarios.consultarMisSubastas("Juan");
		usuarios.consultarSubastasGanadas("Juan");
		usuarios.consultarPujas("Juan");
		// Pedro
		separador();
		usuarios.consultarMisSubastas("Pedro");
		usuarios.consultarSubastasGanadas("Pedro");
		usuarios.consultarPujas("Pedro");
		// Enrique
		separador();
		usuarios.consultarMisSubastas("Enrique");
		usuarios.consultarSubastasGanadas("Enrique");
		usuarios.consultarPujas("Enrique");
		
	}
	
	/**
	 * Separa las impresiones para poder diferenciar mas rapidamente los resultados.
	 */
	public static void separador() {
		System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + +");
	}
}
