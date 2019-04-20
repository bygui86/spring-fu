package com.rabbit.samples.springjafur2dbc.repos.impl;

import com.rabbit.samples.springjafur2dbc.domain.User;
import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.function.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class UserRepositoryImpl implements UserRepository {

	DatabaseClient databaseClient;

	@Override
	public Mono<Long> count() {

		log.debug("count users");

		return getDatabaseClient()
				.execute()
				.sql("SELECT COUNT(*) FROM users")
				.as(Long.class)
				.fetch()
				.one();
	}

	@Override
	public Flux<User> findAll() {

		log.debug("find all users");

		return getDatabaseClient()
				.select()
				.from("users")
				.as(User.class)
				.fetch()
				.all();
	}

	@Override
	public Mono<User> findById(final String id) {

		log.debug("find user by id {}", id);

		return getDatabaseClient()
				.execute()
				.sql("SELECT * FROM users WHERE login = $1")
				.bind(0, id)
				.as(User.class)
				.fetch()
				.one();
	}

	@Override
	public Mono<String> save(final User user) {

		log.debug("save user {}", user);

		return getDatabaseClient()
				.insert()
				.into(User.class)
				.table("users")
				.using(user)
				.map((r, m) -> r.get("login", String.class))
				.one();
	}

	@Override
	public Mono<Void> deleteAll() {

		log.debug("delete all users");

		return getDatabaseClient()
				.execute()
				.sql("DELETE FROM users")
				.fetch()
				.one()
				.then();
	}

	@Override
	public void init() {

		log.debug("init db");

		getDatabaseClient()
				.execute()
				.sql("CREATE TABLE IF NOT EXISTS users (login varchar PRIMARY KEY, firstname varchar, lastname varchar);")
				.then()
				.then(deleteAll())
				.then(save(new User("johnd", "John","Doe")))
				.then(save(new User("janed", "Jane","Doe")))
				.then(save(new User("mattb", "Matteo","Baiguini")))
				.block();
	}

}
