package com.ads.ifpbtv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ads.ifpbtv.model.ConsultasSQL;

public interface ConsultasSqlRepository extends JpaRepository<ConsultasSQL, Long > {
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 1  and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getPerfisUsuarios();
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 2 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getUsuarios();


}
