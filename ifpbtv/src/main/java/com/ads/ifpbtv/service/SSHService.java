package com.ads.ifpbtv.service;

import org.springframework.stereotype.Service;

import com.ads.ifpbtv.ssh.JSchSSH;

@Service
public class SSHService {
	
	private JSchSSH ssh = new JSchSSH();
	
	
	public boolean conectar(String host) {
		
		return ssh.connect(host);
	}
	
	
	
	public String executarComando(String comando) {
		
		return ssh.executeCommand(comando);
	}
	
	
	
	public boolean enviarArquivo(String caminhoOrigem, String dirDestino) {
		
		return ssh.uploadFile(caminhoOrigem, dirDestino);
	}
	
	
	
	public boolean baixarArquivo(String arquivoRemoto, String arquivoLocal) {
		
		return ssh.download(arquivoRemoto, arquivoLocal);
	}
	
	
	
	public boolean deletarArquivo(String caminhoArquivo) {
		
		return ssh.deletarArquivo(caminhoArquivo);
	}
	
	
	
	public boolean deletarArquivosComMesmaExtensao(String caminho, String extensao) {
		
		return ssh.deletarArquivosComMesmaExtensao(caminho, extensao);
	}
	
	
	
	public boolean criarDiretorio(String nomeDiretorio) {
		
		return ssh.criarDiretorioRemoto(nomeDiretorio);
	}
	
	
	
	public String listarArquivos() {
		
		return ssh.listarArquivos();
	}
	
	
	
	public String listarArquivosDeUmDiretorio(String caminho) {
		
		return ssh.listarArquivosDeUmDiretorio(caminho);
	}
	
	
	
	public void fecharConexao() {
		
		ssh.close();
	}
}
