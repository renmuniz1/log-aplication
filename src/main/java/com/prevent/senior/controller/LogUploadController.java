package com.prevent.senior.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.prevent.senior.message.ResponseMessage;
import com.prevent.senior.service.UploadService;

@RestController
@RequestMapping("log-detail")
public class LogUploadController {
	
	@Autowired
	UploadService uploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> upload(@RequestParam("file") MultipartFile file){
		String message = "";
		
		try {
			uploadService.save(file);
			message = "O arquivo foi carregado: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
		} catch (Exception e) {
			 message = "Não foi possível carregar o arquivo: " + file.getOriginalFilename() + "!";
		     return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

}
