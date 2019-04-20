package com.rabbit.samples.springjafur2dbc.configs;

import com.rabbit.samples.springjafur2dbc.handlers.UserHandler;
import com.rabbit.samples.springjafur2dbc.handlers.impl.UserHandlerImpl;
import org.springframework.fu.jafu.ConfigurationDsl;

import java.util.function.Consumer;

import static org.springframework.fu.jafu.webflux.WebFluxServerDsl.webFlux;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class WebConfig {

	public static Consumer<ConfigurationDsl> webConfig =
			configurationDsl ->
					configurationDsl
							.beans(
									beanDefinitionDsl ->
											beanDefinitionDsl.bean(UserHandlerImpl.class)
							)
							.enable(
									webFlux(
											webFluxServerDsl -> {
												webFluxServerDsl
														.port(
																webFluxServerDsl.profiles().contains("test") ? 8181 : 8080
														)
														.router(
																routerBuilder -> {
																	UserHandler userHandler = configurationDsl.ref(UserHandler.class);
																	routerBuilder
																			// .HEAD("/users", userHandler::count)
																			.GET("/users", userHandler::findAll)
																			.GET("/users/{id}", userHandler::findById)
																			.POST("/users", userHandler::save)
																			.DELETE("/users", userHandler::deleteAll)
																	;
																}
														)
														.codecs(
																webFluxServerCodecDsl ->
																		webFluxServerCodecDsl.string().jackson()
														);
											}
									)
							);

}
