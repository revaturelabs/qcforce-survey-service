package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.service.AuthService;

@SpringBootTest
class AuthServiceJWTTest {
	
	@Autowired
	AuthService authService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateToken_withValidSurveyId() {
		int batchId = 2010;
		int surveyId = 1;
		String token = this.authService.createToken(batchId, surveyId);
		
		
	}
	
	@Test
	void testCreateToken_withInvalidSurveyId() {
		int batchId = 2010;
		int surveyId = 0;
		String token = authService.createToken(batchId, surveyId);
		String expectedToken = "";
		assertEquals(expectedToken, token);
	}
	
	

}
