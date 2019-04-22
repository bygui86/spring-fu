package com.rabbit.samples.springjafur2dbc;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ConfigurableApplicationContext;


public class SmokeTests {

	private ConfigurableApplicationContext context;

	@BeforeAll
	void setUp() {

		context = SpringJafuR2dbcApplication.jafuApplication.run("test");
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
