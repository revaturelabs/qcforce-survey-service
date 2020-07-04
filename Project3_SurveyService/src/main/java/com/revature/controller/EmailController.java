package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping(value = "/sendmail")
	public String sendmail() {

		List<String> emailList = new ArrayList<String>();
		emailList.add("first@email.com");
		emailList.add("second@email.com");
		// emailList.add("third@email.com");

		for (String email : emailList) {
			emailService.sendMail(email, "Upcoming QC event",
					"Please complete QC form. Link is down below \n http://www.google.com");
		}

		return "emailsent";
	}
}