package com.ads.ifpbtv.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/{id}")
	public Usuario buscarPeloCodigo(@PathVariable Long id) {
		return usuarioService.buscarPeloCodigo(id);
	}
	
	
	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		return usuarioService.atualizar(id, usuario);
	}
	
	
	@GetMapping("/all")
	public List<Usuario> listarTodos() {
		return usuarioService.listarTodos();
	}
	
	
	@PostMapping
	public Usuario salvar(@RequestBody Usuario usuario) {
		return usuarioService.salvar(usuario);
	}
	
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		usuarioService.remove(id);
	}
}
