package com.revature.response;

import java.util.List;

public class EmailResponse {
	
	private String message;
	private List<String> emails;
	
	public EmailResponse(String message, List<String> emails) {
		super();
		this.message = message;
		this.emails = emails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
}
