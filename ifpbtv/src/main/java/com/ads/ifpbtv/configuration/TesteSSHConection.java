package com.ads.ifpbtv.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ads.ifpbtv.service.SSHService;

@Configuration
public class TesteSSHConection {
	
	@Autowired
	private SSHService ssh;
	
	@Bean
	public void conexaoSSHViaJSch() {
		
		//JSchSSH ssh = new JSchSSH();
		
		ssh.conectar("192.168.1.104");
		
		//System.out.println(ssh.executar("ip addr"));
		
		
//		ssh.uploadFile("C:/Users/moise/OneDrive/Documentos/TESTE.txt", "/home/streaming-server");
//		ssh.uploadFile("C:/Users/moise/OneDrive/Documentos/TESTE 01.txt", "/home/streaming-server");
//		ssh.uploadFile("C:/Users/moise/OneDrive/Documentos/TESTE 02.txt", "/home/streaming-server");
		
		//UPLOAD VIDEO
		//ssh.uploadFile("C:\\Users\\moise\\Downloads\\Personalizar Windows\\Download and install Oracle JDK 11.mp4", "/home/streaming-server");
		
		
//		ssh.download("/home/streaming-server/TESTE.txt", "C:\\Users\\moise\\Downloads\\Personalizar Windows");
//		ssh.download("/home/streaming-server/TESTE 01.txt", "C:\\Users\\moise\\Downloads\\Personalizar Windows");
//		ssh.download("/home/streaming-server/TESTE 02.txt", "C:\\Users\\moise\\Downloads\\Personalizar Windows");
		
		//ssh.deletarArquivo("/home/streaming-server/Download and install Oracle JDK 11.mp4");
		
		//ssh.deletarArquivosComMesmaExtensao("/home/streaming-server/", "txt");
		
		//System.out.println(ssh.listarArquivos());
		//System.out.println(ssh.listarArquivosDeUmDiretorio("/var/www/"));
	}
}
