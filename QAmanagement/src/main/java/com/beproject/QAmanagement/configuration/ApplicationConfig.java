package com.beproject.QAmanagement.configuration;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({ "com.beproject.QAmanagement.service", "com.beproject.QAmanagement.rest", "com.beproject.QAmanagement.dto" })
@EntityScan(basePackages = { "com.beproject.QAmanagement.models" })
@EnableJpaRepositories(basePackages = "com.beproject.QAmanagement.repository")
public class ApplicationConfig {

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

}