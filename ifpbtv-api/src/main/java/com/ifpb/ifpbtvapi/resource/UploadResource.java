package com.ifpb.ifpbtvapi.resource;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ifpb.ifpbtvapi.model.Midia;
import com.ifpb.ifpbtvapi.model.Upload;
import com.ifpb.ifpbtvapi.repository.UploadRepository;
import com.ifpb.ifpbtvapi.service.UploadService;

@RestController
@RequestMapping("/upload")
public class UploadResource {

	@Autowired
	private UploadService uploadService;
	
	@Autowired
	private UploadRepository repository;

	// @CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/{midia}", method = RequestMethod.POST)
	public ResponseEntity<Upload> uploadDocumentoJpeg(@RequestParam("imageFile") MultipartFile file, @PathVariable Long midia) {
		
		System.out.println("Original Image Byte Size - " + file);
		try {			
			Upload u = uploadService.salvar(file, midia);
			return ResponseEntity.ok().body(u);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//@RequestMapping(method = RequestMethod.POST)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

		System.out.println("Original Image Byte Size - " + file.getBytes().length);

		Upload img = new Upload();
		img.setArquivo(file.getBytes());
		Midia midia2 = new Midia();
		img.setMidia(midia2);

		repository.save(img);

		return (BodyBuilder) ResponseEntity.status(HttpStatus.OK);

	}

//	@GetMapping(path = { "/get/{id}" })
//	public Upload getImage(@PathVariable("imageName") Long id) throws IOException {
//
//		final Optional<Upload> retrievedImage = this.repository.findById(id);
//
//		Upload img = new Upload(retrievedImage.get().getId(), retrievedImage.get().get,
//
//				decompressBytes(retrievedImage.get().getPicByte()));
//
//		return img;
//
//	}


	//descomprime a imagem em bytes antes de ser retornada a aplicação angular

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		try {

			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();

	}

}
