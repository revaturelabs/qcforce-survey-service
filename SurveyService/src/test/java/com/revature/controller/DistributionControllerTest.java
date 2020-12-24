package com.revature.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.RequestBuilder;

import com.revature.service.DistributionService;
import com.revature.util.InvalidBatchIdException;

/*
 * This tests the sendEmailsByBatchId method in the Distribution controller. If there are no errors, then a it will perform a 
 * post request and not return any emails. If an invalid batchId is provided, an exception will be thrown and a status of
 * Bad Request
 */
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
class DistributionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DistributionService service;

	@Test
	void distributionControllerSendEmailsByBatchId_withoutError() throws Exception {

		final List<String> invalidEmails = new ArrayList<>(Arrays.asList());

		int validBatchId = 2010;

		Mockito.when(service.sendEmailsByBatchId(validBatchId)).thenReturn(invalidEmails);

		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + validBatchId);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals("", result.getResponse().getContentAsString());

	}

	@Test
	void distributionControllerSendEmailsByBatchId_invalidBatchId() throws Exception {

		final List<String> invalidEmails = new ArrayList<>(Arrays.asList());

		int invalidBatchId = 3010;

		Mockito.when(service.sendEmailsByBatchId(invalidBatchId)).thenThrow(InvalidBatchIdException.class);

		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + invalidBatchId);
		mockMvc.perform(request).andExpect(status().isBadRequest());

	}

	@Test
	void distributionControllerSendEmailsByBatchId_withInvalidEmail() throws Exception {

		final List<String> invalidEmails = new ArrayList<>(Arrays.asList("acacia.hollidayrevature.net"));

		int validBatchId = 2010;

		Mockito.when(service.sendEmailsByBatchId(validBatchId)).thenReturn(invalidEmails);

		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + validBatchId);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals("acacia.hollidayrevature.net", result.getResponse().getContentAsString());

	}

	@Test
	void distributionControllerSendEmailsByBatchId_withInvalidMultipleEmails() throws Exception {

		final List<String> invalidEmails = new ArrayList<>(
				Arrays.asList("acacia.hollidayrevature.net", "ksenia.milstein@revaturenet", "zach.leonardo@revature"));

		int validBatchId = 2010;

		Mockito.when(service.sendEmailsByBatchId(validBatchId)).thenReturn(invalidEmails);

		RequestBuilder request = MockMvcRequestBuilders.post("/distribute/" + validBatchId);
		MvcResult result = mockMvc.perform(request).andReturn();
		assertEquals("acacia.hollidayrevature.net, ksenia.milstein@revaturenet, zach.leonardo@revature",
				result.getResponse().getContentAsString());

	}

}
