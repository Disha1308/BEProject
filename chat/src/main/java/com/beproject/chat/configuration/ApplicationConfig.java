package com.beproject.chat.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({ "com.beproject.chat.rest", "com.beproject.chat.service" })
@EntityScan(basePackages = {"com.beproject.chat.models" })
@EnableJpaRepositories(basePackages = "com.beproject.chat.models")
public class ApplicationConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

}