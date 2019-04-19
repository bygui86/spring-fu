package com.rabbit.samples.springjafuminimal.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


/**
 * @author Matteo Baiguini
 * matteo@solidarchitectures.com
 * 20 Apr 2019
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class Sample {

	String message;

}
