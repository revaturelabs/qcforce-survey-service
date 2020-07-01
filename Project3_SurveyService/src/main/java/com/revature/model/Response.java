package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
	
	@Column(name="submitted_response_ts")
	private Timestamp submittedResponseTs;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "form_id")
	private Form form; //foreign key form_id
	
	@Column(name="batch_name")
	private String batchName;
	
	
	@OneToMany(mappedBy="response",  
			targetEntity=Answer.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Answer> answer = new ArrayList<Answer>();


	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Response(int responseId, Timestamp submittedResponseTs, Form form, String batchName) {
		super();
		this.responseId = responseId;
		this.submittedResponseTs = submittedResponseTs;
		this.form = form;
		this.batchName = batchName;
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

	public Timestamp getSubmittedResponseTs() {
		return submittedResponseTs;
	}


	public void setSubmittedResponseTs(Timestamp submittedResponseTs) {
		this.submittedResponseTs = submittedResponseTs;
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


	public List<Answer> getAnswer() {
		return answer;
	}


	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}


	@Override
	public String toString() {
		return "Response [responseId=" + responseId + ", form=" + form + ", batchName=" + batchName + ", answer="
				+ answer + "]";
	} 

	

}