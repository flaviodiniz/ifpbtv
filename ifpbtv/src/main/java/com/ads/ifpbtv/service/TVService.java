package com.ads.ifpbtv.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.dao.TvDAO;
import com.ads.ifpbtv.exceptions.ObjectNotFoundException;
import com.ads.ifpbtv.model.TV;
import com.ads.ifpbtv.model.request.TVRequest;
import com.ads.ifpbtv.model.response.TVResponse;
import com.ads.ifpbtv.repository.TVRepository;

@Service
public class TVService {
	
	@Autowired
	private TVRepository tvRepository;
	
	@Autowired
	private TvDAO tvDAO;
			
	public TV buscarPeloCodigo(Long id) {
		Optional<TV> tv = tvRepository.findById(id);
		return tv.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + TV.class));
	}
	
<<<<<<< HEAD
	
	public ResponseEntity<TVResponse> salvar(TVRequest tvRequest) {
		
		TVResponse tvResponse = new TVResponse();
		
		try {
			
			switch (validarInformacoes(tvRequest, null)) {
			
			case 0:
				TV tv = fromRequest(tvRequest);
=======
	public ResponseEntity<TVResponse> salvar(TV tv) {
		TVResponse tvResponse = new TVResponse();	
		try {
			switch (validarInformacoes(tv, null)) {
			case 0:	
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
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
	
	
<<<<<<< HEAD
	public ResponseEntity<TVResponse> atualizar(Long id, TVRequest tvRequest) {
		
=======
	public ResponseEntity<TVResponse> atualizar(Long id, TV tv) {
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
		TVResponse tvResponse = new TVResponse();
		try {
<<<<<<< HEAD
			
			switch (validarInformacoes(tvRequest, id)) {
			
			case 0:
				TV tv = fromRequest(tvRequest);
				TV tvSalva = buscarPeloCodigo(id);
				BeanUtils.copyProperties(tv, tvSalva, "id");
				tvRepository.save(tvSalva);
				
=======
			switch (validarInformacoes(tv, id)) {
			case 0:	
				TV tvSalva = buscarPeloCodigo(id);	
				BeanUtils.copyProperties(tv, tvSalva, "id");	
				tvRepository.save(tvSalva);	
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
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
	
<<<<<<< HEAD
	
	private Integer validarInformacoes(TVRequest tvRequest, Long id) {
		
		int tudoOk = 0;
		int erroChave = 1;
		int erroInterno = 2;
		
		try {
			
			if(tvRequest.getLocal().isEmpty() || tvRequest.getModelo().isEmpty() || tvRequest.getMarca().isEmpty() || tvRequest.getChave().isEmpty() || tvRequest.getDisponivel() == null) return erroInterno;
			
			if(id == null) { //SALVANDO TV PELA PRIMEIRA VEZ
				
				TV aux = tvRepository.findByChave(tvRequest.getChave());
				
=======
	private Integer validarInformacoes(TV tv, Long id) {
		int tudoOk = 0;
		int erroChave = 1;
		int erroInterno = 2;
		try {	
			if(tv.getLocal().isEmpty() || tv.getModelo().isEmpty() || tv.getMarca().isEmpty() || tv.isDisponivel() == null) return erroInterno;
			if(id == null) { //SALVANDO TV PELA PRIMEIRA VEZ
				TV aux = tvRepository.findByChave(tv.getChave());
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
				if(aux != null) return erroChave;
				return tudoOk;
<<<<<<< HEAD
				
			} else { //ATUALIZANDO TV JA EXISTENTE
				
				TV tvSalva = buscarPeloCodigo(id);
				
				if(tvSalva != null) {
					
					TV aux = tvRepository.findByChave(tvRequest.getChave());
					
					if(aux != null) return erroChave;
					
					return tudoOk;
				}
				return erroInterno;
			}
=======
			} 
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
			
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
		return tudoOk;
	}
		
	public List<TV> listarTVs() {
		return tvRepository.findAll();
<<<<<<< HEAD
	}
=======
	}	
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
	
	public void excluir(Long id) {
		tvRepository.deleteById(id);
	}
	
<<<<<<< HEAD
	private TV fromRequest(TVRequest tvRequest) {
		
		TV tv = new TV(null, tvRequest.getLocal(), tvRequest.getModelo(), tvRequest.getMarca(), tvRequest.getChave(), tvRequest.getDisponivel(), tvRequest.isOnline());
		
		return tv;
	}
=======
	public List<String> listarMarcas() {	
		return tvDAO.getMarcas();
	}
	
	public List<TV> getTvs(String local) {	
		return tvDAO.filtrarTvs(local);
	}
	
>>>>>>> 43ff5d2e32e105d63b6a315c7f37e79dc9456679
}
