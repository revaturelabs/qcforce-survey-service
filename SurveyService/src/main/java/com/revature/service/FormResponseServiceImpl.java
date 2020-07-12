package com.revature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.DistinctIterable;
import com.revature.logger.AppLogger;
import com.revature.model.ChartData;
import com.revature.model.Form;
import com.revature.model.FormResponse;
import com.revature.repo.FormRepo;
import com.revature.repo.FormResponseRepo;

/**
 * Used to manipulate data recovered from a MongoDB and transform it into
 * valuable information that will get returned to a request.
 * 
 * @author Wei Wu
 * @author Jose Canela
 * @author Andres Toledo
 * @author Monica Datta
 * @author Anastasia Miagkii
 * @author Zachary Reagin
 */
@Service
public class FormResponseServiceImpl implements FormResponseService {
	/**
	 * Instance of FormResponseRepo.
	 */
	private FormResponseRepo formResponseRepo;
	/**
	 * Instance of FormRepo.
	 */
	private FormRepo formRepo;
	/**
	 * Instance of MongoTemplate.
	 */
	private MongoTemplate mongoTemplate;

	@Autowired
	public void setFormResponseRepo(FormResponseRepo formResponseRepo) {
		this.formResponseRepo = formResponseRepo;
	}

	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Autowired
	public void setFormRepo(FormRepo formRepo) {
		this.formRepo = formRepo;
	}

	@Override
	public List<FormResponse> findAll() {
		return formResponseRepo.findAll();
	}

	@Override
	public FormResponse findById(Integer id) {
		return formResponseRepo.findById(id).get();
	}

	@Override
	public void save(FormResponse formResponse) {
		formResponseRepo.save(formResponse);
	}

	@Override
	public List<String> getBatchNames() {
		ArrayList<String> result = new ArrayList<String>();
		DistinctIterable<String> values = getUniqueTask();
		values.into(result);
		return result;
	}

	/**
	 * Gets distinct batches from the database.
	 * 
	 * @return an iterable array of distinct batches.
	 */
	public DistinctIterable<String> getUniqueTask() {

		return mongoTemplate.getCollection("formscollection").distinct("batch", String.class);
	}

	@Override
	public List<FormResponse> getBatchForms(String batch) {
		return formResponseRepo.findByBatch(batch);
	}

	@Override
	public List<String> getBatchWeeks() {
		List<String> result = new ArrayList<String>();
		mongoTemplate.getCollection("formscollection").distinct("week", String.class).into(result);
		return result;
	}

	@Override
	public List<FormResponse> getBatchByNameAndWeek(String batch, String week) {
		System.out.println("Batch: " + batch);
		System.out.println("Week: " + week);
		return formResponseRepo.findByBatchAndWeek(batch, week);
	}

	@Override
	public List<ChartData> getChartDataByBatch(String batch) {
		List<String> weeks = getBatchWeeks();
		List<ChartData> chartsByWeek = new ArrayList<ChartData>();
		for (String week : weeks) {
			chartsByWeek.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), batch, week, 1));
		}
		return chartsByWeek;
	}

	@Override
	public List<ChartData> getChartDataByWeek(String week) {
		List<String> batches = getBatchNames();
		List<ChartData> chartsByBatch = new ArrayList<ChartData>();
		for (String batch : batches) {
			chartsByBatch.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), batch, week, 1));
		}
		return chartsByBatch;
	}

	@Override
	public ChartData getAllChartData() {
		List<String> weeks = getBatchWeeks();
		List<String> batches = getBatchNames();
		List<ChartData> charts = new ArrayList<ChartData>();
		for (String batch : batches) {
			for (String week : weeks) {
				charts.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), batch, week, 1));
			}
		}
		return mergeChartData(charts, "Average for all batches across all weeks.");
	}

	@Override
	public ChartData getBatchesAverageByWeek(String week) {
		List<String> batches = getBatchNames();
		List<ChartData> charts = new ArrayList<ChartData>();
		for (String batch : batches) {
			charts.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), batch, week, 1));
		}
		return mergeChartData(charts, "Average for " + week + " for all batches.");

	}

	@Override
	public ChartData getWeeksAverageByBatch(String batch) {
		List<String> weeks = getBatchWeeks();
		List<ChartData> charts = new ArrayList<ChartData>();
		for (String week : weeks) {
			charts.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), batch, week, 1));
		}
		return mergeChartData(charts, "Average of all weeks for batch :" + batch);
	}

	/**
	 * Calculates the average of all questions for a given list of ChartData.
	 * 
	 * @param charts list of ChartData that needs to be merged.
	 * @param label  description for the result.
	 * @return ChartData representing the average of all questions.
	 */
	private ChartData mergeChartData(List<ChartData> charts, String label) {
		// Instantiate a ChartData object representing the average of all questions.
		ChartData result = new ChartData("", label);
		Form f = formRepo.findById(1);
		//For every question (every key in the ChartData object),
		for (String key : f.getQuestions()) {
			double sum = 0;
			//Instantiate an offset counter in order to account for the fact that some ChartData objects
			//may not have the same list of questions being asked
			int offset = 0;
			//For every ChartData object in the list of ChartData  that needs to be merged
			for (ChartData chart : charts) {
				try {
					//Sum the values for the particular question for each ChartData object
					sum += chart.getData().get(key);
				} catch (Exception e) {
					AppLogger.log.error("mergeChartData: " + e.getMessage());
					//If a ChartData object doesn't have answers to a particular question,
					// one increments the offset
					offset++;
				}
			}
			//Calculates the average for all questions
			result.getData().put(key, sum / (charts.size() - offset));
		}
		return result;
	}

	@Override
	public ChartData getChartDataByBatchAndWeek(String batch, String week) {
		return calculateWeekNumbers(getBatchByNameAndWeek(batch, week), batch, week, 1);
	}

	@Override
	public ChartData calculateWeekNumbers(List<FormResponse> forms, String batch, String week, int id) {

		try {
			//Validates if a list of for responses has something in it
			forms.get(0).getAnswers().size();
		} catch (Exception e) {
			//If there aren't any elements within the list of form responses, 
			//then create a new ChartData object pertaining to a particular batch and week.
			return new ChartData(batch, week);
		}
		//Instantiate a list of doubles pertaining to the averages for answers to questions displayed on a chart  
		ArrayList<Double> data = new ArrayList<Double>(Arrays.asList(new Double[forms.get(0).getAnswers().size()]));
		//Initially set the all averages to zero
		for (int j = 0; j < data.size(); j++) {
			data.set(j, 0.0);
		}
		
		//Instantiate a list of questions from the survey
		Form form = formRepo.findById(id);
		List<String> ques = form.getQuestions();
		// Cycle through form responses (collections) for the week
		for (FormResponse f : forms) {
			//Retrieve the weight from the form response collection
			List<Double> weights = f.getWeights();

			// Finds columns of interest
			for (int i = 0; i < weights.size(); i++) {
				double value = weights.get(i);
				//If the weight for a particular answer to a particular question is -100.0,
				if (weights.get(i) == -100.0) {
					//Set the sum to -100.0. This will be used to filter out data that's will not be displayed.
					data.set(i, -100.0);
				} else {
					//Set the sum pertaining that question
					data.set(i, data.get(i) + value);
				}
			}
			
			AppLogger.log.info("calculateWeekNumbers: Sums =  " + data.toString());
			AppLogger.log.info("calculateWeekNumbers: Weights = " + weights.toString());
		}

		AppLogger.log.info("calculateWeekNumbers: Number of form responses = " + forms.size());

		// Calculates average for every questions on a particular week
		for (int j = 0; j < data.size(); j++) {
			data.set(j, data.get(j) / Double.parseDouble(forms.size() + ""));
		}
		//Instantiates the a ChartData object that will be used to display averaged data
		ChartData chartData = new ChartData(batch, week);
	
		AppLogger.log.info("calculateWeekNumbers: list of question - " + ques.toString());
		// Removes unnecessary averages values
		for (int i = 0; i < data.size(); i++) {
			//If an average value average for a particular question is greater than -1 
			if (data.get(i) > -2.0) {
				// Input a mapping of a question its corresponding average into the ChartData object
				chartData.getData().put(ques.get(i), data.get(i));
			}
		}
		// Returns the averaged data
		return chartData;
	}

}
