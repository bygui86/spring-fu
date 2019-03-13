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

	Mono<Integer> count();

	Flux<User> findAll();

	Mono<User> findById(final String id);

	Mono<Void> deleteAll();

	Mono<String> save(User user);

	void init();

}
