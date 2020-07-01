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
@Table(name="answer", schema="qcforce_survey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "answerId")
public class Answer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="answer_id")
	private int answerId; //PK
	
	@Column(name="answer_string")
	private String answerString;	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "question_id")
	private Question question; //foreign key question_id
	

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Answer(int answerId, String answerString, Question question) {
		super();
		this.answerId = answerId;
		this.answerString = answerString;
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
	
	public Question getQuestion() {
		return question;
	}


	public void setQuestion(Question question) {
		this.question = question;
	}


	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerString=" + answerString + ", question=" + question + "]";
	}

	
	
	
}
