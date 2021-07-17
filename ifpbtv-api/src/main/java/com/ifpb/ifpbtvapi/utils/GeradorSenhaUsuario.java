package com.ifpb.ifpbtvapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ifpb.ifpbtvapi.model.Usuario;
import com.ifpb.ifpbtvapi.repository.UsuarioRepository;

public class GeradorSenhaUsuario {
	
	@Autowired
	private static UsuarioRepository usuRep;
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senha = encoder.encode("12345678");
		System.out.println(senha);
		
		Usuario usu = new Usuario();
		usu.setNome("Tiago");
		usu.setEmail("tiago01@gmail.com");
		usu.setId((long) 1);
		usu.setMatricula("201525020323");
		usu.setPerfil("Administrador");
		usu.setPerfil("Ativo");
		usu.setSenha(senha);
		
		usuRep.save(usu);
		
	}

}
