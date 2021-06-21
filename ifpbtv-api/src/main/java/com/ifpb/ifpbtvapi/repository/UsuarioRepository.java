package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifpb.ifpbtvapi.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email);
	
	@Transactional(readOnly = true)
	public Usuario findByMatricula(String matricula);
	
}
