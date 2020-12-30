package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.service.DistributionService;

/**
 * This controller has three endpoints, one takes in a batch id, another takes
 * in a batch id with a set of emails, and the last takes in a batch id with a
 * .CSV file of emails. If there are no errors the emails are sent to
 * associates.
 * 
 * @author Acacia Holliday, Ksenia Milstein, Marc Roy, Zach Leonardo
 */
@RestController
public class DistributionController {

	private DistributionService distributionService;

	@Autowired
	public void setDistributionService(DistributionService distributionService) {
		this.distributionService = distributionService;
	}

	/**
	 * The method will send an email to associates that are in the database for the
	 * batchId provided.
	 * 
	 * @param batchId represents a batch identifier
	 * @return List of incorrectly formatted emails in the database if any
	 */
	@PostMapping("/distribute/{surveyId}/{batchId}")
	private ResponseEntity<List<String>> sendEmailsByBatchId(@PathVariable int surveyId, @PathVariable int batchId) {

		return null;
	}

	/**
	 * The method will send an email to associates that are provided in the CSV
	 * file.
	 * 
	 * @param batchId  represents a batch identifier
	 * @param surveyId represents a survey that will be filled out by associates
	 * @param csv      represents a csv file of emails
	 * @return List of incorrectly formatted emails in the database if any
	 */
	@PostMapping("/distribute/{surveyId}")
	private ResponseEntity<List<String>> sendEmailsByCSV(@PathVariable int surveyId, @RequestParam int batchId,
			@RequestParam MultipartFile csv) {
		List<String> returnList = new ArrayList<String>();

		try {
			returnList = distributionService.sendEmailsByBatchIdAndCSV(batchId, surveyId, csv);
		} catch (Exception e) {
			returnList.add("Failed to upload files!");
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(returnList);
		}
		
		if (returnList.isEmpty()) {
			returnList.add("Emails sent successfully: " + csv.getOriginalFilename());
		}

		return ResponseEntity.status(HttpStatus.OK).body(returnList);

	}
}
