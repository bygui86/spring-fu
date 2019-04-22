package com.rabbit.samples.springjafuminimal;

import com.rabbit.samples.springjafuminimal.domain.Sample;
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
class IntegrationTests {

	private WebTestClient webTestClient =
			WebTestClient.bindToServer().baseUrl("http://localhost:8181").build();

	private ConfigurableApplicationContext context;

	@BeforeAll
	void setUp() {

		context = SpringJafuMinimalApplication.jafuApplication.run("test");
	}

	@Test
	void requestRootEndpoint() {

		// given
		// -

		webTestClient
				// when
				.get()
				.uri("/")
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectBody(String.class).isEqualTo("Hello world!")
		;
	}

	@Test
	void requestApiEndpoint() {

		// given
		// -

		webTestClient
				// when
				.get()
				.uri("/api")
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectBody(Sample.class).isEqualTo(
						Sample.builder().message("Hello world!").build()
				)
		;
	}

	@AfterAll
	void tearDown() {

		context.close();
	}

}
