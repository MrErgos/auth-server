package com.ergos.auth_server;

import com.ergos.auth_server.model.User;
import com.ergos.auth_server.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	//Create users for test
	@Bean
	@Profile("!prod")
	public ApplicationRunner dataLoader(UserRepository userRepository,
										PasswordEncoder passwordEncoder) {
		return args -> {
			userRepository.save(
					new User("yappy", passwordEncoder.encode("yappy"), "ROLE_USER"));
			userRepository.save(
					new User("chef", passwordEncoder.encode("chef"), "ROLE_ADMIN"));
		};
	}
}
