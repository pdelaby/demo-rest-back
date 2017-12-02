package com.pdy.fac.demorestback.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@ComponentScan(basePackages = { "com.pdy.fac.demorestback"})
@EntityScan(basePackages = "com.pdy.fac.demorestback.*.repository.entities")
@Configuration
@EnableJpaRepositories(
		basePackages = {"com.pdy.fac.demorestback.*.repository"}		
		)
@EnableAutoConfiguration
@SpringBootApplication
// tenter https://stackoverflow.com/questions/28664064/spring-boot-not-an-managed-type
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}