package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * QCForce Survey Service Application Main Class
 * 
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
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
