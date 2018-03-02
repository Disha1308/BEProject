package com.beproject.topicmanagement.configuration;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({ "com.beproject.topicmanagement.service", "com.beproject.topicmanagement.rest" })
@EntityScan(basePackages = { "com.beproject.topicmanagement.models" })
@EnableJpaRepositories(basePackages = "com.beproject.topicmanagement.repository")
public class ApplicationConfig {

	@Named
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages("com.beproject.topicmanagement.rest");
		}
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

}