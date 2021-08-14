package com.ifpb.ifpbtvapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ifpb.ifpbtvapi.model.Programacao;

@Repository
public interface ProgramacaoRepository extends JpaRepository<Programacao, Long> {
	
	@Query(value="SELECT u.*, md.titulo, md.tipoMidia, md.chaveEspecifica, md.disponibilidadeMidia, md.dataCriacao FROM upload u LEFT JOIN midia md ON u.midia_id = md.id	INNER JOIN midia_programacao mpg ON	md.id = mpg.midia_id AND mpg.programacao_id = :id", nativeQuery = true)
	List<Object> getProgramacaoUpload(@Param("id") Long id);
}

