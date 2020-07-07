package com.revature.service;

import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Used for sending email's to the Revature training associates
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
@Service
public class EmailService {
	
	/**An instance of a Session for sending out email's
	 * 
	 */
	private Session session;
	
	/**
	 * Sets up properties of EmailService to achieve connection
	 */
	public EmailService() {
		super();
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "25");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		this.session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication("trmsloginserver@gmail.com", "phkitjrbotxdrdwh");
		    }
		});  
	}

	/**Sends an email to a particular email address
	 * @param msg contents of the email 
	 * @param destination email address
	 * @throws AddressException exception thrown when a wrongly formatted address is encountered.
	 * @throws MessagingException base class for all exceptions thrown by the Messaging classes
	 */
	public void sendEmails(String msg,String destination) throws AddressException, MessagingException
	{
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("trmsloginserver@gmail.com"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destination));
		message.setSubject("Please Fill Out The QC Survey");
		 
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");
		 
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		 
		message.setContent(multipart);
		 
		Transport.send(message);
	}
}