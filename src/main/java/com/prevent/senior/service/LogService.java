package com.prevent.senior.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.prevent.senior.model.LogDetail;
import com.prevent.senior.repository.LogDetailRepository;

@Service
public class LogService {
	
	@Autowired
	LogDetailRepository logDetailRepository;
	
	public Page<LogDetail> findByCriteria(String ip,String request,String status,Pageable pageable){
		
		
		return logDetailRepository.findAll(new Specification<LogDetail>() {
			
			@Override
			public Predicate toPredicate(Root<LogDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				
				List<Predicate> predicates = new ArrayList<>();
				
				if(ip!= null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("ip"), ip)));
				}
				
				if(request!= null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("request"), request)));
				}
				
				if(status!= null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), status)));
				}
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		},pageable);
		
	}
}
