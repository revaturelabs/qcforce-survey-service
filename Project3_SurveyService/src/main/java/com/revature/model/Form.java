package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
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
	
	@Id
	@Column(name="source_id")
	private String sourceId; // SPREADSHEETS ID
	
	@Column(name="creation_form_ts")
	private Timestamp creationFormTs;
	
	@OneToMany(mappedBy="form",  
			targetEntity=Response.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Response> responses = new ArrayList<Response>();
	
	Calendar calendar = Calendar.getInstance();
	java.util.Date now = calendar.getTime();

	public Form() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Form(String sourceId) {
		this.sourceId = sourceId;
		this.creationFormTs = new Timestamp(now.getTime());
	}
	public Form(int formId, String sourceId, Timestamp creationFormTs, List<Response> responses) {
		super();
		this.formId = formId;
		this.sourceId = sourceId;
		this.creationFormTs = creationFormTs;
		this.responses = responses;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public Timestamp getCreationFormTs() {
		return creationFormTs;
	}

	public void setCreationFormTs(Timestamp creationFormTs) {
		this.creationFormTs = creationFormTs;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", sourceId=" + sourceId + ", creationFormTs=" + creationFormTs
				+ ", response=" + responses + "]";
	}

	
	
}
