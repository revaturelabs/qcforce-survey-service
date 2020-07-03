package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Response;
import com.revature.service.ResponseService;

@RestController
public class ResponseController {

	private ResponseService responseService;

	@Autowired
	public void setResponseService(ResponseService responseService) {
		this.responseService = responseService;
	}

	@GetMapping("/response")
	public List<Response> getAllForms() {
		return responseService.getAllResponses();
	}

	@GetMapping("/response/{formId}")
	public List<Response> getResponseById(@PathVariable("formId") int formId) {
		return (List<Response>) responseService.getResponseByFormId(formId);
	}

	/*
	 * @GetMapping("/response/{batchName}") public List<Response>
	 * getNotificationByBatchName(@PathVariable("batchaAme") String batchName) {
	 * return (List<Response>) responseService.getResponseByBatchName(batchName); }
	 */
}
