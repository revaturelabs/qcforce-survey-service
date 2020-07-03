package com.revature.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "answerId")
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int answerId; // PK

	@Column(name = "answer_string")
	private String answerString;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "response_id")
	private Response response; // foreign key response_id

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Question question; // foreign key question_id

	@Column(name = "weight")
	private int weight;

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(int answerId, String answerString, Response response, Question question, int weight) {
		super();
		this.answerId = answerId;
		this.answerString = answerString;
		this.response = response;
		this.question = question;
		this.weight = weight;
	}

	public Answer(Response response) {
		super();
		this.response = response;
	}

	public Answer(Question question) {
		super();
		this.question = question;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerString=" + answerString + ", response=" + response
				+ ", question=" + question + ", weight=" + weight + "]";
	}

}