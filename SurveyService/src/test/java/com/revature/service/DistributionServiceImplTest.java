package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import com.revature.response.EmailResponse;
import com.revature.service.AuthService;
import com.revature.service.CSVParser;
import com.revature.service.DistributionService;
import com.revature.service.DistributionServiceImpl;
import com.revature.service.EmailService;

@SpringBootTest
class DistributionServiceImplTest {
	
	@Autowired
	private DistributionServiceImpl distributionService;

	@MockBean
	AuthService authService;

	@MockBean
	CSVParser csvParser;

	@MockBean
	EmailService emailService;
	
	@MockBean
	AssociateService associateService;
	
	@MockBean
	SurveySubmissionService surveySubmissionService;
	
	EmailResponse emailResponse;

	String batchId;

	int surveyId;
	
	int surveySubIdAcacia;
	int surveySubIdKsenia;
	int surveySubIdZach;

	int associateIdAcacia;
	int associateIdKsenia;
	int associateIdZach;
	
	HashMap<String, Integer> associateIdEmailMap;
	
	Set<String> emails;
	
	Set<Integer> validAssociateIds;
	
	String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdXJ2ZXlJZCI6IjEyMzQ1Njc4OTAiLCJiYXRjaElkIjoiMjAxMCIsImV4cCI6MjAsImlhdCI6MjB9.rpejfxJ1pM5bZm74bpuHh92vIdqfMkwDHATLGiY35qs";

	String url = "http://qcforce.com/survey?token=";
	
	MockMultipartFile csv;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
		batchId = "2010";
		surveyId = 1;
		
		surveySubIdAcacia = 1;
		surveySubIdKsenia = 2;
		surveySubIdZach = 3;
		
		associateIdAcacia = 1;
		associateIdKsenia = 2;
		associateIdZach = 3;
		
		emails = new HashSet<String>();
		validAssociateIds = new HashSet<Integer>();
		
		emails.add("acacia.holliday@revature.net");
		emails.add("ksenia.milstein@revature.net");
		emails.add("zach.leonardo@revature.net");

		associateIdEmailMap = new HashMap<String, Integer>();
		
		associateIdEmailMap.put("acacia.holliday@revature.net", associateIdAcacia);
		associateIdEmailMap.put("ksenia.milstein@revature.net", associateIdKsenia);
		associateIdEmailMap.put("zach.leonardo@revature.net", associateIdZach);
		
		csv = new MockMultipartFile("data", "emails.csv", "text/plain",
				"acacia.holliday@revature.net,ksenia.milstein@revature.net,zach.leonardo@revature.net".getBytes());
		
		for(String email : emails) {
			when(emailService.isValidEmailAddress(email)).thenReturn(true);
		}
		
		when(associateService.getAssociatesByBatchId(batchId)).thenReturn(associateIdEmailMap);
			
		when(csvParser.parseFileForEmails(csv)).thenReturn(emails);
		
		when(authService.createToken(surveyId, batchId, surveySubIdAcacia)).thenReturn(token);
		when(authService.createToken(surveyId, batchId, surveySubIdKsenia)).thenReturn(token);
		when(authService.createToken(surveyId, batchId, surveySubIdZach)).thenReturn(token);
		
		when(emailService.sendEmails(url + token, emails)).thenReturn(new HashSet<String>());
		
		when(surveySubmissionService.getSurveySubmissionByAssociateId(batchId, surveyId, associateIdAcacia)).thenReturn(surveySubIdAcacia);
		when(surveySubmissionService.getSurveySubmissionByAssociateId(batchId, surveyId, associateIdKsenia)).thenReturn(surveySubIdKsenia);
		when(surveySubmissionService.getSurveySubmissionByAssociateId(batchId, surveyId, associateIdZach)).thenReturn(surveySubIdZach);
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test path of sendEmailsByCSV for valid parameters. Expects verification of Parsing emails from csv file,
	 * checking formatting of each email, get valid survey submission id for each email, create token for each email,
	 * 
	 * @throws Exception
	 */
	@Test
	void sendEmailsByCSV_withValidParameters() throws Exception{
		
		EmailResponse returned = distributionService.sendEmailsByCSV(batchId, surveyId, csv);
		
		verify(csvParser).parseFileForEmails(csv);
		
		for(String email : emails) {
			verify(emailService).isValidEmailAddress(email);
		}
		
		verify(associateService).getAssociatesByBatchId(batchId);
		
		verify(surveySubmissionService).getSurveySubmissionByAssociateId(batchId, surveyId, associateIdAcacia);
		verify(surveySubmissionService).getSurveySubmissionByAssociateId(batchId, surveyId, associateIdKsenia);
		verify(surveySubmissionService).getSurveySubmissionByAssociateId(batchId, surveyId, associateIdZach);
		
		verify(authService).createToken(surveyId, batchId, surveySubIdAcacia);
		verify(authService).createToken(surveyId, batchId, surveySubIdKsenia);
		verify(authService).createToken(surveyId, batchId, surveySubIdZach);
		
		for(String email : emails) {
			verify(emailService).sendEmail(url + token, email); // token used here during the test are not unique
		}
		
		verify(associateService).getAssociatesByBatchId(batchId);
		
		assertEquals(returned.getSendFailedEmails().size(), 0, "Returned EmailResponse with non-empty SendFailedEmails: " + returned.getSendFailedEmails().toString());
		assertEquals(returned.getTokenFailedEmails().size(), 0, "Returned EmailResponse with non-empty TokenFailedEmails: " + returned.getTokenFailedEmails().toString());
		assertEquals(returned.getMalformedEmails().size(), 0, "Returned EmailResponse with non-empty MalformedEmails: " + returned.getMalformedEmails().toString());
		assertEquals(returned.getStatusMessage(), "", "Returned EmailResponse with non-empty StatusMessage: " + returned.getStatusMessage());
		
	}
	
	/**
	 * Test path of sendEmailsByCSV for an Invalid CSV. Expects verification of Parsing emails from csv file to fail
	 * and handle it properly. Should return {@link EmailResponse} with empty lists and status code 'File Not Found'
	 * 
	 * @throws Exception
	 */
	@Test
	void sendEmailsByCSV_withInvalidCSV() throws Exception{
		
		when(csvParser.parseFileForEmails(csv)).thenThrow(IllegalArgumentException.class);
		
		EmailResponse returned = distributionService.sendEmailsByCSV(batchId, surveyId, csv);
		
		verify(csvParser).parseFileForEmails(csv);
		
		assertEquals(returned.getSendFailedEmails().size(), 0, "Returned EmailResponse with non-empty SendFailedEmails: " + returned.getSendFailedEmails().toString());
		assertEquals(returned.getTokenFailedEmails().size(), 0, "Returned EmailResponse with non-empty TokenFailedEmails: " + returned.getTokenFailedEmails().toString());
		assertEquals(returned.getMalformedEmails().size(), 0, "Returned EmailResponse with non-empty MalformedEmails: " + returned.getMalformedEmails().toString());
		assertEquals(returned.getStatusMessage(), "File not found", "Returned EmailResponse with unexpected StatusMessage: " + returned.getStatusMessage());
		
	}
	
	/**
	 * Test path of sendEmailsByCSV for an Invalid batch Id. Expects verification of Parsing emails from csv file to pass
	 * but will handle associateService.getAssociatesByBatchId returning an empty HashMap by returning early with 
	 * the {@link EmailResponse} statusMessage field containing 'Invalid Batch Id'
	 * 
	 * @throws Exception
	 */
	@Test
	void sendEmailsByCSV_withInvalidBatchId() throws Exception{
		
		associateIdEmailMap.clear();
		
		when(associateService.getAssociatesByBatchId(batchId)).thenReturn(associateIdEmailMap);
		
		EmailResponse returned = distributionService.sendEmailsByCSV(batchId, surveyId, csv);
		
		verify(associateService).getAssociatesByBatchId(batchId);

		verify(csvParser).parseFileForEmails(csv);
		
		assertEquals(returned.getSendFailedEmails().size(), 0, "Returned EmailResponse with non-empty SendFailedEmails: " + returned.getSendFailedEmails().toString());
		assertEquals(returned.getTokenFailedEmails().size(), 0, "Returned EmailResponse with non-empty TokenFailedEmails: " + returned.getTokenFailedEmails().toString());
		assertEquals(returned.getMalformedEmails().size(), 0, "Returned EmailResponse with non-empty MalformedEmails: " + returned.getMalformedEmails().toString());
		assertEquals(returned.getStatusMessage(), "Invalid Batch Id", "Returned EmailResponse with unexpected StatusMessage: " + returned.getStatusMessage());
		
	}
	
	
	/**
	 * Test path of sendEmailsByCSV for an Invalid survey Id. Expects verification of Parsing emails from csv file to pass.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	void sendEmailsByCSV_withInvalidSurveyId() throws Exception{
		
		associateIdEmailMap.clear();
		
		when(associateService.getAssociatesByBatchId(batchId)).thenReturn(associateIdEmailMap);
		
		EmailResponse returned = distributionService.sendEmailsByCSV(batchId, surveyId, csv);
		
		verify(csvParser).parseFileForEmails(csv);
		
		verify(associateService).getAssociatesByBatchId(batchId);
		
		assertEquals(returned.getSendFailedEmails().size(), 0, "Returned EmailResponse with non-empty SendFailedEmails: " + returned.getSendFailedEmails().toString());
		assertEquals(returned.getTokenFailedEmails().size(), 0, "Returned EmailResponse with non-empty TokenFailedEmails: " + returned.getTokenFailedEmails().toString());
		assertEquals(returned.getMalformedEmails().size(), 0, "Returned EmailResponse with non-empty MalformedEmails: " + returned.getMalformedEmails().toString());
		assertEquals(returned.getStatusMessage(), "Invalid Batch Id", "Returned EmailResponse with unexpected StatusMessage: " + returned.getStatusMessage());
		
	}
	

}