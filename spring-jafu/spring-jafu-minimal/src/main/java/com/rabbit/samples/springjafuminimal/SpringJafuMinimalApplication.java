package com.rabbit.samples.springjafuminimal;


import com.rabbit.samples.springjafuminimal.handlers.SampleHandler;
import com.rabbit.samples.springjafuminimal.services.SampleService;
import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.reactiveWebApplication;
import static org.springframework.fu.jafu.webflux.WebFluxServerDsl.webFlux;

public class SpringJafuMinimalApplication {

	static JafuApplication jafuApplication =
			reactiveWebApplication(
					applicationDsl -> {
							applicationDsl.beans(
									beanDefinitionDsl -> {
											beanDefinitionDsl.bean(SampleService.class);
											beanDefinitionDsl.bean(SampleHandler.class);
									}
							);

							applicationDsl.enable(
									webFlux(
											webFluxServerDsl -> {
													webFluxServerDsl.port(webFluxServerDsl.profiles().contains("test") ? 8181 : 8080);
													webFluxServerDsl.router(
															routerBuilder -> {
																	SampleHandler handler = webFluxServerDsl.ref(SampleHandler.class);
																	routerBuilder.GET("/", handler::hello);
																	routerBuilder.GET("/api", handler::json);
															}
													);

													webFluxServerDsl.codecs(
															webFluxServerCodecDsl -> {
																	webFluxServerCodecDsl.string();
																	webFluxServerCodecDsl.jackson();
															}
													);
											}
									)
							);
					}
			);

	public static void main (String[] args) {

		jafuApplication.run(args);
	}

}
