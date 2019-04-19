package com.rabbit.samples.springjafuminimal.handlers;

import com.rabbit.samples.springjafuminimal.domain.Sample;
import com.rabbit.samples.springjafuminimal.services.SampleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 20 Apr 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class SampleHandler {

	SampleService sampleService;

	public Mono<ServerResponse> hello(ServerRequest request) {

		log.info("hello request");

		return ok().syncBody(
				getSampleService().generateMessage()
		);
	}

	public Mono<ServerResponse> json(ServerRequest request) {

		log.info("json request");

		return ok().syncBody(
				Sample.builder()
						.message(getSampleService().generateMessage())
						.build()
		);
	}

}
