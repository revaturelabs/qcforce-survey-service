package com.revature.model;

import java.io.Serializable;

//@Entity
//@Table(name="form", schema="qcforce_survey")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "formId")

public class Form implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "form_id")
//	private int formId; // PK
//
//	@Column(name = "creation_form_ts")
//	private Timestamp creationFormTs;
//
//	@Column(name = "source_id")
//	private String sourceId;
//
//	@OneToMany(mappedBy = "form", targetEntity = Question.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Question> question = new ArrayList<Question>();
//
//	@OneToMany(mappedBy = "form", targetEntity = Response.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Response> response = new ArrayList<Response>();
//
//	Calendar calendar = Calendar.getInstance();
//	java.util.Date now = calendar.getTime();
//
//	public Form() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Form(String sourceId) {
//		this.sourceId = sourceId;
//		this.creationFormTs = new Timestamp(now.getTime());
//	}
//
//	public Form(int formId, Timestamp creationFormTs, List<Question> question, List<Response> response) {
//		super();
//		this.formId = formId;
//		this.creationFormTs = creationFormTs;
//		this.question = question;
//		this.response = response;
//	}
//
//	public Form(int formId) {
//		super();
//		this.formId = formId;
//	}
//
//	public int getFormId() {
//		return formId;
//	}
//
//	public void setFormId(int formId) {
//		this.formId = formId;
//	}
//
//	public Timestamp getCreationFormTs() {
//		return creationFormTs;
//	}
//
//	public void setCreationFormTs(Timestamp creationFormTs) {
//		this.creationFormTs = creationFormTs;
//	}
//
//	public List<Question> getQuestion() {
//		return question;
//	}
//
//	public void setQuestion(List<Question> question) {
//		this.question = question;
//	}
//
//	public List<Response> getResponse() {
//		return response;
//	}
//
//	public void setResponse(List<Response> response) {
//		this.response = response;
//	}
//
//	public String getSourceId() {
//		return sourceId;
//	}
//
//	public void setSourceId(String sourceId) {
//		this.sourceId = sourceId;
//	}
//
//	@Override
//	public String toString() {
//		return "Form [formId=" + formId + ", creationFormTs=" + creationFormTs + ", question=" + question
//				+ ", response=" + response + "]";
//	}

}