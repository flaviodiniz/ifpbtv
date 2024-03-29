package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifpb.ifpbtvapi.model.TV;

@Repository
public interface TVRepository extends JpaRepository<TV, Long> {
	
	public TV findByChave(String chave);
		
}
