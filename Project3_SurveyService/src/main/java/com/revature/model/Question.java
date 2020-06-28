package com.revature.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="qcforce_survey.question")
public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="question_id")
	private int questionId;//PK
	
	@Column(name="question_string")
	private String question;
	
	/*
	@OneToMany
	private int formId;// FK
	*/
	
	@Column(name="question_type")
	private String questionType;
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	/*
	@Override
	public String toString() {
		return "question [questionId=" + questionId + ", question=" + question + ", formId=" + formId
				+ ", questionType=" + questionType + "]";
	}	
*/
}
