package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "response_id")
	private Response response; //foreign key
	
	@OneToMany(mappedBy="question",  
			targetEntity=Answer.class, 
			fetch=FetchType.EAGER, 
			cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<Answer>();

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Question(int questionId, String questionString, Response response, List<Answer> answers) {
		super();
		this.questionId = questionId;
		this.questionString = questionString;
		this.response = response;
		this.answers = answers;
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
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionString=" + questionString + ", response=" + response + ", answer=" + answers + "]";
	}

	
	
}
