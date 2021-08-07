package com.ifpb.ifpbtvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EntityScan("com.ifpb.ifpbtvapi.model") // path of the entity model
@EnableJpaRepositories("com.ifpb.ifpbtvapi.repository") // path of jpa repository 

@SpringBootApplication
public class IfpbtvApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfpbtvApiApplication.class, args);
	}

}
