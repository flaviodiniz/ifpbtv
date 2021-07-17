package com.ifpb.ifpbtvapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ifpb.ifpbtvapi.model.Upload;
import com.ifpb.ifpbtvapi.service.UploadService;

@RestController
@RequestMapping("/upload")
public class UploadResource {
	
	@Autowired
	private UploadService uploadService;
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{midia}" , method=RequestMethod.POST)
	public ResponseEntity<Upload> uploadDocumentoJpeg(@RequestParam MultipartFile file, @PathVariable Long midia) {
		Upload u = uploadService.salvar(file, midia);
		return ResponseEntity.ok().body(u);
	}

}
