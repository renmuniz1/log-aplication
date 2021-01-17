package com.prevent.senior.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "LOG_DETAIL")
public class LogDetail {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "log_generator")
    @SequenceGenerator(
            name = "log_generator",
            sequenceName = "log_sequence",
            initialValue = 1
    )
	private Long id;
	
	@Column(name = "Data")
	private LocalDateTime data;
	
	@Column(name = "IP")
	private String ip;
	
	@Column(name = "Request")
	private String request;
	
	@Column(name = "Status")
	private String status;
	
	@Column(name = "User_Agent")
	private String userAgent;
	
	public LogDetail(LocalDateTime data, String ip, String request, String status, String userAgent) {
		super();
		this.data = data;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.userAgent = userAgent;
	}

	public LogDetail() {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogDetail other = (LogDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
