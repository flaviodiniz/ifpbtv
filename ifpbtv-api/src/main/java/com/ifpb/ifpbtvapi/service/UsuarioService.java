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

import com.ifpb.ifpbtvapi.dao.UsuarioDAO;
import com.ifpb.ifpbtvapi.exceptions.ObjectNotFoundException;
import com.ifpb.ifpbtvapi.model.Usuario;
import com.ifpb.ifpbtvapi.model.response.UsuarioResponse;
import com.ifpb.ifpbtvapi.repository.UsuarioRepository;
import com.ifpb.ifpbtvapi.utils.ValidarSenhas;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	private ValidarSenhas validar = new ValidarSenhas();
		
	public Usuario buscarPeloCodigo(Long id) {	
		Optional<Usuario> usuario = usuarioRepository.findById(id);	
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Usuario.class.getName()));
	}
	
	public ResponseEntity<UsuarioResponse> salvar(Usuario usuario) {
		
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		
		try {
			
			switch (validarInformacoes(usuario, null)) {
			
			case 0:
				
				//Usuario usuario = fromRequest(usuarioRequest);
				
				usuarioRepository.save(usuario);	
				usuarioResponse.setStatus(true);
				usuarioResponse.setMensagem("Usuário salvo com sucesso!");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.OK);	
				
			case 1:	
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("O e-mail inserido já existe!");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);	
				
			case 2:	
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("A matrícula inserida já existe!");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);
				
			case 3:	
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("A senha deve conter no mínimo oito caracteres. Sendo obrigatório que um dos caracteres não seja uma letra.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);
				
			case 4:
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("Erro! Todos os campos são de preenchimento obrigatório.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);
				
			default:	
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("Ocorreu um erro interno no momento de salvar o usuário!");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (DataIntegrityViolationException e) {
			
			String trace = Arrays.toString(e.getStackTrace());
			
			usuarioResponse.setStatus(false);
			usuarioResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível salvar o usuário.");
			usuarioResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<UsuarioResponse> atualizar(Long id, Usuario usuario) {	
		
		UsuarioResponse usuarioResponse = new UsuarioResponse();
		
		try {
			
			switch (validarInformacoes(usuario, id)) {
			
			case 0:
				
				//Usuario usuario = fromRequest(usuarioRequest);
				Usuario usuarioSalvo = buscarPeloCodigo(id);
				BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
				usuarioRepository.save(usuarioSalvo);
				
				usuarioResponse.setStatus(true);
				usuarioResponse.setMensagem("Usuário atualizado com sucesso!");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.OK);
				
			case 1:
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("O e-mail inserido já pertence a outro usuário! Informe outro e-mail.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);
				
			case 2:
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("A matrícula inserida já pertence a outro usuário! Informe outra matrícula.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);
				
			case 3:
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("A senha deve conter no mínimo oito caracteres. Sendo obrigatório que um dos caracteres não seja uma letra.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.BAD_REQUEST);
				
			case 4:
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("Ocorreu um erro interno! O ID do usuário informado não existe, ou os campos obrigatórios não foram preenchidos.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
				
			default:
				usuarioResponse.setStatus(false);
				usuarioResponse.setMensagem("Ocorreu um erro interno! Entre em contato com a equipe de desenvolvimento para verificar o ocorrido.");
				return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (DataIntegrityViolationException e) {
			
			String trace = Arrays.toString(e.getStackTrace());
			
			usuarioResponse.setStatus(false);
			usuarioResponse.setMensagem("Ocorreu um erro de integridade de dados! Não foi possível atualizar o usuário.");
			usuarioResponse.setTrace(trace.substring(1, trace.indexOf(",")));
			return new ResponseEntity<UsuarioResponse>(usuarioResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	private Integer validarInformacoes(Usuario usuario, Long id) {
		
		int tudoOk = 0;
		int erroEmail = 1;
		int erroMatricula = 2;
		int erroSenha = 3;
		int erroInterno = 4;
		
		try {
			
			if(usuario.getNome().isEmpty() || usuario.getEmail().isEmpty() || usuario.getMatricula().isEmpty() || usuario.getSenha().isEmpty() || usuario.getPerfil().equals(null)) return erroInterno;
			
			if(id == null) { // SALVANDO O USUARIO PELA PRIMEIRA VEZ
				
				Usuario aux01 = usuarioRepository.findByEmail(usuario.getEmail());
				Usuario aux02 = usuarioRepository.findByMatricula(usuario.getMatricula());
				
				if(aux01 != null) return erroEmail;
				
				if(aux02 != null) return erroMatricula;
				
				if(validar.validarSenha(usuario.getSenha()) == false) return erroSenha;
				
				return tudoOk;
				
			} else { //ATUALIZANDO UM USUARIO JA EXISTENTE
				
				Usuario usuarioSalvo = buscarPeloCodigo(id);
				
				if(usuarioSalvo != null) {
					
					if(usuarioSalvo.getEmail().equals(usuario.getEmail()) && usuarioSalvo.getMatricula().equals(usuario.getMatricula())) {
						
						//NAO MUDOU NEM O EMAIL E NEM A MATRICULA	
						if(validar.validarSenha(usuario.getSenha()) == false) return erroSenha;
						
						return tudoOk;
					} 
					
					if(!usuarioSalvo.getEmail().equals(usuario.getEmail()) && usuarioSalvo.getMatricula().equals(usuario.getMatricula())) {
						
						//MUDOU O EMAIL
						Usuario aux = usuarioRepository.findByEmail(usuario.getEmail());
						
						if(aux != null) return erroEmail;
						
						if(validar.validarSenha(usuario.getSenha()) == false) return erroSenha;
						
						return tudoOk;
					}
					
					if(!usuarioSalvo.getMatricula().equals(usuario.getMatricula()) && usuarioSalvo.getEmail().equals(usuario.getEmail())) {
						
						//MUDOU A MATRICULA
						Usuario aux = usuarioRepository.findByMatricula(usuario.getMatricula());
						
						if(aux != null) return erroMatricula;
						
						if(validar.validarSenha(usuario.getSenha()) == false) return erroSenha;
						
						return tudoOk;
					}
					return tudoOk;
				}
				return erroInterno;
			}
			
		} catch (NullPointerException e) {
			return erroInterno;
		}
	}
		
	//vai ficar desnecessario
	public List<Usuario> listarUsuarios() {	
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> getUsuarios(String nome, String matricula, String perfil) {	
		return usuarioDAO.filtrarUsuarios(nome, matricula, perfil);
	}
	
	public List<String> listarPerfis() {	
		return usuarioDAO.perfisUsuarios();
	}
	
	public void excluir(Long id) {
		usuarioRepository.deleteById(id);
	}
	
//	private Usuario fromRequest(Usuario usuarioRequest) {
//		
//		Usuario usuario = new Usuario();
//		
//		usuario.setNome(usuarioRequest.getNome());
//		usuario.setEmail(usuarioRequest.getEmail());
//		usuario.setSenha(usuarioRequest.getSenha());
//		usuario.setMatricula(usuarioRequest.getMatricula());
//		usuario.setPerfil(usuarioRequest.getPerfil());
//		usuario.setAtivo(usuarioRequest.isAtivo());
//		
//		return usuario;
//	}
}
