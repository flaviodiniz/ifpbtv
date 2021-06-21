package com.ifpb.ifpbtvapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarSenhas {
	
	private static final int COMPRIMENTO_MINIMO_SENHA = 8;
	
	public boolean validarSenha(String senha) {
		
		boolean tamanhoOk = true;
		boolean caracterNaoAlfabetico = true;
		
		if(senha == null || senha.isEmpty()) {
			
			return false;
		}
		
		if(!isComprimentoSenhaValido(senha)) {
			
			tamanhoOk = false;
		}
		
		if(!senhaContemCaracterNaoAlfabetico(senha)) {
			
			caracterNaoAlfabetico = false;
		}
		
		if(tamanhoOk == true && caracterNaoAlfabetico == true) return true;
		
		return false;
	}
	
	
	public boolean validarTrocaDeSenha(String novaSenha, String confirmaSenha, String senhaAnterior) {
		
		boolean tamanhoOk = true;
		boolean caracterNaoAlfabetico = true;
		boolean novaSenhaDiferenteDaAnterior = true;
		boolean senhaENovaSenhaSaoIguais = true;
		
		if(novaSenha == null || novaSenha.isEmpty() || confirmaSenha == null || confirmaSenha.isEmpty() || senhaAnterior == null || senhaAnterior.isEmpty()) {
			
			return false;
		}
		
		// validar se a senha contém no mínimo oito caracteres
		if (!isComprimentoSenhaValido(novaSenha)) {
			
			tamanhoOk = false; 
		}
		
		// validar se a senha contém no mínimo um caracter não alfabético
		if (!senhaContemCaracterNaoAlfabetico(novaSenha)) {
			
			caracterNaoAlfabetico = false; 
		}
		
		// validar se a nova senha é diferente da anterior
		if (!isSenhaDiferenteAnterior(novaSenha, senhaAnterior)) {
			
			novaSenhaDiferenteDaAnterior = false;
		}
		
		// validar se senha e a confirmaSenha são iguais
		if (confirmaSenha != null && !isSenhaIgualConfirmaSenha(novaSenha, confirmaSenha)) {
			
			senhaENovaSenhaSaoIguais = false;
		}
		
		
		if(tamanhoOk == true && caracterNaoAlfabetico == true && novaSenhaDiferenteDaAnterior == true && senhaENovaSenhaSaoIguais == true) return true;
		
		return false;
	}

	
	private boolean senhaContemCaracterNaoAlfabetico(String senha) {
		
		Pattern p = Pattern.compile("[\\s\\d\\W]"); 
		Matcher m = p.matcher(senha);
		
		return m.find();
	}
	

	private boolean isSenhaDiferenteAnterior(String senha, String senhaAnterior) {
		return !senha.equals(senhaAnterior);
	}

	
	private boolean isSenhaIgualConfirmaSenha(String senha, String confirmaSenha) {
		return senha.equals(confirmaSenha);
	}
	
	
	private boolean isComprimentoSenhaValido(String senha) {
		return senha.length() >= COMPRIMENTO_MINIMO_SENHA;
	}
}
