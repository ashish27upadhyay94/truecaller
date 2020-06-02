package com.truecaller.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
@EnableCaching
public class TrueCallerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(TrueCallerApplication.class, args);
		
	
		
	}

}
