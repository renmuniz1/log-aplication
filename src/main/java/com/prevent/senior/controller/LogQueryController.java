package com.prevent.senior.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.prevent.senior.dto.LogDetailDto;
import com.prevent.senior.form.LogDetailForm;
import com.prevent.senior.model.LogDetail;
import com.prevent.senior.repository.LogDetailRepository;
import com.prevent.senior.service.LogService;

@RestController
@RequestMapping("log-detail")
public class LogQueryController {
	
	@Autowired
	LogDetailRepository logDetailRepository;
	@Autowired
	LogService logService;
	
	@GetMapping
	@Cacheable(value = "listaDeLogs")
	public Page<LogDetailDto> lista(@RequestParam(required = false) String ip,
			@RequestParam(required = false) String status,
			@RequestParam(required = false) String request,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		
		Page<LogDetail> logs = logService.findByCriteria(ip, request, status, paginacao);
		return LogDetailDto.converter(logs);
		
		
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeLogs", allEntries = true)
	public ResponseEntity<LogDetailDto> cadastrar(@RequestBody @Valid LogDetailForm form, UriComponentsBuilder uriBuilder) {
		LogDetail log = form.converter();
		logDetailRepository.save(log);
		
		URI uri = uriBuilder.path("/log-detail/{id}").buildAndExpand(log.getId()).toUri();
		return ResponseEntity.created(uri).body(new LogDetailDto(log));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LogDetailDto> detalhar(@PathVariable Long id) {
		Optional<LogDetail> log = logDetailRepository.findById(id);
		if (log.isPresent()) {
			return ResponseEntity.ok(new LogDetailDto(log.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeLogs", allEntries = true)
	public ResponseEntity<LogDetailDto> atualizar(@PathVariable Long id, @RequestBody @Valid LogDetailForm form) {
		Optional<LogDetail> optional = logDetailRepository.findById(id);
		if (optional.isPresent()) {
			LogDetail log = form.atualizar(id, logDetailRepository);
			logDetailRepository.save(log);
			return ResponseEntity.ok(new LogDetailDto(log));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeLogs", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<LogDetail> optional = logDetailRepository.findById(id);
		if (optional.isPresent()) {
			logDetailRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
