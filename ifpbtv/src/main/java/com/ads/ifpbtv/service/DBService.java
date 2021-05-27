package com.ads.ifpbtv.service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.ads.ifpbtv.model.Usuario;

@Service
public class DBService {
	
	@Autowired
	//private UsuarioService userService;
	
	
	public void instantiateTestDatabase() {
		
		//SALVANDO USUARIO (OK)
		
//		Usuario user1 = new Usuario(null, "TESTE 01", "teste01@gmail.com", "123", "123456789", true, Perfil.ADMINISTRADOR);
//		Usuario user2 = new Usuario(null, "TESTE 02", "teste02@gmail.com", "456", "987654321", true, Perfil.VISITANTE);
//		Usuario user3 = new Usuario(null, "TESTE 03", "teste03@gmail.com", "789", "001234567", true, Perfil.VISITANTE);
//		
//		userService.salvar(user1);
//		userService.salvar(user2);
//		userService.salvar(user3);
		
//		Usuario user4 = new Usuario(null, "TESTEE 04", "teste04@gmail.com", "007", "00123456789", true, Perfil.VISITANTE);
//		
//		userService.salvar(user4);
		
		//ATUALIZAR USUARIO (OK)
		
//		Usuario user = new Usuario();
//		
//		user.setNome("TESTE 03");
//		user.setEmail("teste03@gmail.com");
//		user.setSenha("789");
//		user.setMatricula("001234567");
//		user.setAtivo(true);
//		user.setPerfil(Perfil.VISITANTE);
//		
//		userService.atualizar(Long.valueOf(5), user);
//		System.out.println("Usuário atualizado!");
		
		
		//BUSCANDO USUARIO (OK)
		
//		Usuario usuario = userService.buscarPeloCodigo(Long.valueOf(1));
//		
//		System.out.println("Nome: " + usuario.getNome()+"\n"
//						+ " Matricula: " + usuario.getMatricula()+"\n"
//						+ " E-mail: " + usuario.getEmail()+"\n"
//						+ " Ativo: " + usuario.isAtivo()+"\n"
//						+ " Perfil: " + usuario.getPerfil());
		
		//LISTANDO OS USUARIOS (OK)
		
//		List<Usuario> users = userService.listarTodos();
//		
//		for (Usuario usuario : users) {
//			System.out.println("Nome: " + usuario.getNome()+"\n"
//					+ " Matricula: " + usuario.getMatricula()+"\n"
//					+ " E-mail: " + usuario.getEmail()+"\n"
//					+ " Ativo: " + usuario.isAtivo()+"\n"
//					+ " Perfil: " + usuario.getPerfil()+"\n\n");
//		}
		
		
		//DELETANDO UM USUARIO (OK)
		
//		userService.remove(Long.valueOf(7));
//		System.out.println("Usuário removido!");
	}
}
