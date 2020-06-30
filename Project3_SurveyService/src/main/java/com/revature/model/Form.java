package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
	
	@Column(name="creation_form_ts")
	private Timestamp creationFormTs;
	
	@OneToMany(mappedBy="form",  
			targetEntity=Question.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private Set<Question> question = new HashSet<Question>(); 
	
	@OneToMany(mappedBy="form",  
			targetEntity=Response.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private Set<Response> response = new HashSet<Response>();

	public Form() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Form(int formId, Timestamp creationFormTs, Set<Question> question, Set<Response> response) {
		super();
		this.formId = formId;
		this.creationFormTs = creationFormTs;
		this.question = question;
		this.response = response;
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

	public Set<Question> getQuestion() {
		return question;
	}

	public void setQuestion(Set<Question> question) {
		this.question = question;
	}

	public Set<Response> getResponse() {
		return response;
	}

	public void setResponse(Set<Response> response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", creationFormTs=" + creationFormTs + ", question=" + question
				+ ", response=" + response + "]";
	} 
	
	
}
