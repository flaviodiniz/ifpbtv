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

import com.ifpb.ifpbtvapi.dao.TvDAO;
import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.GradeProgramacao;
import com.ifpb.ifpbtvapi.model.TV;
import com.ifpb.ifpbtvapi.model.response.TVResponse;
import com.ifpb.ifpbtvapi.repository.GradeProgramacaoRepository;
import com.ifpb.ifpbtvapi.repository.TVRepository;

@Service
public class TVService {
	
	@Autowired
	private TVRepository tvRepository;
	
	@Autowired
	private GradeProgramacaoRepository gradeProgramacaoRepository;
	
	@Autowired
	private TvDAO tvDAO;
			
	public TV buscarPeloCodigo(Long id) {
		Optional<TV> tv = tvRepository.findById(id);
		return tv.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + TV.class));
	}
	
	@Transactional
	public ResponseEntity<TVResponse> salvar(TV tv) {
		TVResponse tvResponse = new TVResponse();	
		try {
			switch (validarInformacoes(tv, null)) {
			case 0:
				GradeProgramacao grade = new GradeProgramacao();
				grade.setExibindo(false);
				grade.setAtiva(false);
				grade.setTitulo(tv.getLocal());
				GradeProgramacao gradeSalva = gradeProgramacaoRepository.save(grade);
				tv.setGradeProgramacao(gradeSalva.getId());
				System.out.println(tv.getGradeProgramacao());
				tvRepository.save(tv);
				tvResponse.setStatus(true);
				tvResponse.setMensagem("TV salva com sucesso!");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.OK);
			case 1:
				tvResponse.setStatus(false);
				tvResponse.setMensagem("A chave inserida já existe!");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.BAD_REQUEST);
			case 2:
				tvResponse.setStatus(false);
				tvResponse.setMensagem("Ocorreu um erro interno no momento de salvar a TV!");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			default:
				tvResponse.setStatus(false);
				tvResponse.setMensagem("Ocorreu um erro interno!");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		} catch (DataIntegrityViolationException e) {
			String trace = Arrays.toString(e.getStackTrace());
			tvResponse.setStatus(false);
			tvResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível salvar a TV.");
			tvResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<TVResponse> atualizar(Long id, TV tv) {
		TVResponse tvResponse = new TVResponse();
		try {
			switch (validarInformacoes(tv, id)) {
			case 0:
				TV tvSalva = buscarPeloCodigo(id);
				BeanUtils.copyProperties(tv, tvSalva, "id");
				tvRepository.save(tvSalva);
				tvResponse.setStatus(true);
				tvResponse.setMensagem("TV atualizada com sucesso!");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.OK);
			case 1:
				tvResponse.setStatus(false);
				tvResponse.setMensagem("A chave inserida já pertence a outra TV! Informe outra chave.");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.BAD_REQUEST);
			case 2:
				tvResponse.setStatus(false);
				tvResponse.setMensagem("Ocorreu um erro interno! O ID da TV informada não existe.");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			default:
				tvResponse.setStatus(false);
				tvResponse.setMensagem("Ocorreu um erro interno!");
				return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataIntegrityViolationException e) {
			String trace = Arrays.toString(e.getStackTrace());
			tvResponse.setStatus(false);
			tvResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível atualizar a TV.");
			tvResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<TVResponse>(tvResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private Integer validarInformacoes(TV tv, Long id) {
		int tudoOk = 0;
		int erroChave = 1;
		int erroInterno = 2;
		try {	
			if(tv.getLocal().isEmpty() || tv.getModelo().isEmpty() || tv.getMarca().isEmpty() || tv.isDisponivel() == null) return erroInterno;
			if(id == null) { //SALVANDO TV PELA PRIMEIRA VEZ
				//TV aux = tvRepository.findByChave(tv.getChave());
				//if(aux != null) return erroChave;
				return tudoOk;
				
			} else { //ATUALIZANDO TV JA EXISTENTE
				
				TV tvSalva = buscarPeloCodigo(id);
				
				if(tvSalva != null) {
					
					TV aux = tvRepository.findByChave(tv.getChave());
					
				//	if(aux != null) return erroChave;
					
					return tudoOk;
				}
				return erroInterno;
			}

			
//			else { //ATUALIZANDO TV JA EXISTENTE
//				TV tvSalva = buscarPeloCodigo(id);
//				if(tvSalva != null) {	
//					TV aux = tvRepository.findByChave(tv.getChave());	
//					if(aux != null) return erroChave;	
//					return tudoOk;
//				}
//				return erroInterno;
//			}	
		} catch (NullPointerException e) {
			return erroInterno;
		}
	}
		
	public List<TV> listarTVs() {
		return tvRepository.findAll();
	}
	
	public void excluir(Long id) {
		tvRepository.deleteById(id);
	}
	
	public List<String> listarMarcas() {	
		return tvDAO.getMarcas();
	}
	
	public List<TV> getTvs(String local) {	
		return tvDAO.filtrarTvs(local);
	}
	
	public GradeProgramacao getGradeTv(Long id) {
		return tvDAO.getGradeTv(id);
	}

}
