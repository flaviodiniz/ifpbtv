package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ifpb.ifpbtvapi.model.Midia;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, Long> {
	
	@Transactional(readOnly = true)
	public Midia findByChaveEspecifica(String chave);
	
}
