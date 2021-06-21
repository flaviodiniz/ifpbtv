package com.ifpb.ifpbtvapi.model.request;

import com.ifpb.ifpbtvapi.model.enums.DisponibilidadeTV;

public class TVRequest {
	
	private String local;
	private String modelo;
	private String marca;
	private String chave;
	private DisponibilidadeTV disponivel;
	private boolean online;
	
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public DisponibilidadeTV getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(DisponibilidadeTV disponivel) {
		this.disponivel = disponivel;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
}
