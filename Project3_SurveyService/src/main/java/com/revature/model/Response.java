package com.revature.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name="response", schema="qcforce_survey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "responseId")
public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="response_id")
	private int responseId;  //primary key
	
	/*
	@OneToMany
	private int formId; //FK
	*/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "form_id")
	private Form form; //foreign key form_id
	
	@Column(name="batch_name")
	private String batchName;
	
	
	@OneToMany(mappedBy="response",  
			targetEntity=Answer.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private Set<Answer> answer = new HashSet<Answer>();


	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Response(int responseId, Form form, String batchName, Set<Answer> answer) {
		super();
		this.responseId = responseId;
		this.form = form;
		this.batchName = batchName;
		this.answer = answer;
	}


	public Response(String batchName) {
		super();
		this.batchName = batchName;
	}


	public Response(Form form) {
		super();
		this.form = form;
	}


	public int getResponseId() {
		return responseId;
	}


	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}


	public Form getForm() {
		return form;
	}


	public void setForm(Form form) {
		this.form = form;
	}


	public String getBatchName() {
		return batchName;
	}


	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}


	public Set<Answer> getAnswer() {
		return answer;
	}


	public void setAnswer(Set<Answer> answer) {
		this.answer = answer;
	}


	@Override
	public String toString() {
		return "Response [responseId=" + responseId + ", form=" + form + ", batchName=" + batchName + ", answer="
				+ answer + "]";
	} 

	

}
