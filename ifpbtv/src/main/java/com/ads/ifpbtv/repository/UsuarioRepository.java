package com.ads.ifpbtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ads.ifpbtv.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email);
	
	@Transactional(readOnly = true)
	public Usuario findByMatricula(String matricula);
	
//	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
//	public Usuario buscarPeloEmail(@Param("email") String email);
//	
//	@Query("SELECT u FROM Usuario u WHERE u.matricula = :matricula")
//	public Usuario buscarPelaMatricula(@Param("matricula") String matricula);
	
}
