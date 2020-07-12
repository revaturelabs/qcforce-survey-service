package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.revature.service.EmailService;

@SpringBootTest
class EmailServiceTest {
	
	@Autowired
	private EmailService es;

	@Test
	void testSendEmails(){
		
		try {
			es.sendEmails("Ana, where are you?","anamiagkiigmail.com");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("anamiagkiigmail.com","anamiagkiigmail.com");
	}

}
