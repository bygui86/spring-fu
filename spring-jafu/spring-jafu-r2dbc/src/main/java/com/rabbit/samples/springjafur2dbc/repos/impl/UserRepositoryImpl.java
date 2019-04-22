package com.rabbit.samples.springjafur2dbc.repos.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.samples.springjafur2dbc.domain.User;
import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.function.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class UserRepositoryImpl implements UserRepository {

	DatabaseClient databaseClient;

	ObjectMapper objectMapper;

	@Override
	public Flux<User> findAll() {

		log.debug("find all users");

		return getDatabaseClient()
				.select()
				.from("users")
				.as(User.class)
				.fetch()
				.all();
	}

	@Override
	public Mono<User> findById(final String id) {

		log.debug("find user by id {}", id);

		return getDatabaseClient()
				.execute()
				.sql("SELECT * FROM users WHERE login = $1")
				.bind(0, id)
				.as(User.class)
				.fetch()
				.one();
	}

	@Override
	public Flux<User> saveAll(final Flux<User> users) {

		log.debug("save all users {}", users);

		return users.doOnNext(
				user -> save(Mono.just(user)).subscribe()
		);
	}

	@Override
	public Mono<User> save(final Mono<User> user) {

		log.debug("save user {}", user);

		return getDatabaseClient()
				.insert()
				.into(User.class)
				.table("users")
				.using(user)
				.map((row, rowMetadata) -> row.get("login", User.class))
				.one();
	}

	@Override
	public Mono<Void> deleteAll() {

		log.debug("delete all users");

		return getDatabaseClient()
				.execute()
				.sql("DELETE FROM users")
				.fetch()
				.one()
				.then();
	}

	@Override
	public Mono<Void> deleteById(final String id) {

		log.debug("delete user by id {}", id);

		return getDatabaseClient()
				.execute()
				.sql("DELETE FROM users WHERE login = $1")
				.bind(0, id)
				.fetch()
				.one()
				.then();
	}

	@Override
	public void init() {

		try {
			log.debug("init db");

			final ClassPathResource resource = new ClassPathResource("data/users.json");
			List<User> users = getObjectMapper().readValue(resource.getInputStream(), new TypeReference<List<User>>() {});

			getDatabaseClient()
					.execute()
					.sql("CREATE TABLE IF NOT EXISTS users (login varchar PRIMARY KEY, firstname varchar, lastname varchar);")
					.then()
					.thenMany(
							saveAll(
									Flux.fromIterable(users)
							)
					).subscribe();


		} catch (IOException e) {
			log.error("Error occurred initializing db: {}", e.getMessage());
		}
	}

}
