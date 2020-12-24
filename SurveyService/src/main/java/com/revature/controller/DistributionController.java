package com.revature.controller;

import java.io.File;
import java.util.List;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller takes in a batch id, or a batch id with a set of emails, then
 * validates if the emails are correctly formatted and the batch id exists. If
 * the emails are invalid then the invalid emails will be returned. If the batch
 * id does not exist then an exception is thrown. If there are no errors the
 * emails are sent to associates.
 * 
 * @author Acacia Holliday, Ksenia Milstein, Marc Roy, Zach Leonardo
 */
@RestController
public class DistributionController {
	/**
	 * The method will send an email to associates that are in the database for the
	 * batchId provided.
	 * 
	 * @param batchId represents a batch identifier
	 * @return List of incorrectly formatted emails in the database if any
	 */
	@PostMapping("/distribute/{batchId}")
	private ResponseEntity<List<String>> sendEmailsByBatchId(@PathVariable int batchId) {

		return null;
	}
	
	@PostMapping("/distribute")
	private ResponseEntity<List<String>> sendEmailsByCSV(@RequestParam int batchId,
			@RequestParam File csv ){
		return null;
	}
}
