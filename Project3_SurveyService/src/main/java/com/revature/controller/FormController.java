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
import com.revature.model.Form;
import com.revature.service.FormService;

@RestController
public class FormController {
	
	private FormService formService;

	@Autowired
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
	@GetMapping("/form")
	public List<Form> getAllForms() {
		return formService.getAllForms();
	}
	
	@GetMapping("/form/{id}")
	public Form getFormById(@PathVariable("formId")int formId) {
		return formService.getFormById(formId);
	}
	
	@PostMapping("/form")
	public String createForm(@RequestBody Form form) {
		formService.createForm(form);
		return "Form successfully created";
	}
	
	@PutMapping("/form")
	public String updateForm(@RequestBody Form form) {
		formService.updateForm(form);
		return "Form successfully updated";
	}
	
	@DeleteMapping("/form/{id}")
	public String deleteForm(@PathParam("formId") int formId) {
		Form form = new Form();
		form.setFormId(formId);
		formService.deleteForm(form);
		return "Form successfully deleted";
	}
}
