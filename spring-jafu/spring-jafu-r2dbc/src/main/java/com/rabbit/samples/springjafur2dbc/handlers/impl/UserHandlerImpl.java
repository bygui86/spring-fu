package com.rabbit.samples.springjafur2dbc.handlers.impl;

import com.rabbit.samples.springjafur2dbc.domain.User;
import com.rabbit.samples.springjafur2dbc.handlers.UserHandler;
import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
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
public class UserHandlerImpl implements UserHandler {

	private final UserRepository userRepository;

	public UserHandlerImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Mono<ServerResponse> findAll(final ServerRequest request) {

		return ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(userRepository.findAll(), User.class);
	}

	@Override
	public Mono<ServerResponse> save(final ServerRequest request) {

		// return ServerResponse.ok()
		// 		.contentType(MediaType.APPLICATION_JSON_UTF8)
		// 		.body(userRepository.save(request.bodyToMono(User.class)), String.class);
		//
		// final Mono<User> mono = request.bodyToMono(User.class)

		// ok().build()

		return null;
	}

}
