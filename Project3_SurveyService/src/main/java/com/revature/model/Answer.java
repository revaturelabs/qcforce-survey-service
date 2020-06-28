package com.revature.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="qcforce_survey.answer")
public class Answer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="answer_id")
	private int answerId; //PK
	
	@Column(name="answer_string")
	private String answer;
	
	@OneToMany
	private int responseId;// FK
	
	@OneToMany
	private int questionId; //FK

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answer=" + answer + ", responseId=" + responseId + ", questionId="
				+ questionId + "]";
	}
	
}
