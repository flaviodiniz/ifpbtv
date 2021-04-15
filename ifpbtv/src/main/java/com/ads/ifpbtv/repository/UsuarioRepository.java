package com.ads.ifpbtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ads.ifpbtv.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//public Optional<Usuario> findByEmail(String email);	
}
