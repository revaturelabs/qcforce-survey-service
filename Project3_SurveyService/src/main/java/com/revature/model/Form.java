package com.revature.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="form", schema="qcforce_survey")
public class Form implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="form_id")
	private int formId; //PK
	
	@Column(name="creation_form_ts")
	private Timestamp creationFormTs;
	
	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public Timestamp getCreationFormTs() {
		return creationFormTs;
	}

	public void setCreationFormTs(Timestamp creationFormTs) {
		this.creationFormTs = creationFormTs;
	}

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", newFormTimestamp=" + creationFormTs + "]";
	}
	
}
