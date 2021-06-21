package com.ifpb.ifpbtvapi.ssh;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchSSH {
	
	private final Logger LOG = LoggerFactory.getLogger(JSchSSH.class);
	
	//@Value("${NGINX_LOGIN}")
	private final String LOGIN = "streaming-server";
	
	//@Value("${NGINX_PASSWORD}")
	private final String SENHA = "streaming-server";
	
	//@Value("${NGINX_PORT}")
	private final int PORTA = 22;
	
	private InputStream commandOutput;
	private BufferedReader reader;
	private Session session;
	private ChannelExec channel;
	
	
	public boolean connect(String host) {

		try {
			
			this.session = new JSch().getSession(LOGIN, host, PORTA);
			this.session.setPassword(SENHA);
			this.session.setConfig("StrictHostKeyChecking", "no");
			this.session.connect();
			
			LOG.info("CONECTADO AO SERVIDOR NGINX! HOST: " + host);
			
			return true;
			
		} catch(JSchException e) {
			LOG.error("ERRO AO CONECTAR AO NGINX " + e.getMessage());
			return false;
		}
	}
	
	
	
	public String executeCommand(String comando) {
		
		try {
			
			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(comando);
			setCommandOutput(channel.getInputStream());
			
			channel.connect();
			
			StringBuilder sBuilder = new StringBuilder();
			String read = reader.readLine();
			
			while (read != null) {
				sBuilder.append(read);
				sBuilder.append("\n");
				read = reader.readLine();
			}

			return sBuilder.toString();
			
		} catch (Exception e) {
			LOG.error("ERRO AO EXECUTAR COMANDO " + comando + ":" + e.getMessage());
		}
		return null;
	}
	
	
	
	/*
	 * FAZ O upload DO ARQUIVO DA MAQUINA LOCAL PARA O DIRETORIO REMOTO
	 * 
	 * PARAMETROS (CAMINHO DO ARQUIVO LOCALMENTE, CAMINHO DE DESTINO NO DIRETORIO REMOTO)
	 * 
	 * */
	
	public boolean uploadFile(String caminhoOrigem, String dirDestino) { 
		
		try {
			
			File file = new File(caminhoOrigem);
			
			String destino = dirDestino.concat("/").concat(file.getName());
			
			dirDestino = dirDestino.replace(" ", "_"); 
			
			LOG.info("UPLOAD REALIZADO COM SUCESSO!");
			
			return upload(caminhoOrigem, destino, dirDestino);
				
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERRO AO FAZER UPLOAD: " + e.getMessage());
		}
		return false;
	}
	
	
	
	private boolean upload(String origem, String destino, String dirDestino) {

		try {	
			
			ChannelSftp sftp = (ChannelSftp) this.session.openChannel("sftp");			
			sftp.connect();		
			
			dirDestino = dirDestino.replace(" ", "_");									
			sftp.cd(dirDestino);
			sftp.put(origem, destino);
			//sftp.put(commandOutput, dirDestino); //trocar para esse depois
			sftp.disconnect();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERRO NO METODO UPLOAD: " + e.getMessage());
		}
		return false;
	}
	
	
	
	
	/*
	 * FAZ O DOWNLOAD DO ARQUIVO DO DIRETORIO REMOTO PARA A MAQUINA LOCAL
	 * 
	 * PARAMETROS (CAMINHO DO ARQUIVO REMOTO, CAMINHO DE DESTINO APOS DO DOWNLOAD NA MAQUINA LOCAL)
	 * 
	 * */
	public boolean download(String arquivoRemoto, String arquivoLocal) { 

		try {

			ChannelSftp sftp = (ChannelSftp) session.openChannel("sftp");
			sftp.connect();

			sftp.get(arquivoRemoto, arquivoLocal);

			sftp.disconnect();

			LOG.info("DOWNLOAD REALIZADO COM SUCESSO!");
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("ERRO AO FAZER DOWNLOAD: " + e.getMessage());
			return false;
		}
	}
	
	//COMO A APP ANDROID CONVERSA COMO SERVIDOR
	
	//COMO ELA REPRODUZ UM VIDEO
	
	/*
	 * APAGA O ARQUIVO DO DIRETORIO REMOTO
	 * 
	 * PARAMETROS (CAMINHO DO ARQUIVO NO DIRETORIO REMOTO)
	 * 
	 * */
	
	public boolean deletarArquivo(String caminhoDoArquivo) { 
		
		try {
			
			
			executeCommand("rm " + caminhoDoArquivo);
			
			LOG.info("ARQUIVO " + caminhoDoArquivo + " EXCLUIDO COM SUCESSO!");
			
			return true;
			
			
//			if(existeArquivo(caminhoDoArquivo)) {
//				
//				
//				
//			} else {
//				
//				LOG.warn("O ARQUIVO " + caminhoDoArquivo + " NAO EXISTE!");
//				
//				return false;
//			}
			
			
		} catch (Exception e) {
			LOG.error("ERRO AO EXCLUIR ARQUIVO DO DIRETORIO REMOTO: " + e.getMessage());
			return false;
		}
	}
	
	
	/*
	 * APAGA TODOS OS ARQUIVOS COM A MESMA EXTENSAO DO DIRETORIO REMOTO
	 * 
	 * PARAMETROS (CAMINHO NO DIRETORIO, TIPO DE EXTENSAO)
	 * 
	 * */
	
	public boolean deletarArquivosComMesmaExtensao(String caminho, String extensao) { 
		
		try {
			
			executeCommand("rm " + caminho + "*." + extensao);
			
			LOG.info("TODOS OS ARQUIVOS COM EXTENSAO ." + extensao + "  FORAM EXCLUIDOS COM SUCESSO!");
			
			return true;
			
		} catch (Exception e) {
			LOG.error("ERRO AO EXCLUIR ARQUIVOS COM MESMA EXTENSAO DO DIRETORIO REMOTO: " + e.getMessage());
			return false;
		}
	}
	
	
	
	public boolean criarDiretorioRemoto(String nomeDiretorio) { //NECESSARIO ACESSO DE SUPER USUARIO
		
		try{
			
			executeCommand("mkdir -p " + nomeDiretorio);
			
			LOG.info("DIRETORIO REMOTO " + nomeDiretorio + " CRIADO COM SUCESSO!");
			
			return true;
			 
		} catch(Exception e) { 
			e.printStackTrace();
			LOG.error("ERRO AO CRIAR DIRETORIO REMOTO: " + e.getMessage());
			return false;
		}
	}
	
	
	
	public String listarArquivos() {
		
		try {
			
			LOG.info("LISTANDO ARQUIVOS...");
			
			return executeCommand("ls");
			
		} catch (Exception e) {
			LOG.error("NAO FOI POSSIVEL LISTAR OS ARQUIVOS!" + e.getMessage());
			return null;
		}
	}
	
	
	public String listarArquivosDeUmDiretorio(String caminho) {
		
		try {
			
			LOG.info("LISTANDO ARQUIVOS DE " + caminho);
			
			return executeCommand("ls " + caminho);
			
		} catch (Exception e) {
			LOG.error("NAO FOI POSSIVEL LISTAR OS ARQUIVOS!" + e.getMessage());
			return null;
		}
	}
	
	//=====================================================================================================================
	
	public void close() {
		
		if (this.session != null) {
			this.session.disconnect();
			
			LOG.info("SESSÃO DESCONECTADA!");
		}
		
		if (this.channel != null) {
			this.channel.disconnect();
			
			LOG.info("CANAL DESCONECTADO!");
		}
	}
	
	public boolean existeArquivo(String origem) {					
		
		File file = new File(origem);
		
		if (file.exists() && file.isFile()) return true;
			
		return false;
	}


	public void setCommandOutput(InputStream in) {
		
		this.commandOutput = in;
		reader = new BufferedReader(new InputStreamReader(in));
	}
	
	
	public InputStream getCommandOutput() {
		return commandOutput;
	}
}
