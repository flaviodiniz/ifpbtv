package com.ifpb.ifpbtvapi.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.Chaves;
import com.ifpb.ifpbtvapi.repository.ConsultasSqlRepository;
import com.ifpb.ifpbtvapi.repository.factory.ExecuteQuery;

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
}
