package com.revature.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistributionController {
	
	@PostMapping("/distribute/{batchId}")
    private ResponseEntity<List<String>> sendEmailsByBatchId(@PathVariable int batchId) {

        return null;
    }
}
