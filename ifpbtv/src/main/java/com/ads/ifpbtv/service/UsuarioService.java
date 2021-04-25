package com.ads.ifpbtv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.exceptions.ObjectNotFoundException;
import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
//	@Autowired
//	private UsuarioDao usuarioDao;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario buscarPeloCodigo(Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Usuario.class.getName()));
	}
	
	
	public Usuario atualizar(Long id, Usuario usuario) {
		
		Usuario usuarioSalvo = buscarPeloCodigo(id);
		
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		
		return usuarioRepository.save(usuarioSalvo);
	}
	
	
	public List<Usuario> listarTodos() {
		
		return usuarioRepository.findAll();
	}
	
	
	public Usuario salvar(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}
	
	
	public void remove(Long id) {
		
		usuarioRepository.deleteById(id);
	}
}
