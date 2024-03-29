package com.ifpb.ifpbtvapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.dao.MidiaDAO;
import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.Chaves;
import com.ifpb.ifpbtvapi.model.Midia;
import com.ifpb.ifpbtvapi.model.response.MidiaResponse;
import com.ifpb.ifpbtvapi.repository.MidiaRepository;

@Service
public class MidiaService {
	
	@Autowired
	private MidiaRepository midiaRepository;
	private ChaveService chaveService;
	
	@Autowired
	private MidiaDAO midiaDAO;
	
	public Midia buscarPeloCodigo(Long id) {		
		Optional<Midia> midia = midiaRepository.findById(id);	
		return midia.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Midia.class.getName()));
	}
		
	public ResponseEntity<MidiaResponse> salvar(Midia midia) {
		MidiaResponse midiaResponse = new MidiaResponse();
		
		try {
			switch (validarInformacoes(midia, null)) {
			case 0:
				
				//Midia midia = fromRequest(midiaRequest);
				
				if(midia.getChaves() != null && !midia.getChaves().isEmpty()) {
					Midia midiaSalva = midiaRepository.save(midia);
					//chaveService.salvarChaves(midia.getChaves());
					midiaResponse.setStatus(true);
					midiaResponse.setMensagem("Mídia salva com sucesso!");
					midiaResponse.setMidia(midiaSalva);
					return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.OK);
				}
				Midia midiaSalva = midiaRepository.save(midia);
				midiaResponse.setStatus(true);
				midiaResponse.setMensagem("Mídia salva com sucesso!");
				midiaResponse.setMidia(midiaSalva);
				return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.OK);
			case 1:
				midiaResponse.setStatus(false);
				midiaResponse.setMensagem("Algumas informações da mídia não foram preenchidas ou estão incorretas!");
				return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.BAD_REQUEST);
			default:
				midiaResponse.setStatus(false);
				midiaResponse.setMensagem("Ocorreu um erro interno no momento de salvar a mídia.");
				return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (DataIntegrityViolationException e) {
			String trace = Arrays.toString(e.getStackTrace());
			midiaResponse.setStatus(false);
			midiaResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível salvar a mídia.");
			midiaResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public ResponseEntity<MidiaResponse> atualizar(Midia midia, Long id) {
		
		MidiaResponse midiaResponse = new MidiaResponse();
		
		try {
			
			switch (validarInformacoes(midia, id)) {
			
			case 0:
				
				//Midia midia = fromRequest(midiaRequest);
				Midia midiaSalva = buscarPeloCodigo(id);
				BeanUtils.copyProperties(midia, midiaSalva, "id");
				
				if(midia.getChaves() != null && !midia.getChaves().isEmpty()) {
					
					midiaRepository.save(midia);
					chaveService.salvarChaves(midia.getChaves());
					
					midiaResponse.setStatus(true);
					midiaResponse.setMensagem("Mídia atualizada com sucesso!");
					return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.OK);

				}
				for(Chaves c: midia.getChaves()) {
					System.out.println(c.getChave());
				}
				midiaRepository.save(midia);
				
				midiaResponse.setStatus(true);
				midiaResponse.setMensagem("Mídia atualizada com sucesso!");
				return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.OK);
				
			case 1:
				midiaResponse.setStatus(false);
				midiaResponse.setMensagem("Algumas informações da mídia não foram preenchidas ou estão incorretas!");
				return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.BAD_REQUEST);
				
			default:
				midiaResponse.setStatus(false);
				midiaResponse.setMensagem("Ocorreu um erro interno no momento de atualizar a mídia.");
				return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (DataIntegrityViolationException e) {
			String trace = Arrays.toString(e.getStackTrace());
			midiaResponse.setStatus(false);
			midiaResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível atualizar a mídia.");
			midiaResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<MidiaResponse>(midiaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	private Integer validarInformacoes(Midia midia, Long id) {
		
		int tudoOk = 0;
		int temErro = 1;
		
		try {
			
			if(midia != null) {
				
				if(midia.getTitulo() == null || midia.getTitulo().isEmpty()) return temErro;
				
				if(midia.getChaveEspecifica() == null || midia.getChaveEspecifica().isEmpty()) return temErro;
				
				//if(midia.getDataCriacao() == null) return temErro;
				
				//if(midia.getTiposDeMidia() == null) return temErro;
				
				if(midia.getDisponibilidadeMidia() == null) return temErro;
				
				//FALTA VERIFICAR A RESPEITO DO ARQUIVO
				
				if(id == null) {
					
					Midia aux = midiaRepository.findByChaveEspecifica(midia.getChaveEspecifica());
					
					if(aux != null) return temErro;
					
					return tudoOk;
					
				} else {
					
					Midia midiaSalva = buscarPeloCodigo(id);
					
					if(midiaSalva != null) return tudoOk;
					
					return temErro;
				}
			}
			return temErro;
			
		} catch (NullPointerException e) {
			return temErro;
		}
	}
	
	
	public List<Midia> listarMidias() {	
		return midiaRepository.findAll();
	}
	
	public void excluir(Long id) {
		midiaDAO.deletaUploadDeMidia(id);
		midiaRepository.deleteById(id);
	}
	
	public List<String> getTiposMidia() {	
		return midiaDAO.getTiposMidia();
	}
	
	public List<String> getDisponibilidadeMidia() {	
		return midiaDAO.getDisponibilidadeMidia();
	}
	
	public List<Chaves> getChaves() {	
		return midiaDAO.getChaves();
	}
//	private Midia fromRequest(MidiaRequest midiaRequest) {
//		
//		Midia midia = new Midia();
//		
//		midia.setTitulo(midiaRequest.getTitulo());
//		midia.setChaveEspecifica(midiaRequest.getChaveEspecifica());
//		midia.setDataCriacao(midiaRequest.getDataCriacao());
//		midia.setLink(midiaRequest.getLink());
//		midia.setDuracao(midiaRequest.getDuracao());
//		midia.setTiposDeMidia(midiaRequest.getTiposDeMidia());
//		midia.setDisponibilidadeMidia(midiaRequest.getDisponibilidadeMidia());
//		
//		if(midiaRequest.getChaves() != null && !midiaRequest.getChaves().isEmpty()) {
//			
//			List<Chaves> chaves = new ArrayList<>();
//			
//			for (ChaveRequest cr : midiaRequest.getChaves()) {
//				
//				Chaves key = new Chaves();
//				key.setChave(cr.getTitulo());
//				key.setMidia(midia);
//				
//				chaves.add(key);
//			}
//			midia.setChaves(chaves);
//		}
//		return midia;
//	}
	
	public List<Midia> getMidias(String titulo, String chave, String tipo, Long usuario) {
		return midiaDAO.getMidias(titulo, chave, tipo, usuario);
	}
}
