package com.renatoav.hardwired;

import com.renatoav.hardwired.entity.Cliente;
import com.renatoav.hardwired.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

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
