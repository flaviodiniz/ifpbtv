package com.ifpb.ifpbtvapi.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.Programacao;
import com.ifpb.ifpbtvapi.repository.ConsultasSqlRepository;
import com.ifpb.ifpbtvapi.repository.factory.ExecuteQuery;
import com.ifpb.ifpbtvapi.repository.factory.ParamDAO;

@Service
public class ProgramacaoDAO {
	
	@Autowired
	private ConsultasSqlRepository consultaSqlRepository;
	
	@Autowired
	private ExecuteQuery executeQuery;
	
	public List<String> getTiposProgramacoes() {
		String sql = consultaSqlRepository.getTiposProgramacao().getSql();
		ArrayList<String> lista = null;
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, null);
			if(rs != null) {
				lista = new ArrayList<String>();
				while(rs.next()){	
					String tipoProgramacao = rs.getString("tp_descricao");
					lista.add(tipoProgramacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Programacao> getProgramacoes(String titulo, String tipoProgramacao) {
		String sql = consultaSqlRepository.getProgramacoes().getSql();
		ArrayList<Programacao> lista = null;
		ParamDAO[] params = new ParamDAO[4];
		String nome2 = "%" + titulo + "%";
		String perfil = "%" + tipoProgramacao + "%";
		if(!titulo.equals("undefined")) {
			params[0] = new ParamDAO(nome2, Types.VARCHAR);
			params[1] = new ParamDAO(nome2, Types.VARCHAR);
		} else {
			params[0] = new ParamDAO("-1", Types.VARCHAR);
			params[1] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		if(!tipoProgramacao.equals("undefined")) {
			params[2] = new ParamDAO(perfil, Types.VARCHAR);
			params[3] = new ParamDAO(perfil, Types.VARCHAR);
		} else {
			params[2] = new ParamDAO("-1", Types.VARCHAR);
			params[3] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		try {
			ResultSet rs = executeQuery.executarConsultaSelect(sql, params);
			Programacao programacao = null;
			if(rs != null) {
				lista = new ArrayList<Programacao>();
				while(rs.next()){	
					programacao = new Programacao();
					programacao.setTitulo(rs.getString("titulo"));
					programacao.setTipoProgramacao(rs.getString("tipoProgramacao"));
					programacao.setId(Long.parseLong(rs.getString("id")));
					lista.add(programacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
