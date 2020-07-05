package com.revature.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.service.EmailService;

@Component
public class EmailScheduler {
	private EmailService emailService;
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	/*
	public List<String> recieveEmailList() {
		WebClient emails = WebClient.create("");
		return emails.get().uri("").retrieve().toEntityList(String.class).block().getBody();

      
	}
	*/
	
	/*
	//@Scheduled(fixedDelay = 30000)
	public void sendmail() {
		List<String> emailList = recieveEmailList();
		
		for (String email : emailList) {
			//emailService.sendMail(email, "Upcoming QC event",
			//		"Please complete QC form. Link is down below \n http://www.google.com");
			System.out.println(emailList);
		}
	}
	*/
}
