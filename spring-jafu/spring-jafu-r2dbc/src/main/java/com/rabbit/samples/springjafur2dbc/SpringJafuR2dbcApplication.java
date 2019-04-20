package com.rabbit.samples.springjafur2dbc;


import com.rabbit.samples.springjafur2dbc.configs.DataConfig;
import com.rabbit.samples.springjafur2dbc.configs.WebConfig;
import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.reactiveWebApplication;


public class SpringJafuR2dbcApplication {

	private static JafuApplication jafuApplication = reactiveWebApplication(

			applicationDsl ->
					applicationDsl
							.enable(DataConfig.dataConfig)
							.enable(WebConfig.webConfig)
							.listener(
									ApplicationReadyEvent.class,
									applicationEvent ->
											applicationDsl.ref(UserRepository.class).init()
							)
	);

	public static void main (String[] args) {

		jafuApplication.run(args);
	}

}
