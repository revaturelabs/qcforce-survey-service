package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @authors
 *
 */
@Entity
public class FormResponse implements Serializable {

	private static final long serialVersionUID = 9136762341724971453L;

	@Id
	private int formId;

	@Column
	private String timestamp;

	@Transient
	private String sourceId;

	@Transient
	private List<String> questions;

	@Transient
	private List<String> answers;

	@Column(columnDefinition = "TEXT")
	private String questionsString;

	@Column(columnDefinition = "TEXT")
	private String answersString;

	public String getQuestionsString() {
		return questionsString;
	}

	public void setQuestionsString(String questionsString) {
		this.questionsString = questionsString;
	}

	public String getAnswersString() {
		return answersString;
	}

	public void setAnswersString(String answersString) {
		this.answersString = answersString;
	}

	public FormResponse() {
		super();
		this.questions = new ArrayList<String>();
		this.answers = new ArrayList<String>();
	}

	/**
	 * @return
	 */
	public int getFormId() {
		return formId;
	}

	/**
	 * @param formId
	 */
	public void setFormId(int formId) {
		this.formId = formId;
	}

	/**
	 * @return
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	/**
	 * @return
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	/**
	 * @return
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "FormResponse [formId=" + formId + ", timestamp=" + timestamp + ", sourceId=" + sourceId + ", questions="
				+ questions + ", answers=" + answers + ", questionsString=" + questionsString + ", answersString="
				+ answersString + "]";
	}

}