package com.ifpb.ifpbtvapi.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.Chaves;
import com.ifpb.ifpbtvapi.model.Midia;
import com.ifpb.ifpbtvapi.model.response.MidiaResponse;
import com.ifpb.ifpbtvapi.model.response.UsuarioResponse;
import com.ifpb.ifpbtvapi.service.MidiaService;

@RestController
@RequestMapping("/midias")
public class MidiaResource {
	
	@Autowired
	private MidiaService midiaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPeloCodigo(@PathVariable Long id) {
		try {	
			Midia user = midiaService.buscarPeloCodigo(id);	
			return ResponseEntity.ok().body(user);
		} catch (ObjectNotFoundException e) {
			UsuarioResponse usuarioResponse = new UsuarioResponse();
			String trace = Arrays.toString(e.getStackTrace());
			usuarioResponse.setStatus(false);
			usuarioResponse.setMensagem("Nenhuma mídia foi encontrada! ID: " + id);
			usuarioResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(usuarioResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<MidiaResponse> salvar(@RequestBody Midia midia) {	
		return midiaService.salvar(midia);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{titulo}/{chave}/{tipo}")
	public ResponseEntity<List<Midia>> getUsuarios(@PathVariable String titulo, @PathVariable String chave, @PathVariable String tipo) {
		List<Midia> midias = midiaService.getMidias(titulo, chave, tipo);
		return ResponseEntity.ok().body(midias);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MidiaResponse> atualizar(@RequestBody Midia midia, @PathVariable Long id) {
		return midiaService.atualizar(midia, id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Midia>> listarTodos() {
		List<Midia> midias = midiaService.listarMidias();
		return ResponseEntity.ok().body(midias);
	} 
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getTiposMidia")
	public ResponseEntity<List<String>> getTiposMidia() {
		List<String> usuarios = midiaService.getTiposMidia();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getDisponibilidadeMidia")
	public ResponseEntity<List<String>> getDisponibilidadeMidia() {
		List<String> usuarios = midiaService.getDisponibilidadeMidia();
		return ResponseEntity.ok().body(usuarios);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getChaves")
	public ResponseEntity<List<Chaves>> getChaves() {
		List<Chaves> usuarios = midiaService.getChaves();
		return ResponseEntity.ok().body(usuarios);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		try {
			midiaService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			MidiaResponse midiaResponse = new MidiaResponse();
			String trace = Arrays.toString(e.getStackTrace());
			midiaResponse.setStatus(false);
			midiaResponse.setMensagem("Erro ao remover mídia! ID: " + id + " inexistente.");
			midiaResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(midiaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
