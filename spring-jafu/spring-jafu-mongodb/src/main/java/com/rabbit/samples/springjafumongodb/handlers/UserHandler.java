package com.rabbit.samples.springjafumongodb.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public interface UserHandler {

	Mono<ServerResponse> findAll(final ServerRequest request);

	Mono<ServerResponse> findById(final ServerRequest request);

	Mono<ServerResponse> saveAll(final ServerRequest request);

	Mono<ServerResponse> save(final ServerRequest request);

	Mono<ServerResponse> deleteAll(final ServerRequest request);

	Mono<ServerResponse> deleteById(final ServerRequest request);

}
