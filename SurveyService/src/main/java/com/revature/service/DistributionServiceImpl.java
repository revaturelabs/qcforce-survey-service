package com.revature.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * TODO: Document after implementation
	 */
	@Override
	public List<String> sendEmailsByBatchId(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Distributes survey links to specified emails within the given csv file.
	 * 
	 * @param batchId the identifier for the desired batch.
	 * @param surveyId the identifier for the survey to distribute.
	 * @param csv the file containing associate emails.
	 */
	@Override
	public List<String> sendEmailsByBatchIdAndCSV(int batchId, int surveyId, File csv) {

		String token = authService.createToken(surveyId);
		String surveyURL = baseURL + "/survey?token=" + token;

		List<String> emails = csvParser.parseFileForEmails(csv);

		return emailService.sendEmails(surveyURL, emails);

	}

}
