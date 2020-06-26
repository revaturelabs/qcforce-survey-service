package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int responseId;  //primary key
	private String batchName;
	private String formId;
	private List<String> answers = new ArrayList<String>();
	
	
	public int getResponseId() {
		return responseId;
	}
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		return "Response [responseId=" + responseId + ", batchName=" + batchName + ", formId=" + formId + ", answers="
				+ answers + "]";
	}
	
	

}
