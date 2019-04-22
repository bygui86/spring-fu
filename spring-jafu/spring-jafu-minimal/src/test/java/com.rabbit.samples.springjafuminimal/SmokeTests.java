package com.rabbit.samples.springjafuminimal;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ConfigurableApplicationContext;


public class SmokeTests {

	private ConfigurableApplicationContext context;

	@BeforeAll
	void setUp() {

		context = SpringJafuMinimalApplication.jafuApplication.run("test");
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
