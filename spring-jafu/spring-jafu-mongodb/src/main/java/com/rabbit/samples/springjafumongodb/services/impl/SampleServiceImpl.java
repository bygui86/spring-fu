package com.rabbit.samples.springjafumongodb.services.impl;

import com.rabbit.samples.springjafumongodb.services.SampleService;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class SampleServiceImpl implements SampleService {

	@Override
	public String generateMessage() {

		return "Hello world!";
	}
}
