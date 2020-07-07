package com.revature.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.model.Form;

/**
 * Used to performing CRUD operations on database.
 *  @author Anastasia Miagkii, Andres Toledo, Jose Canela, Monica Datta, Wei Wu, Zachary Reagin
 */
public interface FormRepo extends MongoRepository<Form, Integer> {

	public Form findById(int id);

	public Form findBySourceId(String sourceId);
}
