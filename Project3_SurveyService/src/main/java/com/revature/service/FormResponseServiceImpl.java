package com.revature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.FormResponse;
import com.revature.repo.FormResponseRepo;

@Service
public class FormResponseServiceImpl implements FormResponseService {

	FormResponseRepo formResponseRepo;

	@Autowired
	public void setFormResponseRepo(FormResponseRepo formResponseRepo) {
		this.formResponseRepo = formResponseRepo;
	}

	@Override
	public List<FormResponse> findAll() {
		return formResponseRepo.findAll();
	}

	@Override
	public FormResponse getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormResponse findById(Integer id) {
		return formResponseRepo.findById(id).get();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(FormResponse entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(FormResponse formResponse) {
		formResponseRepo.save(formResponse);
	}

	@Override
	public List<String> getBatchNames() {
		List<FormResponse> fr = findAll();
		List<String> batches = new ArrayList<String>();
		for (FormResponse res : fr) {
			System.out.println(res.getQuestionsString());
			String[] questions = res.getQuestionsString().split("::");
			int index = Arrays.asList(questions).indexOf("What batch are you in?");
			System.out.println("Index : " + index);
			System.out.println(res.getAnswersString());
			String[] answers = res.getAnswersString().split("::");
			batches.add(answers[index]);
		}
		Set<String> set = new LinkedHashSet<String>(batches);
		return new ArrayList<String>(set);
	}

}
