package com.shop.user;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public GroupedOpenApi swaggerConfig() {
		return GroupedOpenApi.builder()
			.group("Server-APIs")
			.pathsToMatch("/user/**")
			.build();
	}

	@Bean
	public OpenAPI swaggerApiInfoConfig(){
		return new OpenAPI()
		.info(new Info().title("User Microservice")
		.description("Java Rest API Microservice Application")
		.license(new License().name("GitHub").url("https://github.com/kankshini2000")));
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/user").allowedOrigins("http://localhost:9005");
			}
		};
	}

}
