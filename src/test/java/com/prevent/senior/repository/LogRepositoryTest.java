package com.prevent.senior.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.prevent.senior.model.LogDetail;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class LogRepositoryTest {
	
	@Autowired
	private LogDetailRepository logDetailRepository;
	
	@Test
	public void operacoesCrudBasicas() {
		
		populaH2();
		
		Optional<LogDetail> optional = logDetailRepository.findById(1l);
		Assert.assertNotNull(optional.get());
		logDetailRepository.deleteById(1l);
		optional = logDetailRepository.findById(1l);
		Assert.assertEquals(optional.isEmpty(),true);
		
		List<LogDetail>lista = logDetailRepository.findAll();
		Assert.assertEquals(lista.isEmpty(),false);
		
	}
	
	public void populaH2() {
		LogDetail log1 = new LogDetail(LocalDateTime.now(),"192.168.234.600", "GET / HTTP/1.1", "200", "Mozilla");
		LogDetail log2 = new LogDetail(LocalDateTime.now(),"192.168.234.607", "DELETE / HTTP/1.1", "201", "Mozilla");
		
		logDetailRepository.save(log1);
		logDetailRepository.save(log2);
		
	} 
	
}
