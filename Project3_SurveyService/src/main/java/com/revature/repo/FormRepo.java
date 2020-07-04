package com.revature.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.model.Form;

public interface FormRepo extends MongoRepository<Form, Integer> {

	public Form findById(int id);

	public Form findBySourceId(String sourceId);
}
