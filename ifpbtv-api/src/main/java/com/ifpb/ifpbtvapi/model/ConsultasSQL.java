package com.ifpb.ifpbtvapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "cmdbancodedados")
public class ConsultasSQL {
	
	@Id
	@Column(name="cmd_id")
	private Long id;
	
	@Column(name="cmd_descricao")
	private String descricao;
	
	@Lob
	@Column(name="cmd_sql")
	private String  sql;
	
	@Column(name="cmd_status")
	private String  cmdStatus;

	public ConsultasSQL() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getCmdStatus() {
		return cmdStatus;
	}

	public void setCmdStatus(String cmdStatus) {
		this.cmdStatus = cmdStatus;
	}	

}
