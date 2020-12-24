package com.revature.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
	
	public void sendEmails(String msg,String destination) throws AddressException, MessagingException;

}
