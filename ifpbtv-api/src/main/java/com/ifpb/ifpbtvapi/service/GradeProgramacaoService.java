package com.ifpb.ifpbtvapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.GradeProgramacao;
import com.ifpb.ifpbtvapi.model.response.ProgramacaoResponse;
import com.ifpb.ifpbtvapi.repository.GradeProgramacaoRepository;

@Service
public class GradeProgramacaoService {
	
	@Autowired
	private GradeProgramacaoRepository gradeProgramacaoRepository;
	
	
	public GradeProgramacao buscarPeloCodigo(Long id) {
		Optional<GradeProgramacao> programacao = gradeProgramacaoRepository.findById(id);
		return programacao.orElseThrow(() -> new ObjectNotFoundException("Objeto nÃ£o encontrado! ID: " + id + " Tipo: " + GradeProgramacao.class.getName()));
	}
	
	@Transactional
	public ResponseEntity<ProgramacaoResponse> salvar(GradeProgramacao gradeProgramacao) {
		
		ProgramacaoResponse programacaoResponse = new ProgramacaoResponse();
		
		try {
			
			switch (validarInformacoes(gradeProgramacao, null)) {
			
			case 0:
				gradeProgramacaoRepository.save(gradeProgramacao);
				programacaoResponse.setStatus(true);
				programacaoResponse.setMensagem("ProgramaÃ§Ã£o salva com sucesso!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.OK);
				
			case 1:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Alguns campos obrigatÃ³rios nÃ£o foram preenchidos!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.BAD_REQUEST);
				
			case 2:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Ocorreu um erro interno!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);

			default:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Ocorreu um erro interno!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			String trace = Arrays.toString(e.getStackTrace());
			programacaoResponse.setStatus(false);
			programacaoResponse.setMensagem("Ocorreu um erro interno! NÃ£o foi possÃ­vel salvar a programaÃ§Ã£o.");
			programacaoResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ProgramacaoResponse> atualizar(GradeProgramacao gradeProgramacao, Long id) {
		
		ProgramacaoResponse programacaoResponse = new ProgramacaoResponse();
		
		try {
			
			switch (validarInformacoes(gradeProgramacao, id)) {
			
			case 0:
				GradeProgramacao programacaoSalva = buscarPeloCodigo(id);
				BeanUtils.copyProperties(gradeProgramacao, programacaoSalva, "id");
				gradeProgramacaoRepository.save(programacaoSalva);
				programacaoResponse.setStatus(true);
				programacaoResponse.setMensagem("ProgramaÃ§Ã£o atualizada com sucesso!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.OK);
				
			case 1:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Alguns campos obrigatÃ³rios nÃ£o foram preenchidos!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.BAD_REQUEST);
				
			case 2:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Ocorreu um erro interno!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				
			default:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Ocorreu um erro interno!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (DataIntegrityViolationException e) {
			String trace = Arrays.toString(e.getStackTrace());
			programacaoResponse.setStatus(false);
			programacaoResponse.setMensagem("Ocorreu um erro de integridade de dados! NÃ£o foi possÃ­vel atualizar a programaÃ§Ã£o.");
			programacaoResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private Integer validarInformacoes(GradeProgramacao gradeProgramacao, Long id) {
		
		int tudoOk = 0;
		int temErro = 1;
		int erroInterno = 2;
		
		try {
			
			if(gradeProgramacao.getTitulo() == null || gradeProgramacao.getTitulo().isEmpty()) return temErro;
			
			if(id != null) {
				
				GradeProgramacao aux = buscarPeloCodigo(id);
				if(aux != null) return tudoOk;
				return temErro;
			}
			return tudoOk;
			
		} catch (NullPointerException e) {
			return erroInterno;
		}
	}
	
	
	public List<GradeProgramacao> listarProgramacoes() {
		return gradeProgramacaoRepository.findAll();
	}
	
	public void deletarProgramacao(Long id) {
		gradeProgramacaoRepository.deleteById(id);
	}
}
