package com.ifpb.ifpbtvapi.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.dao.ProgramacaoDAO;
import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.Programacao;
import com.ifpb.ifpbtvapi.model.response.ProgramacaoResponse;
import com.ifpb.ifpbtvapi.repository.ProgramacaoRepository;

@Service
public class ProgramacaoService {
	
	@Autowired
	private ProgramacaoRepository programacaoRepository;
	
	@Autowired
	private ProgramacaoDAO programacaoDAO;
	
	
	public Programacao buscarPeloCodigo(Long id) {
		Optional<Programacao> programacao = programacaoRepository.findById(id);
		return programacao.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Programacao.class.getName()));
	}
	
	@Transactional
	public ResponseEntity<ProgramacaoResponse> salvar(Programacao programacao) {
		
		ProgramacaoResponse programacaoResponse = new ProgramacaoResponse();
		LocalDate hoje = LocalDate.now();
		Date data = Date.from(hoje.atStartOfDay(ZoneId.systemDefault()).toInstant());
		programacao.setDataCriacao(data);
		try {
			
			switch (validarInformacoes(programacao, null)) {
			
			case 0:
				programacaoRepository.save(programacao);
				programacaoResponse.setStatus(true);
				programacaoResponse.setMensagem("Programação salva com sucesso!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.OK);
				
			case 1:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Alguns campos obrigatórios não foram preenchidos!");
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
			programacaoResponse.setMensagem("Ocorreu um erro interno! Não foi possível salvar a programação.");
			programacaoResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<ProgramacaoResponse> atualizar(Programacao programacao, Long id) {
		
		ProgramacaoResponse programacaoResponse = new ProgramacaoResponse();
		
		try {
			
			switch (validarInformacoes(programacao, id)) {
			
			case 0:
				Programacao programacaoSalva = buscarPeloCodigo(id);
				BeanUtils.copyProperties(programacao, programacaoSalva, "id");
				programacaoRepository.save(programacaoSalva);
				programacaoResponse.setStatus(true);
				programacaoResponse.setMensagem("Programação atualizada com sucesso!");
				return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.OK);
				
			case 1:
				programacaoResponse.setStatus(false);
				programacaoResponse.setMensagem("Alguns campos obrigatórios não foram preenchidos!");
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
			programacaoResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível atualizar a programação.");
			programacaoResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<ProgramacaoResponse>(programacaoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	private Integer validarInformacoes(Programacao programacao, Long id) {
		
		int tudoOk = 0;
		int temErro = 1;
		int erroInterno = 2;
		
		try {
			
			if(programacao.getTitulo() == null || programacao.getTitulo().isEmpty() || programacao.getTipoProgramacao() == null || programacao.getTipoProgramacao().isEmpty()) return temErro;
			
		//	if(programacao.getHorarioInicio() == null || programacao.getHorarioInicio().isEmpty() || programacao.getHorarioFim() == null || programacao.getHorarioFim().isEmpty()) return temErro;
			
		//	if(programacao.getDiaSemana() == null || programacao.getDiaSemana().isEmpty()) return temErro;
			
			if(id != null) {
				
				Programacao aux = buscarPeloCodigo(id);
				if(aux != null) return tudoOk;
				return temErro;
			}
			return tudoOk;
			
		} catch (NullPointerException e) {
			return erroInterno;
		}
	}	
	
	public List<Programacao> listarProgramacoes() {
		return programacaoRepository.findAll();
	}
	
	public List<Programacao> listarProgramacoes(String titulo, String tipoProgramacao) {
		return programacaoDAO.getProgramacoes(titulo, tipoProgramacao);
	}
	
	public List<Programacao> getProgramacoesGrade(Long idGrade) {
		return programacaoDAO.getProgramacoesGrade(idGrade);
	}
	
	public void deletarProgramacao(Long id) {
		programacaoRepository.deleteById(id);
	}
	
	public List<String> getTipoProgramacoes(){
		return programacaoDAO.getTiposProgramacoes();
	}
	
	public List<Object> getProgramacaoUpload(Long id){
		return programacaoRepository.getProgramacaoUpload(id);
	}
}
