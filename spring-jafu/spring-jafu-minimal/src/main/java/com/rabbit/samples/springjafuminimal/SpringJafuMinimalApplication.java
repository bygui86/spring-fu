package com.rabbit.samples.springjafuminimal;


import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.webApplication;
import static org.springframework.fu.jafu.web.WebFluxServerDsl.server;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


public class SpringJafuMinimalApplication {

	private static JafuApplication jafuApplication = webApplication(

			applicationDsl ->
					applicationDsl.enable(
							server(
									webFluxServerDsl ->
											webFluxServerDsl.router(
													routerBuilder ->
															routerBuilder
																	.GET("/", request -> ok().syncBody("Hello world!"))
									)
							)
					)
	);

	public static void main (String[] args) {

		jafuApplication.run(args);
	}

}
