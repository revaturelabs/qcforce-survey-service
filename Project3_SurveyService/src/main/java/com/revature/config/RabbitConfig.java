package com.revature.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ main configurations used through out the application.
*@author Anastasia Miagkii, Andres Toledo, Jose Canela, Monica Datta, Wei Wu, Zachary Reagin
*/

@Configuration
public class RabbitConfig {
	
	@Bean
	MessageConverter msgConverter() {
		return new Jackson2JsonMessageConverter();
	}

}
