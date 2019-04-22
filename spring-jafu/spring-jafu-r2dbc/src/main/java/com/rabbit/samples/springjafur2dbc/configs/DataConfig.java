package com.rabbit.samples.springjafur2dbc.configs;

import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import com.rabbit.samples.springjafur2dbc.repos.impl.UserRepositoryImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.fu.jafu.ConfigurationDsl;

import java.util.function.Consumer;

import static org.springframework.fu.jafu.r2dbc.H2R2dbcDsl.r2dbcH2;
import static org.springframework.fu.jafu.r2dbc.PostgresqlR2dbcDsl.r2dbcPostgresql;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class DataConfig {

	public static Consumer<ConfigurationDsl> dataConfig =
			configurationDsl ->
					configurationDsl
							.beans(
									beanDefinitionDsl ->
											beanDefinitionDsl.bean(UserRepositoryImpl.class)
							)
							.enable(
									configurationDsl.profiles().contains("postgres")
											? r2dbcPostgresql()
											: r2dbcH2()
							)
							.listener(
									ApplicationReadyEvent.class,
									applicationEvent ->
											configurationDsl.ref(UserRepository.class).init()
							)
			;

}
