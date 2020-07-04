package com.revature.model;

public class ResponseObject {
	
	private String batch;
	private String week;
	private String question;
	private Double answer;
	
	public ResponseObject() {
		super();
	}
	
	public ResponseObject(String batch, String week, String question, Double answer) {
		super();
		this.batch = batch;
		this.week = week;
		this.question = question;
		this.answer = answer;
	}

	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Double getAnswer() {
		return answer;
	}
	public void setAnswer(Double answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "ResponseObject [batch=" + batch + ", week=" + week + ", question=" + question + ", answer=" + answer
				+ "]";
	}
	
	
	

}
