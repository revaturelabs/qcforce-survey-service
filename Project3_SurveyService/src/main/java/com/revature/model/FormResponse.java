package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	private String week;

	@Column
	private String batch;

	@Column
	private String timestamp;

	@Transient
	private String sourceId;

	@ElementCollection
	private Collection<String> questions;

	@ElementCollection
	private Collection<String> answers;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + formId;
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		if (formId != other.formId)
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	public Collection<String> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<String> questions) {
		this.questions = questions;
	}

	public Collection<String> getAnswers() {
		return answers;
	}

	public void setAnswers(Collection<String> answers) {
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
	public String toString() {
		return "FormResponse [getFormId()=" + getFormId() + ", getTimestamp()=" + getTimestamp() + ", getSourceId()="
				+ getSourceId() + ", hashCode()=" + hashCode() + ", getQuestions()=" + getQuestions()
				+ ", getAnswers()=" + getAnswers() + "]";
	}

}