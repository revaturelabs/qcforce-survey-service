package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entity.Form;
import com.revature.repo.FormRepo;

@Service
public class FormServiceImpl implements FormService {
	
	private FormRepo formRepo;
	
	@Autowired
	public void setFormRepo(FormRepo formRepo) {
		this.formRepo=formRepo;
	}

	@Override
	public List<Form> getAllForms() {
		return formRepo.findAll();
	}

	@Override
	public Form getFormById(int formId) {
		return formRepo.findById(formId).get();
	}

	@Override
	public void createForm(Form form) {
		formRepo.save(form);

	}

	@Override
	public void updateForm(Form form) {
		formRepo.findById(form.getFormId()).ifPresent((existingForm) -> formRepo.save(form));

	}

	@Override
	public void deleteForm(Form form) {
		formRepo.delete(form);

	}
	
	@Override
	public Form getFormBySource(String sourceId) {
		List<Form> forms = getAllForms();
		int formCount =0;
		for(Form f : forms) {
			if(f.getSourceId().equals(sourceId))
			{
				return f;
			} else {
				formCount +=1;
			}
		}
		Form form =new Form(sourceId);
		form.setFormId(formCount);
		createForm(form);
		form = getFormBySource(sourceId);
		return form;
	}

}
