package com.example.myExpManagement;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MyExpManagementApplicationTests {
	@Test
	boolean getServerDate(){
		long curTime = System.currentTimeMillis();
		log.info("infolog={}",curTime);
		return true;
	}

}
