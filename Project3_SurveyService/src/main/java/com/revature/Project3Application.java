package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.revature.model.Form;
import com.revature.service.FormServiceImpl;

@SpringBootApplication
public class Project3Application {
	


	public static void main(String[] args) {
		FormServiceImpl formService = new FormServiceImpl();
		Form form = new Form();
		form.setFormId(1);
		form.setNewFormTimestamp("2020-05-05");
		formService.createForm(form);
		SpringApplication.run(Project3Application.class, args);
		
		
	}

}
