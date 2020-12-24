package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.revature.service.DistributionService;
import com.revature.util.InvalidBatchIdException;

/*
 * These tests the post methods of the Distribution controller. The methods need a valid BatchId and correctly formatted 
 * email addresses. If either condition is not met, an exception should be thrown or return a list of invalid emails.
 */
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
class DistributionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DistributionService service;

	/*
	 * Checks the SendEmailByBatchId method with a valid path parameter and no incorrectly formatted emails in the database
	 */
	@Test
	void distributionControllerSendEmailsByBatchId_withoutError() throws Exception {

		// given
		final List<String> invalidEmails = new ArrayList<>(Arrays.asList());
		int validBatchId = 2010;
		Mockito.when(service.sendEmailsByBatchId(validBatchId)).thenReturn(invalidEmails);

		// when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + validBatchId);
		MvcResult result = mockMvc.perform(request).andReturn();
		
		// then
		assertEquals("", result.getResponse().getContentAsString());

	}

	/*
	 * Checks the SendEmailByBatchId method with an invalid path parameter.
	 * The distribution service should throw an InvalidBatchIdException and the request should return a 400 bad request
	 * status code
	 */
	@Test
	void distributionControllerSendEmailsByBatchId_invalidBatchId() throws Exception {

		// given
		int invalidBatchId = 3010;
		Mockito.when(service.sendEmailsByBatchId(invalidBatchId)).thenThrow(InvalidBatchIdException.class);

		// when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + invalidBatchId);

		// then
		mockMvc.perform(request).andExpect(status().isBadRequest());

	}

	/*
	 * Checks the SendEmailByBatchId method with a valid path parameter, 
	 */
	@Test
	void distributionControllerSendEmailsByBatchId_withInvalidEmail() throws Exception {

		// given
		final List<String> invalidEmails = new ArrayList<>(Arrays.asList("acacia.hollidayrevature.net"));
		int validBatchId = 2010;
		Mockito.when(service.sendEmailsByBatchId(validBatchId)).thenReturn(invalidEmails);

		// when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + validBatchId);
		MvcResult result = mockMvc.perform(request).andReturn();

		// then
		assertEquals("acacia.hollidayrevature.net", result.getResponse().getContentAsString());

	}

	@Test
	void distributionControllerSendEmailsByBatchId_withInvalidMultipleEmails() throws Exception {

		// given
		final List<String> invalidEmails = new ArrayList<>(
				Arrays.asList("acacia.hollidayrevature.net", "ksenia.milstein@revaturenet", "zach.leonardo@revature"));
		int validBatchId = 2010;
		Mockito.when(service.sendEmailsByBatchId(validBatchId)).thenReturn(invalidEmails);

		// when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + validBatchId);
		MvcResult result = mockMvc.perform(request).andReturn();

		// then
		assertEquals("acacia.hollidayrevature.net, ksenia.milstein@revaturenet, zach.leonardo@revature",
				result.getResponse().getContentAsString());

	}

	@Test
	void distributionControllerSendEmailsByBatchIdAndCSV_withoutError() throws Exception {

		// given
		final List<String> invalidEmails = new ArrayList<>(Arrays.asList());
		int validBatchId = 2010;
		Mockito.when(service.sendEmailsByBatchIdAndCSV(validBatchId, new File("emails.csv"))).thenReturn(invalidEmails);

		// when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/")
				.param("batchId", Integer.toString(validBatchId)).param("csv", "files.csv");
		MvcResult result = mockMvc.perform(request).andReturn();

		// then
		assertEquals("", result.getResponse().getContentAsString());

	}

	@Test
	void distributionControllerSendEmailsByBatchIdAndCsv_invalidBatchId() throws Exception {

		// given
		int invalidBatchId = 3010;
		Mockito.when(service.sendEmailsByBatchIdAndCSV(invalidBatchId, new File("emails.csv")))
				.thenThrow(InvalidBatchIdException.class);

		// when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/")
				.param("batchId", Integer.toString(invalidBatchId)).param("csv", "files.csv");
		// then
		mockMvc.perform(request).andExpect(status().isBadRequest());

	}

	@Test
	void distributionControllerSendEmailsByBatchIdAndCSV_withInvalidEmail() throws Exception {
		
		//given
		final List<String> invalidEmails = new ArrayList<>(Arrays.asList("acacia.hollidayrevature.net"));
		int validBatchId = 2010;
		Mockito.when(service.sendEmailsByBatchIdAndCSV(validBatchId, new File("emails.csv"))).thenReturn(invalidEmails);

		//when
		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/")
				.param("batchId", Integer.toString(validBatchId)).param("csv", "files.csv");
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//then
		assertEquals("acacia.hollidayrevature.net", result.getResponse().getContentAsString());

	}

}
