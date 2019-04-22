package com.rabbit.samples.springjafumongodb.handlers.impl;

import com.mongodb.client.result.DeleteResult;
import com.rabbit.samples.springjafumongodb.domain.User;
import com.rabbit.samples.springjafumongodb.handlers.UserHandler;
import com.rabbit.samples.springjafumongodb.repos.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class UserHandlerImpl implements UserHandler {

	UserRepository userRepository;

	@Override
	public Mono<ServerResponse> findAll(final ServerRequest request) {

		log.info("find all users");

		return ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(
						getUserRepository().findAll(),
						User.class
				);
	}

	@Override
	public Mono<ServerResponse> findById(final ServerRequest request) {

		String id = request.pathVariable("id");

		log.info("find user by id {}", id);

		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						getUserRepository().findById(id),
						User.class
				);
	}

	@Override
	public Mono<ServerResponse> saveAll(final ServerRequest request) {

		log.info("save user");

		return ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(
						getUserRepository().saveAll(request.bodyToFlux(User.class)),
						User.class
				);
	}

	@Override
	public Mono<ServerResponse> save(final ServerRequest request) {

		log.info("save user");

		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						getUserRepository().save(request.bodyToMono(User.class)),
						User.class
				);
	}

	@Override
	public Mono<ServerResponse> deleteAll(final ServerRequest request) {

		log.info("delete all users");

		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						getUserRepository().deleteAll(),
						DeleteResult.class
				);
	}

	@Override
	public Mono<ServerResponse> deleteById(final ServerRequest request) {

		String id = request.pathVariable("id");

		log.info("delete user by id {}", id);

		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(
						getUserRepository().deleteById(id),
						DeleteResult.class
				);
	}

}
