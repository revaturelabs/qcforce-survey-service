package com.revature.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "form", schema = "qcforce_survey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Form implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; // PK

	@CreationTimestamp
	@Column(name = "created_time")
	private Timestamp creationTime;

	@Column(name = "source_id")
	private String sourceId;

	@OneToMany(mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Question> questions = new ArrayList<Question>();

	@OneToMany(mappedBy = "form", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Response> responses = new ArrayList<Response>();

	public Form() {
		super();
	}

	public Form(String sourceId) {
		this.sourceId = sourceId;
	}

	public Form(int id, Timestamp creationTime, List<Question> questions, List<Response> responses) {
		super();
		this.id = id;
		this.creationTime = creationTime;
		this.questions = questions;
		this.responses = responses;
	}

	public Form(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void addResponse(Response response) {
		this.responses.add(response);
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", creationTime=" + creationTime + ", question=" + questions + ", response="
				+ responses + "]";
	}

}