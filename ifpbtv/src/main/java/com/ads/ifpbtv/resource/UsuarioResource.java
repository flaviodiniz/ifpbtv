package com.ads.ifpbtv.resource;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.ifpbtv.exceptions.ObjectNotFoundException;
import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.model.response.UsuarioResponse;
import com.ads.ifpbtv.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPeloCodigo(@PathVariable Long id) {
		
		try {
			
			Usuario user = usuarioService.buscarPeloCodigo(id);
			
			return ResponseEntity.ok().body(user);
			
		} catch (ObjectNotFoundException e) {
			
			UsuarioResponse usuarioResponse = new UsuarioResponse();
			
			String trace = Arrays.toString(e.getStackTrace());
			
			usuarioResponse.setStatus(false);
			usuarioResponse.setMensagem("Nenhum usuário foi encontrado! ID: " + id);
			usuarioResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(usuarioResponse, HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PostMapping
	public ResponseEntity<UsuarioResponse> salvar(@Valid @RequestBody Usuario usuario) {
		
		return usuarioService.salvar(usuario);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		
		return usuarioService.atualizar(id, usuario);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> listarTodos() {
		
		List<Usuario> usuarios = usuarioService.listarUsuarios();
		
		return ResponseEntity.ok().body(usuarios);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		
		try {
			
			usuarioService.excluir(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EmptyResultDataAccessException e) {
			
			UsuarioResponse usuarioResponse = new UsuarioResponse();
			
			String trace = Arrays.toString(e.getStackTrace());
			
			usuarioResponse.setStatus(false);
			usuarioResponse.setMensagem("Erro ao remover usuário! ID: " + id + " inexistente.");
			usuarioResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
