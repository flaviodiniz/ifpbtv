package com.ifpb.ifpbtvapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "programacao")
public class Programacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String tipoProgramacao;
	private String horarioInicio;
	private String horarioFim;
	private String diaSemana;
	private Date dataCriacao;
	private Date dataExibicao;
	private Long usuario;
	
	//@OneToMany
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="programacao_id", 
        joinColumns = @JoinColumn(name = "id"))
    @Column(name="datas_programacao")
	private List<Date> datasExibidas;
	
	@ManyToMany
	@JoinTable(name = "midia_programacao", joinColumns = @JoinColumn(name = "programacao_id")
	, inverseJoinColumns = @JoinColumn(name = "midia_id"))
	private List<Midia> playlist;
	
	public Programacao() {}

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

	public String getTipoProgramacao() {
		return tipoProgramacao;
	}

	public void setTipoProgramacao(String tipoProgramacao) {
		this.tipoProgramacao = tipoProgramacao;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public String getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public List<Midia> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<Midia> playlist) {
		this.playlist = playlist;
	}

	public Date getDataExibicao() {
		return dataExibicao;
	}

	public void setDataExibicao(Date dataExibicao) {
		this.dataExibicao = dataExibicao;
	}

	public List<Date> getDatasExibidas() {
		return datasExibidas;
	}

	public void setDatasExibidas(List<Date> datasExibidas) {
		this.datasExibidas = datasExibidas;
	}
	
	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
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
		Programacao other = (Programacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

