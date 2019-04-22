package com.rabbit.samples.springjafumongodb;


import com.rabbit.samples.springjafumongodb.configs.DataConfig;
import com.rabbit.samples.springjafumongodb.configs.WebConfig;
import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.reactiveWebApplication;


public class SpringJafuMongoDbApplication {

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
