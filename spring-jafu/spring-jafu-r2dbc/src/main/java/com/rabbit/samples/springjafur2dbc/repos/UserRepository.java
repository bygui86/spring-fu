package com.rabbit.samples.springjafur2dbc.repos;

import com.rabbit.samples.springjafur2dbc.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public interface UserRepository {

	Flux<User> findAll();

	Mono<User> findById(final String id);

	Flux<User> saveAll(final Flux<User> users);

	Mono<User> save(final Mono<User> user);

	Mono<Void> deleteAll();

	Mono<Void> deleteById(final String id);

	void init();

}
