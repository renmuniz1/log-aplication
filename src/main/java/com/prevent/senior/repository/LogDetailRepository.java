package com.prevent.senior.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.prevent.senior.model.LogDetail;

public interface LogDetailRepository extends JpaRepository<LogDetail, Long>,JpaSpecificationExecutor<LogDetail>{
	
	Page<LogDetail> findByIp(String ip, Pageable paginacao);
	
	List<LogDetail> findByIp(String ip);
	
	void deleteByIp(String ip);

}
