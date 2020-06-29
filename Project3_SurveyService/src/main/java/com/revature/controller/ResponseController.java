package com.revature.controller;

import java.util.List;

import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.revature.model.Notification;
import com.revature.model.Response;
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
	public List<Response> getResponseById(@PathVariable("formId")int formId) {
		return (List<Response>) responseService.getResponseByFormId(formId);
	}
	@GetMapping("/response/{batchName}")
	public List<Response> getNotificationByBatchName(@PathVariable("batchaAme")String batchName) {
		return (List<Response>) responseService.getResponseByBatchName(batchName);
	}
	
	@PostMapping("/response")
	public String createResponse(@RequestBody Response response) {
		responseService.createResponse(response);
		return "Response successfully created";
	}
	
	@PutMapping("/response")
	public String updateResponse(@RequestBody Response response) {
		responseService.updateResponse(response);
		return "Response successfully updated";
	}
	
	@DeleteMapping("/response/{id}")
	public String deleteResponse(@PathParam("responseId") int responseId) {
		Response response = new Response();
		response.setResponseId(responseId);
		responseService.deleteResponse(response);
		return "Response successfully deleted";
	}	
	
	
}
