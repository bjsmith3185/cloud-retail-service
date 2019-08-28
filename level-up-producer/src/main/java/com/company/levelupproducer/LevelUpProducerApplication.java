package com.company.levelupproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LevelUpProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevelUpProducerApplication.class, args);
	}

}
