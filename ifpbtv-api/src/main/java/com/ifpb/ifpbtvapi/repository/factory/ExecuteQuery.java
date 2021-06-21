package com.ifpb.ifpbtvapi.repository.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import org.springframework.stereotype.Service;

@Service
public class ExecuteQuery extends ConnectionFactoryDB {
	
	public ResultSet executarConsultaSelect(String sql, ParamDAO[] params) throws ClassNotFoundException, NamingException, SQLException {
		if(params != null && params.length > 0) {
			return super.executeQuery(sql, params);
		}		
		return super.executeQuery(sql);
	}
	
	public Integer executarConsultaUpdateOrInsert(String sql, ParamDAO[] params) throws ClassNotFoundException, NamingException, SQLException {
		int	linhasAfetadas = super.executeUpdate(sql, params);
		return linhasAfetadas;
	}
	
	public Integer executarConsultaUpdateOrInsert(String sql, ParamDAO[] params, Connection conexao) throws ClassNotFoundException, NamingException, SQLException {
		if(conexao == null) {
			return super.executeUpdate(sql, params);
		}
		return super.executeUpdate(sql, params, conexao);
	}
	
	public Integer executarConsultaInsertComRetornoID(String sql, ParamDAO[] params,String nomeColunaId) throws ClassNotFoundException, NamingException, SQLException {
		System.out.println("\""+nomeColunaId+"\"");
		int	id = super.executeUpdate(sql, params, new String[] {nomeColunaId});
		return id;
	}
	
	public ArrayList<String> executarProcedure(String strStatment, ParamDAO[] params) throws ClassNotFoundException, NamingException, SQLException {
		ArrayList<String> saida = super.executeCallable(strStatment, params);
		return saida;
	}

}
