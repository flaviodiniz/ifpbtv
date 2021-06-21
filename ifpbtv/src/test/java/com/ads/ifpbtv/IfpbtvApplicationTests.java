package com.ads.ifpbtv;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ads.ifpbtv.exceptions.ObjectNotFoundException;
import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.model.enums.Perfil;
import com.ads.ifpbtv.service.TVService;
import com.ads.ifpbtv.service.UsuarioService;

@SpringBootTest
class IfpbtvApplicationTests {
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private TVService tvService;

	@Test
	void contextLoads() {
		
		//TESTE BUSCANDO USUARIO COM SUCESSO
		
//		Usuario user = new Usuario(Long.parseLong("1"), "TESTE 01", "teste01@gmail.com", "abcdef123", "123456789", true, Perfil.ADMINISTRADOR);
//		
//		assertEquals(userService.buscarPeloCodigo(Long.parseLong("1")), user);
		
		
		//------------------------------------------------------------------------------------------------------------------
		//Salvando um usuário com um atributo nulo.
		
//		try {
//			
//		} catch (DataIntegrityViolationException e) {
//			Usuario usuario = new Usuario(null, "LAu", "usuario@mail.com", null, "9876543210", false, Perfil.VISITANTE);
//			assertEquals(e, userService.salvar(usuario));
//		}
		
		
		
//		try {
//			
//		} catch (DataIntegrityViolationException e) {
//			TV tv = new TV(null, null, "Smart", "marcaaa", "key", DisponibilidadeTV.NAO, false);
//			assertEquals(e, tvService.salvar(tv));
//		}
		//---------------------------------------------------------------------------
		
		//TESTE BUSCANDO UM USUÁRIO QUE NÃO EXISTE
		
//		try {
//			
//		} catch (ObjectNotFoundException e) {
//			assertEquals(e, userService.buscarPeloCodigo(Long.parseLong("1000")));
//		}
		
		
//		try {
//			
//		} catch (ObjectNotFoundException e) {
//			assertEquals(e, userService.atualizar(null, user));
//		}
		
		//---------------------------------------------------------------------------
		
//		try {
//			
//		} catch (ObjectNotFoundException e) {
//			assertEquals(e, tvService.buscarPeloCodigo(Long.parseLong("1000")));
//		}
	}
}
