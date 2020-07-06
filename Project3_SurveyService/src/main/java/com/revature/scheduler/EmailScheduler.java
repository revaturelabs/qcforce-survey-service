package com.revature.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.service.EmailService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EmailScheduler {
	
	private EmailService emailService;
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@Scheduled(cron = "0 0 10 * * MON")
	public void sendMailScheduler() {
		//TODO:Ensure that we can send emails in bulk
		System.out.println("Scheduler ran");
				recieveEmailList().subscribe(x -> {
					sendMail(x);
				});	
	}
	
	public void sendMailSchedulerVersionTwo() {
		if(LocalDate.now().getDayOfWeek().getValue()==1) {
			if(LocalDateTime.now().getHour() == 10) {
				recieveEmailList().subscribe(x -> {
					sendMail(x);
				});
			}else {
				System.out.print("Did not send email but the time check works");
			}
		}
	}
	public Mono<List<String>> recieveEmailList() {
		WebClient emails = WebClient.create("http://ec2-18-219-219-28.us-east-2.compute.amazonaws.com:8086");
	return emails.get().uri("/associate/active-emails").retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {});

	}
	public void sendMail(List<String> data) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("emailList: "+ data);
		for (String email : data) {
			System.out.println("Individual Email: "+ email);
			try {
				emailService.sendEmails("Please complete the following QC form: <br> <a href=\"https://docs.google.com/forms/d/e/1FAIpQLSctgsH-__acrraIWMPDsV3XSFmTAujJNIxK9zKEhATsYsKHSw/viewform?usp=send_form\">Survey Link</a>", email);
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
}
