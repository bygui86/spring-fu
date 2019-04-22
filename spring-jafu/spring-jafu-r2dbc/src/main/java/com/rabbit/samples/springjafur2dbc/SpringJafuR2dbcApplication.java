package com.rabbit.samples.springjafur2dbc;


import com.rabbit.samples.springjafur2dbc.configs.DataConfig;
import com.rabbit.samples.springjafur2dbc.configs.WebConfig;
import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.reactiveWebApplication;


public class SpringJafuR2dbcApplication {

	private static JafuApplication jafuApplication = reactiveWebApplication(

			applicationDsl ->
					applicationDsl
							.enable(DataConfig.dataConfig)
							.enable(WebConfig.webConfig)
	);

	public static void main (String[] args) {

		jafuApplication.run(args);
	}

}
