package com.rabbit.samples.springjafumongodb;


import com.rabbit.samples.springjafumongodb.handlers.SampleHandler;
import com.rabbit.samples.springjafumongodb.handlers.impl.SampleHandlerImpl;
import com.rabbit.samples.springjafumongodb.services.impl.SampleServiceImpl;
import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.webApplication;
import static org.springframework.fu.jafu.web.WebFluxServerDsl.server;


public class SpringJafuMongoDbApplication {

	private static JafuApplication jafuApplication = webApplication(

			applicationDsl ->
					applicationDsl
							.beans(
									beanDefinitionDsl ->
											beanDefinitionDsl
													.bean(SampleHandlerImpl.class)
													.bean(SampleServiceImpl.class)
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
																			SampleHandler sampleHandler = webFluxServerDsl.ref(SampleHandler.class);
																			routerBuilder.GET("/", sampleHandler::helloString);
																			routerBuilder.GET("/api", sampleHandler::helloJson);
																	}
															)
															.codecs(
																	webFluxServerCodecDsl ->
																			webFluxServerCodecDsl
																					.string()
																					.jackson()
															)
									)
							)
	);

	public static void main (String[] args) {

		jafuApplication.run(args);
	}

}
