package com.revature.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents form data from form submissions.
 * @author Anastasia Miagkii, Andres Toledo, Jose Canela, Monica Datta, Wei Wu, Zachary Reagin
 */
@Document(collection = "forms")
public class Form {

	/**
	 *	variable of type {@link Integer} that represents the internal form id used by the database. 
	 */
	@Id
	private int id;
	/**
	 *	variable of type {@link String} that represents the id linked to the response spreadsheet. 
	 */
	private String sourceId;
	/**
	 *	variable of type {@link List}{@link String} that represents a String of questions. 
	 */
	private List<String> questions;

	@Override
	public String toString() {
		return "Form [hashCode()=" + hashCode() + ", getSourceId()=" + getSourceId() + ", getQuestions()="
				+ getQuestions() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
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
		Form other = (Form) obj;
		if (id != other.id)
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
		return true;
	}

	public Form() {
		super();
		this.questions = new ArrayList<String>();
	}
	/**Gets source id.
	 * @return source id.
	 */
	public String getSourceId() {
		return sourceId;
	}
	/**
	 * Sets source id.
	 * @param sourceid new sourceId.
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	/**
	 * Gets an {@link List}{@link String} of questions in the form.
	 * @return {@link List}{@link String} questions.
	 */
	public List<String> getQuestions() {
		return questions;
	}
	
	/** Sets the questions.
	 * @param questions new {@link List}{@link String} of questions.
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}
	/**Gets id.
	 * @return id new id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets id.
	 * @param id new id.
	 */
	public void setId(int id) {
		this.id = id;
	}

}
