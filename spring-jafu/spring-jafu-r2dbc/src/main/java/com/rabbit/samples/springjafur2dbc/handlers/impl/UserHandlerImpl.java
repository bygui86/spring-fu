package com.rabbit.samples.springjafur2dbc.handlers.impl;

import com.rabbit.samples.springjafur2dbc.domain.User;
import com.rabbit.samples.springjafur2dbc.handlers.UserHandler;
import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class UserHandlerImpl implements UserHandler {

	private final UserRepository repository;

	public UserHandlerImpl(final UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public Mono<ServerResponse> findAll(final ServerRequest request) {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(repository.findAll(), User.class);
	}

}
