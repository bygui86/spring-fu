package com.rabbit.samples.springjafumongodb.repos.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.DeleteResult;
import com.rabbit.samples.springjafumongodb.domain.User;
import com.rabbit.samples.springjafumongodb.repos.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;


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

	ReactiveMongoOperations reactiveMongoOperations;

	ObjectMapper objectMapper;

	@Override
	public Flux<User> findAll() {

		log.debug("find all users");

		return getReactiveMongoOperations()
				.findAll(User.class);
	}

	@Override
	public Mono<User> findById(final String id) {

		log.debug("find user by id {}", id);

		return getReactiveMongoOperations()
				.findById(id, User.class);
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

		log.info("save user {}", user);

		return getReactiveMongoOperations()
				.save(user)
		;
	}

	@Override
	public Mono<DeleteResult> deleteAll() {

		log.debug("delete all users");

		return getReactiveMongoOperations()
				.remove(User.class)
				.all()
		;
	}

	@Override
	public Mono<DeleteResult> deleteById(final String id) {

		log.debug("delete user by id {}", id);

		return getReactiveMongoOperations()
				.remove(
						Query.query(Criteria.where("login").is(id)),
						User.class
				)
		;
	}

	@Override
	public void init() {

		try {
			log.debug("init db");

			deleteAll().block();

			final ClassPathResource resource = new ClassPathResource("data/users.json");
			List<User> users = getObjectMapper().readValue(resource.getInputStream(), new TypeReference<List<User>>() {});
			saveAll(Flux.fromIterable(users)).subscribe();
		} catch (IOException e) {
			log.error("Error occurred initializing db: {}", e.getMessage());
		}
	}

}
