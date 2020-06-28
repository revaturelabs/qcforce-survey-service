package com.revature.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qcforce_survey.form")
public class Form implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="form_id")
	private int formId; //PK
	
	@Column(name="creation_form_ts")
	private String newFormTimestamp;
	
	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public String getNewFormTimestamp() {
		return newFormTimestamp;
	}

	public void setNewFormTimestamp(String newFormTimestamp) {
		this.newFormTimestamp = newFormTimestamp;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", newFormTimestamp=" + newFormTimestamp + "]";
	}
	
}
