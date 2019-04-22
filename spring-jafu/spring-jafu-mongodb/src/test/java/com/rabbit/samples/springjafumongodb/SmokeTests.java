package com.rabbit.samples.springjafumongodb;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


public class SmokeTests {

	private ConfigurableApplicationContext context;

	@BeforeAll
	void setUp() {

		context = SpringJafuMongoDbApplication.jafuApplication.run("test");
	}

	@Test
	public void contextLoads() {

		// no-op
	}

	@AfterAll
	void tearDown() {

		context.stop();
	}

}
