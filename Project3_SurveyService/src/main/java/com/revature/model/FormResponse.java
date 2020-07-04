package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @authors
 *
 */
@Document(collection = "formscollection")
public class FormResponse implements Serializable {

	private static final long serialVersionUID = 9136762341724971453L;

	@Id
	private int responseId;

	private int formId;

	private String batch;

	private String week;

	private String timestamp;

	private List<String> answers;

	private List<Integer> weights;

	@Transient
	private List<String> questions;

	public FormResponse() {
		super();
		this.answers = new ArrayList<String>();
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
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

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public List<Integer> getWeights() {
		return weights;
	}

	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}

	public FormResponse(int formId, String week, String batch, String timestamp, String sourceId, Set<String> questions,
			List<String> answers) {
		super();
		this.formId = formId;
		this.week = week;
		this.batch = batch;
		this.timestamp = timestamp;
		this.answers = answers;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result + formId;
		result = prime * result + responseId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());
		result = prime * result + ((weights == null) ? 0 : weights.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormResponse other = (FormResponse) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (formId != other.formId)
			return false;
		if (responseId != other.responseId)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		if (weights == null) {
			if (other.weights != null)
				return false;
		} else if (!weights.equals(other.weights))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FormResponse [getAnswers()=" + getAnswers() + ", getFormId()=" + getFormId() + ", getTimestamp()="
				+ getTimestamp() + ", getResponseId()=" + getResponseId() + ", getWeights()=" + getWeights()
				+ ", getWeek()=" + getWeek() + ", getBatch()=" + getBatch() + ", hashCode()=" + hashCode() + "]";
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

}