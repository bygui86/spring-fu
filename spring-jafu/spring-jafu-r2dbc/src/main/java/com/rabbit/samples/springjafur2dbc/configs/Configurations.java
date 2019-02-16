package com.rabbit.samples.springjafur2dbc.configs;

import com.rabbit.samples.springjafur2dbc.handlers.UserHandler;
import com.rabbit.samples.springjafur2dbc.handlers.impl.UserHandlerImpl;
import com.rabbit.samples.springjafur2dbc.repos.impl.UserRepositoryImpl;
import org.springframework.fu.jafu.ConfigurationDsl;

import java.util.function.Consumer;

import static org.springframework.fu.jafu.r2dbc.H2R2dbcDsl.r2dbcH2;
import static org.springframework.fu.jafu.web.WebFluxServerDsl.server;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class Configurations {

	public static Consumer<ConfigurationDsl> dataConfig =
			configurationDsl -> configurationDsl
					.beans(
							beanDefinitionDsl ->
									beanDefinitionDsl
											.bean(UserRepositoryImpl.class)
					)
					.enable(r2dbcH2());

	public static Consumer<ConfigurationDsl> webConfig =
			configurationDsl -> configurationDsl
					.beans(
							beanDefinitionDsl ->
									beanDefinitionDsl
											.bean(UserHandlerImpl.class)
					)
					.enable(
							server(
									webFluxServerDsl ->
											webFluxServerDsl
													.port(
															webFluxServerDsl.profiles().contains("test") ? 8181 : 8080
													)
													.router(
															routerBuilder -> {
																	UserHandler userHandler = configurationDsl.ref(UserHandler.class);
																	routerBuilder.GET("/", userHandler::findAll);
															}
													)
													.codecs(
															webFluxServerCodecDsl ->
																	webFluxServerCodecDsl
																			.string()
																			.jackson()
													)
							)
					);

}
