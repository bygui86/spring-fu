package com.rabbit.samples.springjafumongodb.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public interface SampleHandler {

	Mono<ServerResponse> helloString(final ServerRequest request);

	Mono<ServerResponse> helloJson(ServerRequest request);

}
