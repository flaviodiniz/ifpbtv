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
import com.ifpb.ifpbtvapi.model.TV;
import com.ifpb.ifpbtvapi.model.response.TVResponse;
import com.ifpb.ifpbtvapi.service.TVService;

@RestController
@RequestMapping("/tvs")
public class TVResource {
	
	@Autowired
	private TVService tvService;	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarTvPeloCodigo(@PathVariable Long id) {
		try {
			TV tv = tvService.buscarPeloCodigo(id);
			return ResponseEntity.ok().body(tv);
		} catch (ObjectNotFoundException e) {
			TVResponse tvResponse = new TVResponse();
			String trace = Arrays.toString(e.getStackTrace());
			tvResponse.setStatus(false);
			tvResponse.setMensagem("Nenhuma TV foi encontrada! ID: " + id);
			tvResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(tvResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<TVResponse> salvar(@RequestBody TV tv) {
		return tvService.salvar(tv);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/all/{local}")
	public ResponseEntity<List<TV>> getTvs(@PathVariable String local) {
		List<TV> tvs = tvService.getTvs(local);
		return ResponseEntity.ok().body(tvs);
	}	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<TVResponse> atualizar(@PathVariable Long id, @RequestBody TV tv) {
		return tvService.atualizar(id, tv);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<TV>> listarTVs() {	
		List<TV> tvs = tvService.listarTVs();	
		return ResponseEntity.ok().body(tvs);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/marcas")
	public ResponseEntity<List<String>> listarMarcas() {
		List<String> marcas = tvService.listarMarcas();
		return ResponseEntity.ok().body(marcas);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> excluir(@PathVariable Long id) {
		try {
			tvService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			TVResponse tvResponse = new TVResponse();
			String trace = Arrays.toString(e.getStackTrace());
			tvResponse.setStatus(false);
			tvResponse.setMensagem("Erro ao remover TV! ID: " + id + " inexistente.");
			tvResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
