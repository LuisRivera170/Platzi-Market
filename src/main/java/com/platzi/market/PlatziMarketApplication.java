package com.platzi.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PlatziMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatziMarketApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Hola platzi 🚀";
	}
}
