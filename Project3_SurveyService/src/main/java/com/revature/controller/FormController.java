package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.FormResponseService;

@RestController
public class FormController {

	private FormResponseService formResponseService;

	@Autowired
	public void setFormResponseService(FormResponseService formResponseService) {
		this.formResponseService = formResponseService;
	}

	@GetMapping("/batch/list")
	public List<String> getBatchNames() {
		return formResponseService.getBatchNames();
	}

}
