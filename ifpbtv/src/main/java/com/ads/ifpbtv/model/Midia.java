package com.ads.ifpbtv.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ads.ifpbtv.model.enums.DisponibilidadeMidia;
import com.ads.ifpbtv.model.enums.TiposDeMidia;

@Entity
@Table(name = "midia")
public class Midia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String chaveEspecifica;
	private String link;
	private Date dataCriacao;
	private Integer duracao;
	private TiposDeMidia tiposDeMidia;
	private DisponibilidadeMidia disponibilidadeMidia;
	private File file;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "midia", cascade = CascadeType.ALL)
	private List<Chaves> chaves = new ArrayList<>();
	
//	@ManyToMany
//	@JoinTable(name = "TB_PLAYLIST_MIDIA", joinColumns = @JoinColumn(name = "midia_id"), inverseJoinColumns = @JoinColumn(name = "playlist_id"))
//	private List<Playlist> playlists = new ArrayList<>();
	
	public Midia() {}

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Chaves> getChaves() {
		return chaves;
	}

	public void setChaves(List<Chaves> chaves) {
		this.chaves = chaves;
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
		Midia other = (Midia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
