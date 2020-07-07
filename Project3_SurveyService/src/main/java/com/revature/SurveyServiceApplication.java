package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * QCForce Survey Service Application Main Class
 * 
 * @author Anastasia Miagkii, Andres Toledo, Jose Canela, Monica Datta, Wei Wu, Zachary Reagin
 */

@SpringBootApplication()
@EnableScheduling
public class SurveyServiceApplication {
	/**
	 * @param args input parameters passed in when running the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SurveyServiceApplication.class, args);
	}
}
