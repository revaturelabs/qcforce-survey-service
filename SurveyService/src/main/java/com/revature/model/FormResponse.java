package com.revature.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents form data as consumed from the messaging queue.
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin 
 */
@Document(collection = "formscollection")
public class FormResponse implements Serializable {

	/**
	 * Provides a serial version ID for deserialization
	 */
	private static final long serialVersionUID = 9136762341724971453L;
	/**
	 *	variable of type {@link Integer} that represents the internal response id used by the database. 
	 */
	@Id
	private int responseId;
	/**
	 *	variable of type {@link Integer} that represents the internal form id used by the database. 
	 */
	private int formId;
	/**
	 *	variable of type {@link String} that represents the internal batch name used by the database. 
	 */
	private String batch;
	/**
	 *	variable of type {@link String} that represents the internal batch week used by the database. 
	 */
	private String week;
	/**
	 * variable of type {@link String} that represents the time the response was submitted. 
	 */
	private String timestamp;

	/**
	 * variable of type {@link List}{@link String} that represents the answers of the response. 
	 */
	private List<String> answers;

	/**
	 * variable of type {@link List}{@link Double} that represents the weights given to the answers of the response. 
	 */
	private List<Double> weights;

	/**
	 * variable of type {@link List}{@link String} that represents the questions of the response. 
	 */
	@Transient
	private List<String> questions;

	/**
	 * initializes the answers array.
	 */
	public FormResponse() {
		super();
		this.answers = new ArrayList<String>();
	}
	/**
	 * Gets an {@link List}{@link String} of answers in the form.
	 * @return {@link List}{@link String} answers.
	 */
	public List<String> getAnswers() {
		return answers;
	}
	/** Sets the answers.
	 * @param answers new {@link List}{@link String} of answers.
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	/**
	 * Gets the form id.
	 * @return form id.
	 */
	public int getFormId() {
		return formId;
	}

	/**
	 * Sets the form id.
	 * @param formId new form id.
	 */
	public void setFormId(int formId) {
		this.formId = formId;
	}

	/**
	 * Gets the timeStamp.
	 * @return timeStamp.
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timeStamp.
	 * @param timestamp new timestamp.
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Gets the response id.
	 * @return response id.
	 */
	public int getResponseId() {
		return responseId;
	}
	/**
	 * Sets the response id.
	 * @param responseId new responseId.
	 */
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	/**
	 * Gets an {@link List}{@link Double} of weights to answers in the form.
	 * @return {@link List}{@link Double} weights.
	 */
	public List<Double> getWeights() {
		return weights;
	}
	/** Sets the weights for answers.
	 * @param weights new {@link List}{@link Double} of weights.
	 */
	public void setWeights(List<Double> weights) {
		this.weights = weights;
	}
	/**Gets the week of when a FormResponse was submitted
	 * @return the week
	 */
	public String getWeek() {
		return week;
	}

	/**Sets the week of when a FormResponse was submitted
	 * @param week new week
	 */
	public void setWeek(String week) {
		this.week = week;
	}

	/**Gets the batch name associated with the FormResponse
	 * @return the batch name
	 */
	public String getBatch() {
		return batch;
	}

	/**Sets the batch name associated with the FormResponse
	 * @param batch new batch
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}
	/**Gets {@link List}{@link String} of questions
	 * @return list of questions
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/**Sets {@link List}{@link String} of questions
	 * @param questions new questions
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}
	
	/**
	 * Creates a {@link FormResponse}
	 * @param formId new formId
	 * @param week new week
	 * @param batch new batch
	 * @param timestamp new timestamp
	 * @param sourceId new sourceId
	 * @param questions new question
	 * @param answers new list of answers
	 */
	public FormResponse(int formId, String week, String batch, String timestamp, String sourceId, Set<String> questions,
			List<String> answers) {
		super();
		this.formId = formId;
		this.week = week;
		this.batch = batch;
		this.timestamp = timestamp;
		this.answers = answers;
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
}