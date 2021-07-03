package com.ifpb.ifpbtvapi.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifpb.ifpbtvapi.model.Midia;
import com.ifpb.ifpbtvapi.model.Upload;
import com.ifpb.ifpbtvapi.repository.UploadRepository;

@Service
public class UploadService {
	
	@Autowired
	private UploadRepository uploadRepository; 
	
	public Upload salvar(MultipartFile multipepartFile, Long midia) {
		try {
			Midia midia2 = new Midia();
			midia2.setId(midia);
			InputStream initialStream = multipepartFile.getInputStream();
			byte[] buffer = new byte[initialStream.available()];
			initialStream.read(buffer);
			Upload upload = new Upload();
			upload.setMidia(midia2);
			upload.setArquivo(buffer);
			return salve(upload);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Upload salve(Upload upload) {
		return uploadRepository.save(upload);
	}

}
