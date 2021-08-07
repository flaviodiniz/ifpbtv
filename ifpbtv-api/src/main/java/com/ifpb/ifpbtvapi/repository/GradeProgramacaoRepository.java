package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.ifpbtvapi.model.GradeProgramacao;

@Repository
public interface GradeProgramacaoRepository extends JpaRepository<GradeProgramacao, Long> {
	
}
