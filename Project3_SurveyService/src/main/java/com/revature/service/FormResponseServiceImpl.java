package com.revature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.ChartData;
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
		return new ArrayList<String>(new LinkedHashSet<String>(formResponseRepo.getBatches()));
	}

	public List<FormResponse> getBatchForms(String batch) {
		return formResponseRepo.getBatchForms(batch);

	}

	@Override
	public List<String> getBatchWeeks() {
		return new ArrayList<String>(new LinkedHashSet<String>(formResponseRepo.getWeeks()));
	}

	@Override
	public List<FormResponse> getBatchByNameAndWeek(String batch, String week) {
		System.out.println("Batch: " + batch);
		System.out.println("Week: " + week);
		System.out.println(formResponseRepo.getBatchFormsbyWeek(batch, week));
		return formResponseRepo.getBatchFormsbyWeek(batch, week);
	}

	@Override
	public List<ChartData> getChartDataByBatch(String batch) {
		List<String> weeks = getBatchWeeks();
		List<ChartData> chartsByWeek = new ArrayList<ChartData>();
		for (String week : weeks) {
			chartsByWeek.add(calculateWeekNumbers(getBatchByNameAndWeek(batch, week), week));
		}
		return chartsByWeek;
	}

	@Override
	public ChartData getChartDataByBatchAndWeek(String batch, String week) {
		return calculateWeekNumbers(getBatchByNameAndWeek(batch, week), week);
	}

	@Override
	public ChartData calculateWeekNumbers(List<FormResponse> forms, String week) {

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
		for (FormResponse f : forms) {
			// Convert from collection
			List<String> ans = (List<String>) f.getAnswers();
			List<String> ques = (List<String>) f.getQuestions();
			// System.out.println("Form Id : " + f.getFormId());
			// System.out.println("Week : " + week);
			// Finds columns of interest
			for (int i = 0; i < ans.size(); i++) {
				try {
					double value = Double.parseDouble(ans.get(i).trim());
					// System.out.println("Value : " + value);
					data.set(i, data.get(i) + value);
					questions.add(ques.get(i));

				} catch (Exception e) {
					data.set(i, -1.0);
				}
			}
			// System.out.println("Sums : " + data.toString());
		}
		// System.out.println("Question size : " + forms.size());
		// Calculates average
		for (int j = 0; j < data.size(); j++) {
			data.set(j, data.get(j) / Double.parseDouble(forms.size() + ""));
		}
		ChartData chartData = new ChartData(week);
		int qIndex = 0;
		// Removes unnecessary values
		for (int i = data.size() - 1; i > -1; i--) {
			if (data.get(i) >= 0) {
				chartData.getData().put(questions.get(qIndex), data.get(i));
				qIndex++;
			}
		}
		// Returns data
		return chartData;
	}

}
