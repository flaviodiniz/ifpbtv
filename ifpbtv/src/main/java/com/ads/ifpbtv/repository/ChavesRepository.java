package com.ads.ifpbtv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ads.ifpbtv.model.Chaves;

@Repository
public interface ChavesRepository extends JpaRepository<Chaves, Long> {
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM midia WHERE midia_id = :midiaID", nativeQuery = true)
	List<Chaves> buscarChavesPorMidia(@Param("midiaID") Long id);
}
