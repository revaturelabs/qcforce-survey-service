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
	public ChartData getAllChartData() {
		List<String> weeks = getBatchWeeks();
		List<String> batches = getBatchNames();
		List<ChartData> charts = new ArrayList<ChartData>();
		for(String batch: batches) {
			for(String week: weeks) {
				charts.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), week, 1));
			}
		}
		ChartData result = new ChartData("All Batches Average");
		Form f = formRepo.findById(1);
		for (String key : f.getQuestions())
		{
			double sum=0;
			int offset=0;
			for (ChartData chart : charts)
			{	try {
					sum+=chart.getData().get(key);
				}catch(Exception e) {
					offset++;
				}
			}
			result.getData().put(key,sum/(charts.size()-offset));
		}
			return result;
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
		
		for (int j = 0; j < data.size(); j++) {
			data.set(j, 0.0);
		}
		// Cycle through forms for the week
		Form form = formRepo.findById(id);
		List<String> ques = form.getQuestions();
		for (FormResponse f : forms) {
			// Convert from collection
			List<Double> weights = f.getWeights();
			
			System.out.println("Form Id : " + f.getFormId());
			System.out.println("Week : " + week);
			
			// Finds columns of interest

				for (int i = 0; i < weights.size(); i++) {
					double value = weights.get(i);
					
					if(weights.get(i) == -100.0) {
						data.set(i, -100.0);
					}else {
						data.set(i, data.get(i) + value);
					}
			}
			System.out.println("Sums : " + data.toString());
			System.out.println("Weights: "+ weights.toString());
		}

		System.out.println("Question size : " + forms.size());
		
		// Calculates average
		for (int j = 0; j < data.size(); j++) {
			data.set(j, data.get(j) / Double.parseDouble(forms.size() + ""));
		}
		ChartData chartData = new ChartData(week);
		// Removes unnecessary values
		System.out.println(ques.toString());
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i) > -2.0) {
				chartData.getData().put(ques.get(i), data.get(i));
			}
		}
		// Returns data
		return chartData;
	}

}
