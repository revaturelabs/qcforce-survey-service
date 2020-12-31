package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revature.response.EmailResponse;

/**
 * This service receives requests from the distribution controller, and then
 * validates if the emails are correctly formatted and the batch id exists. If
 * the emails are invalid then the invalid emails will be returned. If the batch
 * id does not exist then an exception is thrown.
 */

@Service
public class DistributionServiceImpl implements DistributionService {

	EmailService emailService;

	CSVParser csvParser;

	AuthService authService;

	public final String baseURL = "http://qcforce.com";

	/**
	 * @param emailService the emailService to set
	 */
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * @param csvParser the csvParser to set
	 */
	@Autowired
	public void setCsvParser(CSVParser csvParser) {
		this.csvParser = csvParser;
	}

	/**
	 * @param authService the authService to set
	 */
	@Autowired
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * Sends emails by batch Id takes survey id calls email service with batch Id emails 
	 * received from messaging queue
	 * and calls auth service with surveyId.
	 * @param batchId - identifies the batch to recieve emails from
	 * @parm surveyId - identifies the survey to send to batch emails
	 */
	@Override
	public List<String> sendEmailsByBatchId(int batchId, int surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Distributes survey links to specified emails within the given csv file.
	 * 
	 * @param batchId  the identifier for the desired batch.
	 * @param surveyId the identifier for the survey to distribute.
	 * @param csv      the file containing associate emails.
	 * 
	 */
	@Override
	public EmailResponse sendEmailsByBatchIdAndCSV(int batchId, int surveyId, MultipartFile csv) {
		//Call webclient service?
		EmailResponse response;
		List<String> emails;
		try {
			emails = csvParser.parseFileForEmails(csv);
		} catch (IOException e1) {
	
			return new EmailResponse("CSV Parsing error", null);
		}
		List<String> invalidEmails = new ArrayList<>();
		List<String> failedToSend = new ArrayList<>();
		String mes = "";

		for (String email : emails) {
			try {
				emailService.validateEmail(email);
			} catch (AddressException e) {
				invalidEmails.add(email);
			}
		}

		if (invalidEmails.isEmpty()) {
			for (String email : emails) {
				try {
					emailService.sendEmail(mes, email);
				} catch (MessagingException e) {
					failedToSend.add(email);
				}
			}
			response = new EmailResponse("Failed to send", failedToSend);
			return response;
		} else {
			if (failedToSend.isEmpty()) {
				response = new EmailResponse("Emails successfully sent", null);
				return response;
			} else {
				response = new EmailResponse("Malformatted emails", invalidEmails);
				return response;
			}
		}
	}

}
