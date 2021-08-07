package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.ifpbtvapi.model.Programacao;

@Repository
public interface ProgramacaoRepository extends JpaRepository<Programacao, Long> {
	
}

