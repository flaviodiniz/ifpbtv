package com.ads.ifpbtv.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.ads.ifpbtv.model.Usuario;
import com.ads.ifpbtv.repository.ConsultasSqlRepository;
import com.ads.ifpbtv.repository.factory.ExecuteQuery;
import com.ads.ifpbtv.repository.factory.ParamDAO;

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
	
	public List<Usuario> filtrarUsuarios(String nome, String matricula, String pefil) {
		String sql = consultaSqlRepository.getUsuarios().getSql();
		ArrayList<Usuario> lista = null;
		ParamDAO[] params = new ParamDAO[6];
		String nome2 = "%" + nome + "%";
		String perfil = "%" + pefil + "%";
		System.out.println(perfil);
		if(!nome.equals("undefined")) {
			params[0] = new ParamDAO(nome2, Types.VARCHAR);
			params[1] = new ParamDAO(nome2, Types.VARCHAR);
		} else {
			params[0] = new ParamDAO("-1", Types.VARCHAR);
			params[1] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		if(!matricula.equals("undefined")) {
			params[2] = new ParamDAO("%" + matricula + "%", Types.VARCHAR);
			params[3] = new ParamDAO("%" + matricula + "%", Types.VARCHAR);
		} else {
			params[2] = new ParamDAO("-1", Types.VARCHAR);
			params[3] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		if(!pefil.equals("undefined")) {
			params[4] = new ParamDAO(perfil, Types.VARCHAR);
			params[5] = new ParamDAO(perfil, Types.VARCHAR);
		} else {
			params[4] = new ParamDAO("-1", Types.VARCHAR);
			params[5] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, params);
			Usuario usuario = null;
			if(rs != null) {
				lista = new ArrayList<Usuario>();
				while(rs.next()){	
					usuario = new Usuario();
					usuario.setId(Long.parseLong(rs.getString("id")));
					usuario.setNome(rs.getString("nome"));
					usuario.setMatricula(rs.getString("matricula"));
					usuario.setStatus(rs.getString("status"));
					usuario.setEmail(rs.getString("email"));
					usuario.setPerfil(rs.getString("perfil"));
					System.out.println(usuario.getNome());
					lista.add(usuario);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
