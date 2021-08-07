package com.ifpb.ifpbtvapi.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.Chaves;
import com.ifpb.ifpbtvapi.model.Midia;
import com.ifpb.ifpbtvapi.model.Usuario;
import com.ifpb.ifpbtvapi.repository.ConsultasSqlRepository;
import com.ifpb.ifpbtvapi.repository.factory.ExecuteQuery;
import com.ifpb.ifpbtvapi.repository.factory.ParamDAO;

@Service
public class MidiaDAO {
	
	@Autowired
	private ConsultasSqlRepository consultaSqlRepository;
	
	@Autowired
	private ExecuteQuery executeQuery;
	
	public List<String> getTiposMidia() {
		String sql = consultaSqlRepository.getTiposMidia().getSql();
		ArrayList<String> lista = null;
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, null);
			if(rs != null) {
				lista = new ArrayList<String>();
				while(rs.next()){	
					String marca = rs.getString("tpm_descricao");
					lista.add(marca);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<String> getDisponibilidadeMidia() {
		String sql = consultaSqlRepository.getDisponibilidadeMidia().getSql();
		ArrayList<String> lista = null;
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, null);
			if(rs != null) {
				lista = new ArrayList<String>();
				while(rs.next()){	
					String marca = rs.getString("dpm_descricao");
					lista.add(marca);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Chaves> getChaves() {
		String sql = consultaSqlRepository.getChaves().getSql();
		ArrayList<Chaves> lista = null;
		Chaves chave = null;
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, null);
			if(rs != null) {
				lista = new ArrayList<Chaves>();
				while(rs.next()){	
					chave = new Chaves();
					String marca = rs.getString("chave");
					Long id = rs.getLong("id");
					chave.setChave(marca);
					chave.setId(id);
					lista.add(chave);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Midia> getMidias(String titulo, String chave, String tipo) {
		String sql = consultaSqlRepository.getMidias().getSql();
		ArrayList<Midia> lista = null;
		ParamDAO[] params = new ParamDAO[6];
		String nome2 = "%" + titulo + "%";
		String perfil = "%" + tipo + "%";
		if(!titulo.equals("undefined")) {
			params[0] = new ParamDAO(nome2, Types.VARCHAR);
			params[1] = new ParamDAO(nome2, Types.VARCHAR);
		} else {
			params[0] = new ParamDAO("-1", Types.VARCHAR);
			params[1] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		if(!chave.equals("undefined")) {
			params[2] = new ParamDAO("%" + chave + "%", Types.VARCHAR);
			params[3] = new ParamDAO("%" + chave + "%", Types.VARCHAR);
		} else {
			params[2] = new ParamDAO("-1", Types.VARCHAR);
			params[3] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		if(!tipo.equals("undefined")) {
			params[4] = new ParamDAO(perfil, Types.VARCHAR);
			params[5] = new ParamDAO(perfil, Types.VARCHAR);
		} else {
			params[4] = new ParamDAO("-1", Types.VARCHAR);
			params[5] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, params);
			Midia midia = null;
			if(rs != null) {
				lista = new ArrayList<Midia>();
				while(rs.next()){	
					midia = new Midia();
					Usuario usuario = new Usuario();
					usuario.setId(rs.getLong("usuario_id"));
					usuario.setNome(rs.getString("nome"));
					midia.setId(Long.parseLong(rs.getString("id")));
					midia.setTitulo(rs.getString("titulo"));
					midia.setLink(rs.getString("link"));
					midia.setChaveEspecifica(rs.getString("chaveEspecifica"));
					midia.setTipoMidia(rs.getString("tipoMidia"));
					midia.setUsuario(usuario);
					lista.add(midia);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public boolean deletaUploadDeMidia(Long midia) {
		System.out.println(midia);
		String sql = consultaSqlRepository.deletaUploadMidia().getSql();
		ParamDAO[] params = new ParamDAO[1];
		params[0] = new ParamDAO(midia, Types.BIGINT);
		try {
			int rs = executeQuery.executarConsultaUpdateOrInsert(sql, params);
			if(rs != 0) {		
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
