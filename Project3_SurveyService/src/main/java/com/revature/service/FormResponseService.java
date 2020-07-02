package com.revature.service;

import java.util.List;

import com.revature.model.FormResponse;

public interface FormResponseService {

	public List<FormResponse> findAll();

	public FormResponse getOne(Integer id);

	public FormResponse findById(Integer id);

	public List<String> getBatchNames();

	public long count();

	public void deleteById(Integer id);

	public void delete(FormResponse entity);

	public void deleteAll();

	public void save(FormResponse formResponse);

}
