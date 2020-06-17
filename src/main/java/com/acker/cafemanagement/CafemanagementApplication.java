package com.acker.cafemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CafemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafemanagementApplication.class, args);
	}
	
	//Setting up Global CORS filter to expose spring apis to angular
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("*")//TODO: dangerous to leave api open to all make it localhost:4200 only
				.allowedHeaders("*");
			}
		};
	
	}
	
	
	//eoc

}
