
# Spring Fu sample projects

## Sub-projects

Spring Fu sample projects:
* Jafu
	* Minimal
	* MongoDB - `WORK IN PROGRESS`
	* R2DBC
* Kofu
	* Minimal - `TODO`
	* MongoDB - `TODO`
	* R2DBC - `TODO`

---

## Prerequisites

* Maven pom
	* Spring Reactive Web dependency 
	* Spring Milestones repository
	* Spring Fu dependency (current version 0.0.5)

---

## Run sample

Start application
```
cd <PROJECT_ROOT_FOLDER>/<SUB_PROJECT_FOLDER>
mvnw clean package spring-boot:run
```

---

## TODOs

* introduce Lombok
* natively compile with GraalVM

---

## Links

* https://github.com/spring-projects/spring-fu
* https://spring.io/blog/2018/10/02/the-evolution-of-spring-fu

### Jafu

* https://github.com/spring-projects/spring-fu/blob/master/jafu/README.adoc
* https://github.com/spring-projects/spring-fu/tree/master/samples/jafu-reactive-minimal
* https://github.com/spring-projects/spring-fu/tree/master/samples/jafu-reactive-r2dbc

### Kofu

* https://github.com/spring-projects/spring-fu/blob/master/kofu/README.adoc
* https://github.com/spring-projects/spring-fu/tree/master/samples/kofu-reactive-minimal
* https://github.com/spring-projects/spring-fu/tree/master/samples/kofu-reactive-r2dbc
* https://github.com/spring-projects/spring-fu/tree/master/samples/kofu-reactive-mongodb
* https://github.com/spring-projects/spring-fu/tree/master/samples/kofu-coroutines-r2dbc
* https://github.com/spring-projects/spring-fu/tree/master/samples/kofu-coroutines-mongodb

### APT plugin demo

* https://github.com/dsyer/spring-init-experiment
