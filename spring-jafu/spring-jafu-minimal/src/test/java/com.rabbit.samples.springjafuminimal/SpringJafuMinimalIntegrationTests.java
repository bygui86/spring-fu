package com.rabbit.samples.springjafuminimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 20 Apr 2019
 */
public class SpringJafuMinimalIntegrationTests {

	private WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8181").build();

	private ConfigurableApplicationContext context;

	@BeforeAll
	public void beforeAll() {

		context = SpringJafuMinimalApplication.jafuApplication.run("test");
	}

	@Test
	public void requestRootEndpoint() {

		// given
		// -

		client
				// when
				.get()
				.uri("/")
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectBody(String.class).isEqualTo("Hello world!");
	}

	@Test
	public void requestApiEndpoint() {

		// given
		// -

		client
				// when
				.get()
				.uri("/api")
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectBody(String.class).isEqualTo("{\"message\":\"Hello world!\"}");
	}

	@AfterAll
	void afterAll() {

		context.close();
	}

}
