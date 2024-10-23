package com.dollop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class BloggingAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApiApplication.class, args);
	}

}
