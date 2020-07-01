package com.revature.service;

import java.util.List;

import com.revature.model.Form;

public interface FormService {
	
	public List<Form> getAllForms();
	
	public Form getFormById(int formId);
	
	public Form getFormBySource(String sourceId);
	
	public void createForm(Form form);
	
	public void updateForm(Form form);
	
	public void deleteForm(Form form);


}
