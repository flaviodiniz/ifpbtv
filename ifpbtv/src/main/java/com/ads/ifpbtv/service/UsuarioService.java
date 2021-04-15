package com.ads.ifpbtv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.dao.UsuarioDao;
import com.ads.ifpbtv.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public Usuario buscarPeloCodigo(Long id) {
		return usuarioDao.buscarPeloCodigo(id);
			
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		return usuarioDao.atualizar(id, usuario);
	}
	
	public List<Usuario> listarTodos() {
		return usuarioDao.listarTodos();
	}
	
	public Usuario salvar(Usuario usuario) {
		return usuarioDao.salvar(usuario);
	}
	
	public void remove(Long id) {
		usuarioDao.remove(id);
	}

}
