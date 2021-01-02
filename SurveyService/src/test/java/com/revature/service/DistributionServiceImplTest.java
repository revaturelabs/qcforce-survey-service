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
	
	EmailResponse emailResponse;

	int batchId;

	int surveyId;
	
	int surveySubIdAcacia;
	int surveySubIdKsenia;
	int surveySubIdZach;
	
	Set<String> emails;
	
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
		
		batchId = 2010;
		surveyId = 1;
		
		surveySubIdAcacia = 1;
		surveySubIdKsenia = 2;
		surveySubIdZach = 3;
		
		emails.add("acacia.holliday@revature.net");
		emails.add("ksenia.milstein@revature.net");
		emails.add("zach.leonardo@revature.net");
		
		csv = new MockMultipartFile("data", "emails.csv", "text/plain",
				"acacia.holliday@revature.net,ksenia.milstein@revature.net,zach.leonardo@revature.net".getBytes());
		
		when(csvParser.parseFileForEmails(csv)).thenReturn(emails);
		when(authService.createToken(surveyId, batchId, surveySubIdAcacia)).thenReturn(token);
		when(authService.createToken(surveyId, batchId, surveySubIdKsenia)).thenReturn(token);
		when(authService.createToken(surveyId, batchId, surveySubIdZach)).thenReturn(token);
		when(emailService.sendEmails(url + token, emails)).thenReturn(new HashSet<String>());
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void sendEmailsByCSV_withValidParameters() throws Exception{

	
		emailResponse = distributionService.sendEmailsByCSV(batchId, surveyId, csv);
		
		verify(csvParser).parseFileForEmails(csv);
		verify(authService).createToken(surveyId, batchId, surveySubIdAcacia);
		verify(authService).createToken(surveyId, batchId, surveySubIdKsenia);
		verify(authService).createToken(surveyId, batchId, surveySubIdZach);
		verify(emailService).sendEmails(url + token, emails);
		
		assertTrue(emailResponse.getSendFailedEmails().size() == 0);
		assertTrue(emailResponse.getTokenFailedEmails().size() == 0);
		assertTrue(emailResponse.getMalformedEmails().size() == 0);
		
		// parseFileForEmails(file)
		// call send emails
	}

	// happy path
	// invalid batchId
	// invalid surveyID
	// invalid csv

}