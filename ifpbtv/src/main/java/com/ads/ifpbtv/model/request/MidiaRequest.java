package com.ads.ifpbtv.model.request;

import java.util.Date;
import java.util.List;

import com.ads.ifpbtv.model.enums.DisponibilidadeMidia;
import com.ads.ifpbtv.model.enums.TiposDeMidia;

public class MidiaRequest {
	
	private String titulo;
	private String chaveEspecifica;
	private String link;
	private Date dataCriacao;
	private Integer duracao;
	private TiposDeMidia tiposDeMidia;
	private DisponibilidadeMidia disponibilidadeMidia;
	//FALTA POR O ARQUIVO, MAS AINDA NAO SEI COMO VAI SER FEITO
	private List<ChaveRequest> chaves;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getChaveEspecifica() {
		return chaveEspecifica;
	}
	public void setChaveEspecifica(String chaveEspecifica) {
		this.chaveEspecifica = chaveEspecifica;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	public TiposDeMidia getTiposDeMidia() {
		return tiposDeMidia;
	}
	public void setTiposDeMidia(TiposDeMidia tiposDeMidia) {
		this.tiposDeMidia = tiposDeMidia;
	}
	public DisponibilidadeMidia getDisponibilidadeMidia() {
		return disponibilidadeMidia;
	}
	public void setDisponibilidadeMidia(DisponibilidadeMidia disponibilidadeMidia) {
		this.disponibilidadeMidia = disponibilidadeMidia;
	}
	public List<ChaveRequest> getChaves() {
		return chaves;
	}
	public void setChaves(List<ChaveRequest> chaves) {
		this.chaves = chaves;
	}
}
