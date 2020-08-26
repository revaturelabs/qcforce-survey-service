package com.revature.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.model.Form;

/**
 * Used for performing CRUD operations on a Form Collection in MongoDB database.
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
public interface FormRepo extends MongoRepository<Form, Integer> {

	/**Retrieves a Form by a particular form id
	 * @param id a form id
	 * @return a particular Form
	 */
	public Form findById(int id);

	/**Retrieves a Form by a particular source id
	 * @param sourceId a Form's sourceId
	 * @return a particular Form by sourceId
	 */
	public Form findBySourceId(String sourceId);
}
