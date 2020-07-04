package com.revature.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "answer", schema = "qcforce_survey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; // PK

	@Column(name = "answer_string")
	private String answerString;

	@ManyToOne
	@JoinColumn(name = "response_id")
	private Response response; // foreign key response_id

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question; // foreign key question_id

	@Column(name = "weight")
	private double weight;

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(int id, String answerString, Response response, Question question, double weight) {
		super();
		this.id = id;
		this.answerString = answerString;
		this.response = response;
		this.question = question;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswerString() {
		return answerString;
	}

	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerString=" + answerString + ", response=" + response + ", question="
				+ question + ", weight=" + weight + "]";
	}

}