package com.ads.ifpbtv.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ads.ifpbtv.ssh.JSchSSH;

@Configuration
public class TesteSSHConection {
	
	@Bean
	public void conexaoSSHViaJSch() {
		
		try {
			
			JSchSSH.connect("streaming-server", "streaming-server", "192.168.80.128", 22, "ip addr");
			
			System.out.println("CONEXÃO SSH REALIZADA COM SUCESSO!");
			
		} catch (Exception e) {
			
			System.out.println("CONEXÃO SSH FALHOU!!!");
			e.printStackTrace();
		}
	}
	
	
//	@Bean
//	public void conexaoSSHViaApacheMinaSSHD() {
//		
//		try {
//			
//			ApacheMinaSSHD.listFolderStructure("streaming-server", "streaming-server", "192.168.1.101", 22, System.currentTimeMillis(), "ip addr");
//			
//			System.out.println("CONEXÃO SSH REALIZADA COM SUCESSO!");
//			
//		} catch (IOException e) {
//			
//			System.out.println("CONEXÃO SSH FALHOU!!!");
//			e.printStackTrace();
//		}
//	}
}
