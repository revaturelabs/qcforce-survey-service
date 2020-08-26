package com.revature.service;

import java.util.List;

import com.revature.model.ChartData;
import com.revature.model.FormResponse;

/**
 * Handles JPA operations between Java and a database.
 * 
 * @author Wei Wu
 * @author Jose Canela
 * @author Andres Toledo
 */
public interface FormResponseService {

	/**
	 * Retrieves a list of all {@link FormResponse}.
	 * 
	 * @return list of {@link FormResponse}
	 */
	public List<FormResponse> findAll();

	/**
	 * Gets {@link FormResponse} by id.
	 * 
	 * @param id represents a {@link FormResponse}.
	 * @return {@link FormResponse} by id.
	 */
	public FormResponse findById(Integer id);

	/**
	 * Gets {@link List}{@link String} of distinct batch names.
	 * 
	 * @return list of batch names.
	 */
	public List<String> getBatchNames();

	/**
	 * Gets {@link List}{@link String} of weeks.
	 * 
	 * @return list of weeks.
	 */
	public List<String> getBatchWeeks();

	/**
	 * Gets all form responses that belong to a particular batch on a particular
	 * week
	 * 
	 * @param batch name of the batch.
	 * @param week  name of the week.
	 * @return list of {@link FormResponse}
	 */
	public List<FormResponse> getBatchByNameAndWeek(String batch, String week);
	/**
	 * Saves a {@link FormResponse} to the database.
	 * 
	 * @param formResponse FormResponse object.
	 */
	public void save(FormResponse formResponse);

	/**
	 * Gets all forms for a given batch.
	 * @param batch name of the batch.
	 * @return list of {@link FormResponse}.
	 */
	public List<FormResponse> getBatchForms(String batch);

	/**
	 * Gets the average for every week for all questions of a given batch.
	 * 
	 * @param batch batch name.
	 * @return list of {@link ChartData}.
	 */
	public List<ChartData> getChartDataByBatch(String batch);

	/**
	 * Gets the average for all questions belonging to a week for every batch.
	 * 
	 * @param week name of the week.
	 * @return list of {@link ChartData}.
	 */
	public List<ChartData> getChartDataByWeek(String week);

	/**
	 * Returns the average value for all questions over all weeks for all batches in
	 * the database.
	 * 
	 * @return a {@link ChartData} object representing merged data.
	 */
	public ChartData getAllChartData();

	/**
	 * Gets the average for all questions of all batches for a particular week.
	 * 
	 * @param week name of the week.
	 * @return a {@link ChartData} object representing merged data.
	 */
	public ChartData getBatchesAverageByWeek(String week);

	/**
	 * Gets the average for all questions over all weeks for a particular batch.
	 * 
	 * @param batch name of the batch.
	 * @return a {@link ChartData} object representing merged data.
	 */
	public ChartData getWeeksAverageByBatch(String batch);

	/**
	 * Calculates the average of all questions for a particular week.
	 * 
	 * @param forms list of forms submitted during the specified week.
	 * @param batch name of the current batch
	 * @param week  name of the current week
	 * @param id    represents the form that the data belongs to.
	 * @return a {@link ChartData} object representing merged data.
	 */
	public ChartData calculateWeekNumbers(List<FormResponse> forms, String batch, String week, int id);

	/**
	 * Calculates the average of the questions asked on a particular week for a
	 * particular batch.
	 * 
	 * @param batch name of the batch.
	 * @param week  name of the week.
	 * @return a a {@link ChartData} object representing merged data.
	 */
	public ChartData getChartDataByBatchAndWeek(String batch, String week);
}
