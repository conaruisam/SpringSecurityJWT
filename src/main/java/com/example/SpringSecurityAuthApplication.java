package com.example;

import com.example.entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityAuthApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAuthApplication.class, args);
	}

	/*
	CommandLineRunner init() {
		return args -> {
			UserEntity user = UserEntity.builder()
					.email("conaruisam@gmail.com")
					.username("isam")
					.
					.build()
		}S

	 */

}
