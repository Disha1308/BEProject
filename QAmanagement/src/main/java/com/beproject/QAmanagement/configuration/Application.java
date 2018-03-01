package com.beproject.QAmanagement.configuration;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan({ "com.beproject.QAmanagement.service", "com.beproject.QAmanagement.rest" })
@EntityScan(basePackages = { "com.beproject.QAmanagement.models" })
@EnableJpaRepositories(basePackages = "com.beproject.QAmanagement.repository")
public class Application {
	
	@Named
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages("com.beproject.QAmanagement.rest");
		}
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}