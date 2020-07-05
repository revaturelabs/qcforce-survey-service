package com.revature;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class Project3Application {

//	@Autowired
//	private FormResponseRepo repository;
//
//	@Autowired
//	private FormRepo formRepository;

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);
	}

}
