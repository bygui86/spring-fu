package com.rabbit.samples.springjafumongodb.domain;

/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 16 Feb 2019
 */
public class Sample {

	private String message;

	public Sample() { }

	public Sample(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
