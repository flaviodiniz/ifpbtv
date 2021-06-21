package com.ifpb.ifpbtvapi.service;

//import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ads.ifpbtv.model.TV;
//import com.ads.ifpbtv.model.Usuario;
//import com.ads.ifpbtv.model.enums.DisponibilidadeTV;
//import com.ads.ifpbtv.model.enums.Perfil;


//@Service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.TV;
import com.ifpb.ifpbtvapi.model.Usuario;

@Service
public class DBService {
	
	//private BCryptPasswordEncoder criptografarSenha; //pra usar na senha antes de salvar ela no banco.
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private TVService tvService;
	
	
	public void instantiateTestDatabase() {
		
		//SALVANDO USUARIO (OK)
		
//		Usuario user1 = new Usuario(null, "TESTE 01", "teste01@gmail.com", "abcdef123", "123456789", true, Perfil.ADMINISTRADOR);
//		Usuario user2 = new Usuario(null, "TESTE 02", "teste02@gmail.com", "abcdef456", "987654321", true, Perfil.VISITANTE);
//		Usuario user3 = new Usuario(null, "TESTE 03", "teste03@gmail.com", "abcdef789", "001234567", true, Perfil.VISITANTE);
//		
//		userService.salvar(user1);
//		userService.salvar(user2);
//		userService.salvar(user3);
//		
//		
//		TV tv1 = new TV(null, "TV - Biblioteca", "Smart TV", "TCL", "123", DisponibilidadeTV.NAO, false);
//		TV tv2 = new TV(null, "TV - Bloco D", "Smart TV", "TCL", "456", DisponibilidadeTV.SIM, false);
//		TV tv3 = new TV(null, "TV - Bloco Administrativo", "Smart TV", "TCL", "789", DisponibilidadeTV.SIM, false);
//		
//		tvService.salvar(tv1);
//		tvService.salvar(tv2);
//		tvService.salvar(tv3);

		Usuario user1 = new Usuario(null, "TESTE 01", "teste01@gmail.com", "abcdef123", "123456789", "Ativo", "Administrador" );
		Usuario user2 = new Usuario(null, "TESTE 02", "teste02@gmail.com", "abcdef456", "987654321", "Inativo", "Administrador" );
		Usuario user3 = new Usuario(null, "TESTE 03", "teste03@gmail.com", "abcdef789", "001234567", "Ativo", "Comum");
		
		userService.salvar(user1);
		userService.salvar(user2);
		userService.salvar(user3);
		
		
		TV tv1 = new TV(null, "TV - Biblioteca", "Smart TV", "TCL", "123", "NAO", false);
		TV tv2 = new TV(null, "TV - Bloco D", "Smart TV", "TCL", "456", "SIM", false);
		TV tv3 = new TV(null, "TV - Bloco Administrativo", "Smart TV", "TCL", "789", "SIM", false);
		
		tvService.salvar(tv1);
		tvService.salvar(tv2);
		tvService.salvar(tv3);

		
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
