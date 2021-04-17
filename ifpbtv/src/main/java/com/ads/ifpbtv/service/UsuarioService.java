package com.ads.ifpbtv.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

//import com.ads.ifpbtv.dao.UsuarioDao;
import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
//	@Autowired
//	private UsuarioDao usuarioDao;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarPeloCodigo(Long id) {
		Usuario usuarioSalvo = usuarioRepository.getOne(id);
		if(usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalvo;		
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
