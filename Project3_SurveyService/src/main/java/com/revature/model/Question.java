package com.revature.model;

import java.io.Serializable;
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
@Table(name="question", schema="qcforce_survey")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "questionId")
public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="question_id")
	private int questionId;//PK
	
	@Column(name="question_string")
	private String questionString;
	
	/*
	@OneToMany
	private int formId;// FK
	*/
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "form_id")
	private Form form; //foreign key form_id
	
	@Column(name="question_type")
	private String questionType;
	
	@OneToMany(mappedBy="question",  
			targetEntity=Answer.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Answer> answer = new ArrayList<Answer>();

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(int questionId, String questionString, Form form, String questionType, List<Answer> answer) {
		super();
		this.questionId = questionId;
		this.questionString = questionString;
		this.form = form;
		this.questionType = questionType;
		this.answer = answer;
	}

	public Question(String questionType) {
		super();
		this.questionType = questionType;
	}
	

	public Question(Form form) {
		super();
		this.form = form;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionString() {
		return questionString;
	}

	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionString=" + questionString + ", form=" + form + ", questionType="
				+ questionType + ", answer=" + answer + "]";
	} 
	
	
}