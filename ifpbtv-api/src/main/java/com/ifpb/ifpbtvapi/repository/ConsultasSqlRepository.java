package com.ifpb.ifpbtvapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ifpb.ifpbtvapi.model.ConsultasSQL;

public interface ConsultasSqlRepository extends JpaRepository<ConsultasSQL, Long > {
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 1  and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getPerfisUsuarios();
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 2 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getUsuarios();

	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 3 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getMarcas();
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 4 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getTvs();
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 5 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getTiposMidia();
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 6 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getDisponibilidadeMidia();	
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 7 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getChaves();	
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 8 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL getMidias();
	
	@Query(value = "SELECT * FROM cmdbancodedados WHERE cmd_id = 9 and cmd_status = 'A'", nativeQuery = true)
	ConsultasSQL deletaUploadMidia();
}
