package com.revature.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.revature.model.FormResponse;

/**
 * Used for performing CRUD operations on a FormResponse Collection in MongoDB database.
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
public interface FormResponseRepo extends MongoRepository<FormResponse, Integer> {

	/**Retrieves a {@link FormResponse} by a responseID
	 * @param responseID a form response's responseID
	 * @return a FormResponse
	 */
	public FormResponse findByResponseId(int responseID);

	/**Retrieves a {@link List}{@link FormResponse} by a particular batch name
	 * @param batch a batch name
	 * @return a list of FormResponse with the same batch name
	 */
	public List<FormResponse> findByBatch(String batch);

	/**Retrieves a {@link List}{@link FormResponse} by a particular week
	 * @param week a week
	 * @return a list of FormResponse with the week
	 */
	public List<FormResponse> findByWeek(String week);

	/**Retrieves a {@link List}{@link FormResponse} by a particular batch name and week
	 * @param batch a batch name
	 * @param week a week
	 * @return a list of FormResponse with the week
	 */
	public List<FormResponse> findByBatchAndWeek(String batch, String week);

}