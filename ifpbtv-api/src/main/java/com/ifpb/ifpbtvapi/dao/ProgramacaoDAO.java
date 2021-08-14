package com.ifpb.ifpbtvapi.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.ifpbtvapi.model.Midia;
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
		String title = "%" + titulo + "%";
		String programationType = "%" + tipoProgramacao + "%";
		if(!titulo.equals("undefined")) {
			params[0] = new ParamDAO(title, Types.VARCHAR);
			params[1] = new ParamDAO(title, Types.VARCHAR);
		} else {
			params[0] = new ParamDAO("-1", Types.VARCHAR);
			params[1] = new ParamDAO("-1", Types.VARCHAR);
		}
		
		if(!tipoProgramacao.equals("undefined")) {
			params[2] = new ParamDAO("%" + programationType + "%", Types.VARCHAR);
			params[3] = new ParamDAO("%" + programationType + "%", Types.VARCHAR);
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
					programacao.setDiaSemana(rs.getString("diaSemana"));
					programacao.setHorarioInicio(rs.getString("horarioInicio"));
					programacao.setHorarioFim(rs.getString("horarioFim"));
					programacao.setDataCriacao(rs.getDate("dataCriacao"));
				//	programacao.setPlaylist((List<Midia>) rs.getArray("playlist"));
					lista.add(programacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Programacao> getProgramacoesGrade(Long idGrade) {
		String sql = "SELECT pro.* FROM programacao pro\r\n" + 
				"INNER JOIN grade_de_programacao gdp on gdp.programacao = pro.id\r\n" + 
				"WHERE gdp.grade = ?";
		ArrayList<Programacao> lista = null;
		ParamDAO[] params = new ParamDAO[1];
		
		params[0] = new ParamDAO(idGrade, Types.BIGINT);
		
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
					programacao.setDiaSemana(rs.getString("diaSemana"));
					programacao.setHorarioInicio(rs.getString("horarioInicio"));
					programacao.setHorarioFim(rs.getString("horarioFim"));
					programacao.setDataCriacao(rs.getDate("dataCriacao"));
					lista.add(programacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	

}
