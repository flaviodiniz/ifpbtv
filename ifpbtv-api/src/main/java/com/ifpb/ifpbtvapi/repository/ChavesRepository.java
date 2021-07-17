package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ifpb.ifpbtvapi.model.Chaves;

@Repository
public interface ChavesRepository extends JpaRepository<Chaves, Long> {
	
//	@Transactional(readOnly = true)
//	@Query(value = "SELECT * FROM midia WHERE midia_id = :midiaID", nativeQuery = true)
//	List<Chaves> buscarChavesPorMidia(@Param("midiaID") Long id);
}
