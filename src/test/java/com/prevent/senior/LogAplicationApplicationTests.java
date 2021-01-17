package com.prevent.senior;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LogAplicationApplicationTests {

	@Test
	void contextLoads() {
		
		String line = "2020-01-01 00:00:11.763|192.168.234.82|\"GET / HTTP/1.1\"|200|\"swcd "
				+ "(unknown version) CFNetwork/808.2.16 Darwin/15.6.0\"";
		
		String val[]  = line.split("[|]");
		
		Assert.assertEquals(val.length, 5);
	}

}
