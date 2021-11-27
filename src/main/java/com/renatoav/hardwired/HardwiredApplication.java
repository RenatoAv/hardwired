package com.renatoav.hardwired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HardwiredApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwiredApplication.class, args);
	}

//	@Bean
//	CommandLineRunner cli(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
//		return cli -> {
//			clienteRepository.save(new Cliente("a", "a", "a", passwordEncoder.encode("a"), "ROLE_USER"));
//		};
//	}

}
