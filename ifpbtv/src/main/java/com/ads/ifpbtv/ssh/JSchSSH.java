package com.ads.ifpbtv.ssh;

import java.io.ByteArrayOutputStream;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JSchSSH {
	
	//Implementacao do SSH com JSch

	public static void connect(String username, String password, String host, int port, String command) throws Exception {

		Session session = null;
		ChannelExec channel = null;

		try {
			
			session = new JSch().getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			
			ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			channel.setOutputStream(responseStream);
			channel.connect();

			while (channel.isConnected()) {
				Thread.sleep(100);
			}

			String responseString = new String(responseStream.toByteArray());
			System.out.println(responseString);
			
		} finally {
			
			if (session != null) {
				session.disconnect();
			}
			
			if (channel != null) {
				channel.disconnect();
			}
		}
	}
	
	
	public boolean uploadFile(String user, String password, String host, String path) { 
		
		try {
			
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host);
			
			session.setPassword(password);
			session.connect();

			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();

			sftpChannel.put(path); //Caminho  do arquivo.
			
			sftpChannel.disconnect();
			session.disconnect();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean getFile(String user, String password, String host, String path) { 
		
		try {
			
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host);
			
			session.setPassword(password);
			session.connect();
			
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			
			sftpChannel.get(path); //Caminho  do arquivo
			
			System.out.println(sftpChannel.get(path));
			
			sftpChannel.disconnect();
			session.disconnect();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
