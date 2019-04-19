#!/bin/sh

# GRADLE
#gradlew clean build -Pgraal=true
#unzip build/libs/spring-jafu-minimal.jar -d build/libs/spring-jafu-minimal
#native-image -H:IncludeResources='META-INF/.*.json|META-INF/spring.factories|org/springframework/boot/logging/.*' --allow-incomplete-classpath --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,org.springframework.core.io.VfsUtils,org.springframework.format.support.DefaultFormattingConversionService -H:ReflectionConfigurationFiles=graal/app.json,graal/boot.json,graal/framework.json,graal/log4j.json,graal/netty.json -Dio.netty.noUnsafe=true -H:+ReportUnsupportedElementsAtRuntime -Dfile.encoding=UTF-8 -cp ".:$(echo build/libs/spring-jafu-minimal/BOOT-INF/lib/*.jar | tr ' ' ':')":build/libs/spring-jafu-minimal/BOOT-INF/classes com.rabbit.samples.springjafuminimal.SpringJafuMinimalApplication

# MAVEN
mvnw clean package
unzip target/spring-jafu-minimal.jar -d target/spring-jafu-minimal
native-image -H:IncludeResources='META-INF/.*.json|META-INF/spring.factories|org/springframework/boot/logging/.*' --allow-incomplete-classpath --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,org.springframework.core.io.VfsUtils,org.springframework.format.support.DefaultFormattingConversionService -H:ReflectionConfigurationFiles=graal/app.json,graal/boot.json,graal/framework.json,graal/log4j.json,graal/netty.json -Dio.netty.noUnsafe=true -H:+ReportUnsupportedElementsAtRuntime -Dfile.encoding=UTF-8 -cp ".:$(echo target/spring-jafu-minimal/BOOT-INF/lib/*.jar | tr ' ' ':')":target/spring-jafu-minimal/BOOT-INF/classes com.rabbit.samples.springjafuminimal.SpringJafuMinimalApplication
