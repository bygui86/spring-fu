package com.rabbit.samples.springjafumongodb;

import com.mongodb.client.result.DeleteResult;
import com.rabbit.samples.springjafumongodb.domain.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 22 Apr 2019
 */
class IntegrationTests {

	private WebTestClient webTestClient =
			WebTestClient.bindToServer().baseUrl("http://localhost:8181").build();

	private ConfigurableApplicationContext context;

	@BeforeAll
	void setUp() {

		context = SpringJafuMongoDbApplication.jafuApplication.run("test");
	}

	@Test
	void requestAllUsers() {

		// given
		// -

		webTestClient
				// when
				.get()
				.uri("/users")
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectHeader().contentType(MediaType.APPLICATION_STREAM_JSON)
				.expectBodyList(User.class)
		;
	}

	@Test
	void requestUserById() {

		// given
		String login = "mattb";

		webTestClient
				// when
				.get()
				.uri("/users/" + login)
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(User.class).isEqualTo(
						User.builder().login(login).firstname("matteo").lastname("baiguini").build()
				)
		;
	}

	@Test
	void saveUser() {

		// given
		final User user = User.builder().login("joed").firstname("joe").lastname("doe").build();

		webTestClient
				// when
				.post()
				.uri("/users")
				.body(Mono.just(user), User.class)
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(User.class).isEqualTo(user)
		;
	}

	@Test
	void deleteUserById() {

		// given
		String login = "janed";

		webTestClient
				// when
				.delete()
				.uri("/users/" + login)
				.exchange()

				// then
				.expectStatus().is2xxSuccessful()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
		;
	}

	@AfterAll
	void tearDown() {

		context.stop();
	}
}