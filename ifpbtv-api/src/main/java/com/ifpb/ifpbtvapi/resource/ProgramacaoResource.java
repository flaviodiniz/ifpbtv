package com.ifpb.ifpbtvapi.resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
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
import com.ifpb.ifpbtvapi.model.Programacao;
import com.ifpb.ifpbtvapi.model.response.ProgramacaoResponse;
import com.ifpb.ifpbtvapi.service.ProgramacaoService;

@RestController
@RequestMapping("/programacao")
public class ProgramacaoResource {
	
	@Autowired
	private ProgramacaoService programacaoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPeloCodigo(@PathVariable Long id) {
		try {	
			Programacao programacao = programacaoService.buscarPeloCodigo(id);	
			return ResponseEntity.ok().body(programacao);
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
	public ResponseEntity<ProgramacaoResponse> salvar(@RequestBody Programacao programacao) {	
		return programacaoService.salvar(programacao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProgramacaoResponse> atualizar(@RequestBody Programacao programacao, @PathVariable Long id) {
		return programacaoService.atualizar(programacao, id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Programacao>> listarTodos() {
		List<Programacao> programacoes = programacaoService.listarProgramacoes();
		return ResponseEntity.ok().body(programacoes);
	} 
	
	@GetMapping("/listar/{titulo}/{tipoProgramacao}")
	public ResponseEntity<List<Programacao>> listarProgramacoes(@PathVariable String titulo, @PathVariable String tipoProgramacao) {
		System.out.println(titulo);
		List<Programacao> programacoes = programacaoService.listarProgramacoes(titulo, tipoProgramacao);
		return ResponseEntity.ok().body(programacoes);
	} 
	
	@GetMapping("/listarProgramacoesGrade/{idGrade}")
	public ResponseEntity<List<Programacao>> listarProgramacoes(@PathVariable Long idGrade) {
		List<Programacao> programacoes = programacaoService.getProgramacoesGrade(idGrade);
		return ResponseEntity.ok().body(programacoes);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> remove(@PathVariable Long id) {
		try {
			programacaoService.deletarProgramacao(id);
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
	
	@GetMapping("/getTiposProgramacao")
	public ResponseEntity<List<String>> getTiposMidia() {
		List<String> tipos = programacaoService.getTipoProgramacoes();
		return ResponseEntity.ok().body(tipos);
	}
	
	@GetMapping("/listar/all/{id}")
	public ResponseEntity<List<Object>> getProgramacaoUpload(@PathVariable Long id) {
		List<Object> programacoes = programacaoService.getProgramacaoUpload(id);
		return ResponseEntity.ok().body(programacoes);
	} 
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
	//1048576
}
