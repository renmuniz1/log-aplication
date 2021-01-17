package com.prevent.senior.controller;

import java.net.URI;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.prevent.senior.model.LogDetail;
import com.prevent.senior.repository.LogDetailRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class LogControllerTest {
	
	@Autowired
	private LogDetailRepository logDetailRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void requestParaApi() throws Exception {
		populaH2();
		URI uri = new URI("/log-detail?page=0&size=5");
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(200));
	}
	
	
	public void populaH2() {
		LogDetail log1 = new LogDetail(LocalDateTime.now(),"192.168.234.600", "GET / HTTP/1.1", "200", "Mozilla");
		LogDetail log2 = new LogDetail(LocalDateTime.now(),"192.168.234.607", "DELETE / HTTP/1.1", "201", "Mozilla");
		
		logDetailRepository.save(log1);
		logDetailRepository.save(log2);
		
	} 

}
