package com.prevent.senior.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prevent.senior.model.LogDetail;
import com.prevent.senior.repository.LogDetailRepository;
import com.prevent.senior.util.LogDetailUtil;

@Service
public class UploadService {
	
	@Autowired
	LogDetailRepository repository;
	
	public void save(MultipartFile file) {
		try {
			List<LogDetail> details = LogDetailUtil.fileToList(file.getInputStream());
			repository.saveAll(details);
		} catch (IOException e) {
			throw new RuntimeException("falha ao salvar o arquivo: " + e.getMessage());
		}
	}

}
