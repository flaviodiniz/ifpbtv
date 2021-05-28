package com.ads.ifpbtv.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.repository.ConsultasSqlRepository;
import com.ads.ifpbtv.repository.factory.ExecuteQuery;

@Service
public class UsuarioDAO {
	
	@Autowired
	private ConsultasSqlRepository consultaSqlRepository;
	
	@Autowired
	private ExecuteQuery executeQuery;
	
	public List<String> perfisUsuarios() {
		String sql = consultaSqlRepository.getPerfisUsuarios().getSql();
		ArrayList<String> lista = null;
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, null);
			if(rs != null) {
				lista = new ArrayList<String>();
				while(rs.next()){	
					String perfil = rs.getString("per_descricao");
					lista.add(perfil);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Usuario> filtrarUsuarios() {
		/*
		 * Montar sql e banco para buscar com os filtros que viram por paramentros
		 * 
		 */
		return null;
	}

}
