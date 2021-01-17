package com.prevent.senior.dto;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import com.prevent.senior.model.LogDetail;

public class LogDetailDto {
	
	private Long id;
	
	private LocalDateTime data;
	
	private String ip;
	
	private String request;
	
	private String status;
	
	private String userAgent;
	
	public LogDetailDto(LogDetail logDetail) {
		
		super();
		this.id = logDetail.getId();
		this.data = logDetail.getData();
		this.ip = logDetail.getIp();
		this.request = logDetail.getRequest();
		this.status = logDetail.getStatus();
		this.userAgent = logDetail.getUserAgent();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
	public static Page<LogDetailDto> converter(Page<LogDetail> logs) {
		 return logs.map(LogDetailDto::new);
	}
	
}
