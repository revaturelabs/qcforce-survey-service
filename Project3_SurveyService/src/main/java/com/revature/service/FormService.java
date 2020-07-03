package com.revature.service;

import java.util.List;

import com.revature.entity.Form;

public interface FormService {
	
	public List<Form> getAllForms();
	
	public Form getFormById(int formId);
	
	public Form getFormBySource(String sourceId);
	
	public void createForm(Form form);
	
	public void updateForm(Form form);
	
	public void deleteForm(Form form);


}
