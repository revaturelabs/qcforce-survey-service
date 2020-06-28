package com.revature.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="qcforce_survey.response")
public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="response_id")
	private int responseId;  //primary key
	
	/*
	@OneToMany
	private int formId; //FK
	*/
	@Column(name="batch_name")
	private String batchName;
	
	
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
	
	/*
	@Override
	public String toString() {
		return "Response [responseId=" + responseId + ", batchName=" + batchName + ", formId=" + formId + "]";
	}
	*/
	

}
