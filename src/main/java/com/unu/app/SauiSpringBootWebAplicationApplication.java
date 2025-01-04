package com.unu.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.unu.app.entity") 
@EnableJpaRepositories("com.unu.app.repository")
public class SauiSpringBootWebAplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SauiSpringBootWebAplicationApplication.class, args);
	}

}
