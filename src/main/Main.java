package main;

import daos.UsuarioDAO;
import pojos.Usuario;

public class Main {

	public static void main(String[] args) {

		UsuarioDAO usuarios = new UsuarioDAO(null);
		
		usuarios.getUsuarios().put("Manolo", new Usuario("Manolo", 0));
		
		
		
	}

}
