package com.ads.ifpbtv.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.model.TV;
import com.ads.ifpbtv.repository.ConsultasSqlRepository;
import com.ads.ifpbtv.repository.factory.ExecuteQuery;
import com.ads.ifpbtv.repository.factory.ParamDAO;

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
	

}
