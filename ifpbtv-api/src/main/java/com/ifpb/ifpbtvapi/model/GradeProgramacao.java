package com.ifpb.ifpbtvapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "grade_programacao")
public class GradeProgramacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	private String titulo;
	private boolean exibindo;
	private boolean ativa;
	
	@ManyToMany
	@JoinTable(name = "grade_de_programacao", joinColumns = @JoinColumn(name = "grade"),inverseJoinColumns = @JoinColumn(name = "programacao"))
	private List<Programacao> programacoes;
	
	@ManyToMany
	@JoinTable(name = "grade_tv", joinColumns = @JoinColumn(name = "grade"),inverseJoinColumns = @JoinColumn(name = "tv"))
	private List<TV> tvs;
	
	public GradeProgramacao() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isExibindo() {
		return exibindo;
	}

	public void setExibindo(boolean exibindo) {
		this.exibindo = exibindo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public List<Programacao> getProgramacoes() {
		return programacoes;
	}

	public void setProgramacoes(List<Programacao> programacoes) {
		this.programacoes = programacoes;
	}

	public List<TV> getTvs() {
		return tvs;
	}

	public void setTvs(List<TV> tvs) {
		this.tvs = tvs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GradeProgramacao other = (GradeProgramacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}