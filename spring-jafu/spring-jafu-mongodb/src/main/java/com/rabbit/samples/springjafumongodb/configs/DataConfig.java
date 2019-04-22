package com.rabbit.samples.springjafumongodb.configs;

import com.rabbit.samples.springjafumongodb.repos.UserRepository;
import com.rabbit.samples.springjafumongodb.repos.impl.UserRepositoryImpl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.fu.jafu.ConfigurationDsl;
import org.springframework.fu.jafu.mongo.ReactiveMongoDsl;

import java.util.function.Consumer;

import static org.springframework.fu.jafu.mongo.ReactiveMongoDsl.reactiveMongo;


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
									reactiveMongo()
									// reactiveMongo(ReactiveMongoDsl::embedded)
							)
							.listener(
									ApplicationReadyEvent.class,
									applicationEvent ->
											configurationDsl.ref(UserRepository.class).init()
							)
			;

}
