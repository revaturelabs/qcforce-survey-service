package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @authors
 *
 */
public class FormResponse implements Serializable {

	private static final long serialVersionUID = 9136762341724971453L;

	@JsonProperty("formId")
	private int formResponseId;

	private String timestamp;

	private String sourceId;

	private List<String> questions;

	private List<String> answers;

	public FormResponse() {
		super();
		this.questions = new ArrayList<String>();
		this.answers = new ArrayList<String>();
	}

	/**
	 * @return
	 */
	public int getFormResponseId() {
		return formResponseId;
	}

	/**
	 * @param formResponseId
	 */
	public void setFormResponseId(int formResponseId) {
		this.formResponseId = formResponseId;
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

}