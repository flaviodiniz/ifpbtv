package com.ads.ifpbtv.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.repository.ConsultasSqlRepository;
import com.ads.ifpbtv.repository.factory.ExecuteQuery;

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
	
	public List<String> getChaves() {
		String sql = consultaSqlRepository.getChaves().getSql();
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


}
