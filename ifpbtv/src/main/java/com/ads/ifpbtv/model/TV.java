package com.ads.ifpbtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tv")
public class TV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "local_tv")
	private String local;
	
	private String modelo;
	private String marca;
	private String chave;
	
	@Column(name = "tv_disponivel")
	private Disponibilidade disponivel;
	
	@Column(name = "tv_online")
	private boolean online;
	
	public TV() {}

	public TV(Long id, String local, String modelo, String marca, String chave, Disponibilidade disponivel, boolean online) {
		super();
		this.id = id;
		this.local = local;
		this.modelo = modelo;
		this.marca = marca;
		this.chave = chave;
		this.disponivel = disponivel;
		this.online = online;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Disponibilidade isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Disponibilidade disponivel) {
		this.disponivel = disponivel;
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
		TV other = (TV) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
