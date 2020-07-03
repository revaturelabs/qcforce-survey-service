package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="form", schema="qcforce_survey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "formId")
public class Form implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="form_id")
	private int formId; //PK
	
	@Column(name="new_form_ts")
	private Timestamp creationFormTs;
	
	@Column(name="source_id")
	private String sourceId;
	
	@OneToMany(mappedBy="form",  
			targetEntity=Question.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Question> questions = new ArrayList<Question>(); 
	
	@OneToMany(mappedBy="form",  
			targetEntity=Response.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Response> responses = new ArrayList<Response>();

	Calendar calendar = Calendar.getInstance();
	java.util.Date now = calendar.getTime();

	public Form() {
		super();
	}
	public Form(String sourceId) {
		this.sourceId = sourceId;
		this.creationFormTs = new Timestamp(now.getTime());
	}

	public Form(int formId, Timestamp creationFormTs, List<Question> questions, List<Response> responses) {
		super();
		this.formId = formId;
		this.creationFormTs = creationFormTs;
		this.questions = questions;
		this.responses = responses;
	}

	public Form(int formId) {
		super();
		this.formId = formId;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public Timestamp getCreationFormTs() {
		return creationFormTs;
	}

	public void setCreationFormTs(Timestamp creationFormTs) {
		this.creationFormTs = creationFormTs;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", creationFormTs=" + creationFormTs + ", question=" + questions
				+ ", response=" + responses + "]";
	} 
	
	
}