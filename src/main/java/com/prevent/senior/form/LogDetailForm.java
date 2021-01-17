package com.prevent.senior.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prevent.senior.model.LogDetail;
import com.prevent.senior.repository.LogDetailRepository;

public class LogDetailForm {
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime data;
	
	@NotNull @NotEmpty
	private String ip;
	
	@NotNull @NotEmpty
	private String request;
	
	@NotNull @NotEmpty
	private String status;
	
	@NotNull @NotEmpty
	private String userAgent;
	
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	public LogDetail converter() {
		return new LogDetail(data, ip, request, status, userAgent);
	}
	
	public LogDetail atualizar(Long id, LogDetailRepository logDetailRepository) {
		LogDetail logDetail = logDetailRepository.getOne(id);
		logDetail.setData(this.data);
		logDetail.setIp(this.ip);
		logDetail.setRequest(this.request);
		logDetail.setStatus(this.status);
		logDetail.setUserAgent(this.userAgent);
		
		return logDetail;
	}
}
