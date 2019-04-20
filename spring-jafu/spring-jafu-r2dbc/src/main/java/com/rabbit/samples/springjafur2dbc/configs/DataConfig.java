package com.rabbit.samples.springjafur2dbc.configs;

import com.rabbit.samples.springjafur2dbc.repos.UserRepository;
import com.rabbit.samples.springjafur2dbc.repos.impl.UserRepositoryImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.fu.jafu.ConfigurationDsl;

import java.util.function.Consumer;

import static org.springframework.fu.jafu.r2dbc.H2R2dbcDsl.r2dbcH2;


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
							.enable(r2dbcH2())
			;

}
