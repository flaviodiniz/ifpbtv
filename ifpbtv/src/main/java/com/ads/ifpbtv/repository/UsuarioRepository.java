package com.ads.ifpbtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ads.ifpbtv.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//public Optional<Usuario> findByEmail(String email);	
}
