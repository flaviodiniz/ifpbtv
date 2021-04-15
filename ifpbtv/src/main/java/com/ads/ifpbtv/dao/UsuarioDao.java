package com.ads.ifpbtv.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.repository.UsuarioRepository;

@Service
public class UsuarioDao {
	
	@Autowired
	private UsuarioRepository usarioRepository;
	
	public Usuario buscarPeloCodigo(Long id) {
		Usuario usuarioSalvo = usarioRepository.getOne(id);
		if(usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalvo;	
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarPeloCodigo(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usarioRepository.save(usuarioSalvo);
	}
	
	public List<Usuario> listarTodos() {
		return usarioRepository.findAll();
	}
	
	public List<Usuario> filtrarUsuarios() {
		/*
		 * Montar sql e banco para buscar com os filtros que viram por paramentros
		 * 
		 */
		return usarioRepository.findAll();
	}
	
	public Usuario salvar(Usuario usuario) {
		return usarioRepository.save(usuario);
	}
	
	public void remove(Long id) {
		usarioRepository.deleteById(id);
	}


}
