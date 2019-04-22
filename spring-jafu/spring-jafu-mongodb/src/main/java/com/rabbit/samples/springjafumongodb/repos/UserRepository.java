package com.rabbit.samples.springjafumongodb.repos;

import com.mongodb.client.result.DeleteResult;
import com.rabbit.samples.springjafumongodb.domain.User;
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

	Mono<DeleteResult> deleteAll();

	Mono<DeleteResult> deleteById(final String id);

	void init();

}
