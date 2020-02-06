package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import daos.SuperDAO;
import daos.UsuarioDAO;
import pojos.Puja;
import pojos.Usuario;

public class Main {

	public static void main(String[] args) {

		SuperDAO baseDeDatos = new SuperDAO();
		
		baseDeDatos.getUsuarios().crearSubasta("Kanachan", "ninguna", LocalDateTime.now());
		baseDeDatos.getUsuarios().consultarSubastasGanadas("Kanachan");
		baseDeDatos.getUsuarios().consultarMisSubastas("Kanachan");
		
		Usuario manolo = baseDeDatos.getUsuarios().getUsuarios().get("Manolo");
//		List<Puja> aaa = new ArrayList<Puja>();
//		
//		aaa.stream().forEach(System.out.println("ahahhaa"));
		
	}

}
