package com.ads.ifpbtv.resource;

import java.util.Arrays;
import java.util.List;

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
import com.ads.ifpbtv.model.Midia;
import com.ads.ifpbtv.model.response.MidiaResponse;
import com.ads.ifpbtv.model.response.UsuarioResponse;
import com.ads.ifpbtv.service.MidiaService;

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
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<MidiaResponse> salvar(@RequestBody Midia midia) {	
		return midiaService.salvar(midia);
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
	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@GetMapping("/perfis")
//	public ResponseEntity<List<String>> listarPerfis() {
//		List<String> midias = midiaService.listarPerfis();
//		return ResponseEntity.ok().body(midias);
//	}
	
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
