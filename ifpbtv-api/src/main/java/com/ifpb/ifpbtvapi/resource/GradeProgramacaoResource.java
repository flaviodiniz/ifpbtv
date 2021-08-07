package com.ifpb.ifpbtvapi.resource;

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

import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.GradeProgramacao;
import com.ifpb.ifpbtvapi.model.response.ProgramacaoResponse;
import com.ifpb.ifpbtvapi.service.GradeProgramacaoService;

@RestController
@RequestMapping("/grade-programacao")
public class GradeProgramacaoResource {
	
	@Autowired
	private GradeProgramacaoService gradeProgramacaoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPeloCodigo(@PathVariable Long id) {
		try {	
			GradeProgramacao gradeProgramacao = gradeProgramacaoService.buscarPeloCodigo(id);	
			return ResponseEntity.ok().body(gradeProgramacao);
		} catch (ObjectNotFoundException e) {
			ProgramacaoResponse programacaoResponse = new ProgramacaoResponse();
			String trace = Arrays.toString(e.getStackTrace());
			programacaoResponse.setStatus(false);
			programacaoResponse.setMensagem("Nenhuma Playlist foi encontrada! ID: " + id);
			programacaoResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(programacaoResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<ProgramacaoResponse> salvar(@RequestBody GradeProgramacao gradeProgramacao) {	
		return gradeProgramacaoService.salvar(gradeProgramacao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProgramacaoResponse> atualizar(@RequestBody GradeProgramacao gradeProgramacao, @PathVariable Long id) {
		return gradeProgramacaoService.atualizar(gradeProgramacao, id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<GradeProgramacao>> listarTodos() {
		List<GradeProgramacao> programacoes = gradeProgramacaoService.listarProgramacoes();
		return ResponseEntity.ok().body(programacoes);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		try {
			gradeProgramacaoService.deletarProgramacao(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			ProgramacaoResponse programacaoResponse = new ProgramacaoResponse();
			String trace = Arrays.toString(e.getStackTrace());
			programacaoResponse.setStatus(false);
			programacaoResponse.setMensagem("Erro ao remover mÃ­dia! ID: " + id + " inexistente.");
			programacaoResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<Object>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
