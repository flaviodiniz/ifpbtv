package com.ifpb.ifpbtvapi.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.GradeProgramacao;
import com.ifpb.ifpbtvapi.model.TV;
import com.ifpb.ifpbtvapi.repository.ConsultasSqlRepository;
import com.ifpb.ifpbtvapi.repository.factory.ExecuteQuery;
import com.ifpb.ifpbtvapi.repository.factory.ParamDAO;

@Service
public class TvDAO {
	
	@Autowired
	private ConsultasSqlRepository consultaSqlRepository;
	
	@Autowired
	private ExecuteQuery executeQuery;
	
	public List<String> getMarcas() {
		String sql = consultaSqlRepository.getMarcas().getSql();
		ArrayList<String> lista = null;
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, null);
			if(rs != null) {
				lista = new ArrayList<String>();
				while(rs.next()){	
					String marca = rs.getString("mar_descricao");
					lista.add(marca);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<TV> filtrarTvs(String local) {
		String sql = consultaSqlRepository.getTvs().getSql();
		ArrayList<TV> lista = null;
		ParamDAO[] params = new ParamDAO[2];
		String local2 = "%" + local + "%";
	
		if(!local.equals("undefined")) {
			params[0] = new ParamDAO(local2, Types.VARCHAR);
			params[1] = new ParamDAO(local2, Types.VARCHAR);
		} else {
			params[0] = new ParamDAO("-1", Types.VARCHAR);
			params[1] = new ParamDAO("-1", Types.VARCHAR);
		}
				
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, params);
			TV tv = null;
			if(rs != null) {
				lista = new ArrayList<TV>();
				while(rs.next()){	
					tv = new TV();
					tv.setId(Long.parseLong(rs.getString("id")));
					tv.setLocal(rs.getString("local"));
					tv.setModelo(rs.getString("modelo"));
					tv.setMarca(rs.getString("marca"));
					tv.setChave(rs.getString("chave"));					
					tv.setDisponivel(rs.getString("disponivel"));
					tv.setGradeProgramacao(rs.getLong("gradeProgramacao"));
					String on = rs.getString("online");
					if (on.equals("false")) {
						tv.setOnline(false);
					} else {
						tv.setOnline(true);
					}
					lista.add(tv);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public GradeProgramacao getGradeTv(Long id) {
		String sql = consultaSqlRepository.getGradeTv().getSql();
		GradeProgramacao grade = null;
		ParamDAO[] params = new ParamDAO[1];
		params[0] = new ParamDAO(id, Types.BIGINT);
		
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, params);
			if(rs != null) {
				while(rs.next()){	
					grade = new GradeProgramacao();
					grade.setId(rs.getLong("id"));
					grade.setExibindo(rs.getBoolean("exibindo"));
					grade.setAtiva(rs.getBoolean("ativa"));
					grade.setTitulo(rs.getString("titulo"));
					return grade;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grade; 
	}
	

}
