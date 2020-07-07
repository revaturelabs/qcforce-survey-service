package com.revature.scheduler;

import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.revature.service.EmailService;
import reactor.core.publisher.Mono;

/**
 * Used for scheduling email's to the Revature training associates
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
@Component
public class EmailScheduler {
	
	/**Instance of an EmailService
	 * 
	 */
	private EmailService emailService;
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	/**Sends out email's to active training associates given a particular schedule
	 * 
	 */
	@Scheduled(cron = "0 0 10 * * MON")
	public void sendMailScheduler() {
		
		System.out.println("Scheduler ran");
				recieveEmailList().subscribe(x -> {
					sendMail(x);
				});	
	}
	
	/**Retrieves the list of email's for all active training associates
	 * @return {@link Mono}{@link List}{@link String} of email's
	 */
	public Mono<List<String>> recieveEmailList() {
		WebClient emails = WebClient.create("http://ec2-18-219-219-28.us-east-2.compute.amazonaws.com:8086");
	return emails.get().uri("/associate/active-emails").retrieve().bodyToMono(new ParameterizedTypeReference<List<String>>() {});

	}
	/**Sends out an email containing the link to the QC Survey to all the active training associates
	 * @param data email addresses of all active associates
	 */
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
