package com.rabbit.samples.springjafumongodb.handlers.impl;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.rabbit.samples.springjafumongodb.domain.Sample;
import com.rabbit.samples.springjafumongodb.handlers.SampleHandler;
import com.rabbit.samples.springjafumongodb.services.SampleService;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class SampleHandlerImpl implements SampleHandler {

	private SampleService sampleService;

	public SampleHandlerImpl(SampleService sampleService) {

		this.sampleService = sampleService;
	}

	@Override
	public Mono<ServerResponse> helloString(final ServerRequest request) {

		return ok().syncBody(sampleService.generateMessage());
	}

	@Override
	public Mono<ServerResponse> helloJson(final ServerRequest request) {

		return ok().syncBody(new Sample(sampleService.generateMessage()));
	}
}
