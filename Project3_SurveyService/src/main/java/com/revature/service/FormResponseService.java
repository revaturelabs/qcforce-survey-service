package com.revature.service;

import java.util.List;

import com.revature.model.ChartData;
import com.revature.model.FormResponse;

public interface FormResponseService {

	public List<FormResponse> findAll();

	public FormResponse getOne(Integer id);

	public FormResponse findById(Integer id);

	public List<String> getBatchNames();

	public List<String> getBatchWeeks();

	public List<FormResponse> getBatchByNameAndWeek(String batch, String week);

	public long count();

	public void deleteById(Integer id);

	public void delete(FormResponse entity);

	public void deleteAll();

	public void save(FormResponse formResponse);

	public List<FormResponse> getBatchForms(String batch);

	public List<ChartData> getChartDataByBatch(String batch);
	
	public List<ChartData> getChartDataByWeek(String week);
	
	public List<ChartData> getAllChartData();

	public ChartData calculateWeekNumbers(List<FormResponse> forms, String week, int id);

	public ChartData getChartDataByBatchAndWeek(String batch, String week);
}
