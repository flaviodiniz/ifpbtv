package com.ads.ifpbtv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.model.Perfil;
import com.ads.ifpbtv.model.Usuario;

@Service
public class DBService {
	
	@Autowired
	private UsuarioService userService;
	
	
	public void instantiateTestDatabase() {
		
		Usuario user1 = new Usuario(null, "TESTE 01", "teste01@gmail.com", "123", "123456789", true, Perfil.ADMINISTRADOR);
		Usuario user2 = new Usuario(null, "TESTE 02", "teste02@gmail.com", "456", "987654321", true, Perfil.VISITANTE);
		
		userService.salvar(user1);
		userService.salvar(user2);
	}
}
