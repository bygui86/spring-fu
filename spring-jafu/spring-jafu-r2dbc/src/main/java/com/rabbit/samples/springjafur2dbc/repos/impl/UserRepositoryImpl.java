package com.rabbit.samples.springjafur2dbc.repos.impl;

import com.rabbit.samples.springjafur2dbc.domain.User;
import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import org.springframework.data.r2dbc.function.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class UserRepositoryImpl implements UserRepository {

	private final DatabaseClient client;

	public UserRepositoryImpl(DatabaseClient client) {
		this.client = client;
	}

	@Override
	public Mono<Integer> count() {
		return client.execute().sql("SELECT COUNT(*) FROM users").as(Integer.class).fetch().one();
	}

	@Override
	public Flux<User> findAll() {
		return client.select().from("users").as(User.class).fetch().all();
	}

	@Override
	public Mono<User> findOne(String id) {
		return client.execute().sql("SELECT * FROM users WHERE login = $1").bind(1, id).as(User.class).fetch().one();
	}

	@Override
	public Mono<Void> deleteAll() {
		return client.execute().sql("DELETE FROM users").fetch().one().then();
	}

	@Override
	public Mono<String> save(User user) {
		return client.insert().into(User.class).table("users").using(user)
				.map((r, m) -> r.get("login", String.class)).one();
	}

	@Override
	public void init() {
		client.execute().sql("CREATE TABLE IF NOT EXISTS users (login varchar PRIMARY KEY, firstname varchar, lastname varchar);").then()
				.then(deleteAll())
				.then(save(new User("johnd", "John","Doe")))
				.then(save(new User("janed", "Jane","Doe")))
				.then(save(new User("mattb", "Matteo","Baiguini")))
				.block();
	}

}
