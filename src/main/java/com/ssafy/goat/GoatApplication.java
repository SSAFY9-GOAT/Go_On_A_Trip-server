package com.ssafy.goat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GoatApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoatApplication.class, args);
	}

}
