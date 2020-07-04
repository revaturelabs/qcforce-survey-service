package com.revature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.DistinctIterable;
import com.revature.model.ChartData;
import com.revature.model.Form;
import com.revature.model.FormResponse;
import com.revature.repo.FormRepo;
import com.revature.repo.FormResponseRepo;

@Service
public class FormResponseServiceImpl implements FormResponseService {

	FormResponseRepo formResponseRepo;

	FormRepo formRepo;

	MongoTemplate mongoTemplate;

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
		ArrayList<String> result = new ArrayList<String>();
		DistinctIterable<String> values = getUniqueTask();
		values.into(result);
		return result;
	}

	public DistinctIterable<String> getUniqueTask() {

		return mongoTemplate.getCollection("formscollection").distinct("batch", String.class);
	}

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
			chartsByWeek.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), week, 1));
		}
		return chartsByWeek;
	}
	
	@Override
	public List<ChartData> getChartDataByWeek(String week) {
		List<String> batches = getBatchNames();
		List<ChartData> chartsByBatch = new ArrayList<ChartData>();
		for (String batch : batches) {
			chartsByBatch.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), week, 1));
		}
		return chartsByBatch;
	}
	
	@Override
	public List<ChartData> getAllChartData() {
		List<String> weeks = getBatchWeeks();
		List<ChartData> charts = new ArrayList<ChartData>();
		
			for(String week: weeks) {
				charts.add(calculateWeekNumbers(findAll(), week, 1));
		}
		return charts;
	}

	@Override
	public ChartData getChartDataByBatchAndWeek(String batch, String week) {
		return calculateWeekNumbers(getBatchByNameAndWeek(batch, week), week, 1);
	}

	@Override
	public ChartData calculateWeekNumbers(List<FormResponse> forms, String week, int id) {

		try {
			forms.get(0).getAnswers().size();
		} catch (Exception e) {
			return new ChartData(week);
		}
		ArrayList<Double> data = new ArrayList<Double>(Arrays.asList(new Double[forms.get(0).getAnswers().size()]));

		ArrayList<String> questions = new ArrayList<String>();
		
		for (int j = 0; j < data.size(); j++) {
			data.set(j, 0.0);
		}
		// Cycle through forms for the week
		Form form = formRepo.findById(id);
		for (FormResponse f : forms) {
			// Convert from collection
			//List<String> ans = f.getAnswers();
			List<Double> weights = f.getWeights();
			List<String> ques = form.getQuestions();
			
			System.out.println("Form Id : " + f.getFormId());
			System.out.println("Week : " + week);
			
			// Finds columns of interest
			//for (int i = 0; i < ans.size(); i++) {
				for (int i = 0; i < weights.size(); i++) {
					double value = weights.get(i);
					
					if(weights.get(i) == -100.0) {
						data.set(i, -2.0);
					}else {
						data.set(i, data.get(i) + value);
						questions.add(ques.get(i));
					}
				/*
				try {
					double value = Double.parseDouble(ans.get(i).trim());
					
					// System.out.println("Value : " + value);
					data.set(i, data.get(i) + value);
					questions.add(ques.get(i));

				} catch (Exception e) {
					data.set(i, -1.0);
				}
				*/
			}
			System.out.println("Sums : " + data.toString());
		}

		System.out.println("Question size : " + forms.size());
		
		// Calculates average
		for (int j = 0; j < data.size(); j++) {
			data.set(j, data.get(j) / Double.parseDouble(forms.size() + ""));
		}
		ChartData chartData = new ChartData(week);
		int qIndex = 0;
		// Removes unnecessary values
		for (int i = data.size() - 1; i > -1; i--) {
			if (data.get(i) >= -1.0) {
				chartData.getData().put(questions.get(qIndex), data.get(i));
				qIndex++;
			}
		}
		// Returns data
		return chartData;
	}

}
