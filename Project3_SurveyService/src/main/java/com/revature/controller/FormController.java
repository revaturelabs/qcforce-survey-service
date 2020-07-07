package com.revature.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.revature.model.ChartData;
import com.revature.service.FormResponseService;

/**
 * Controls all endpoints related to FormController
 * 
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */

@RestController
@CrossOrigin
public class FormController {
	/**
	 * Creates an instance of {@link formResponseService} used to send data.
	 */
	private FormResponseService formResponseService;

	/**
	 * Initializes all services.
	 * 
	 * @param formResponseService sets from bean of type {@link formResponseService}.
	 */
	@Autowired
	public void setFormResponseService(FormResponseService formResponseService) {
		this.formResponseService = formResponseService;
	}

	/**
	 * Retrieves a List of Batch names from the formResponseService
	 * 
	 * @return a List of Batch Names
	 */
	@GetMapping("/batch/list")
	public List<String> getBatchNames() {
		return formResponseService.getBatchNames();
	}

	/**
	 * Retrieves a List of Batches based on a week from the formResponseService
	 * 
	 * @return a List of Batches according to all the weeks
	 */
	@GetMapping("/batch/weeks")
	public List<String> getBatchWeeks() {
		return formResponseService.getBatchWeeks();
	}

	/**
	 * Retrieves a List of ChartData by batch week from the formResponseService
	 * 
	 * @param batchWeek String which represents the name of a week
	 * @return a List of chart data based on batchWeek
	 */
	@GetMapping("/batch/chartdatabatch/week/{week}")
	public List<ChartData> getChartDataByWeek(@PathVariable(name = "week") String batchWeek) {
		return formResponseService.getChartDataByWeek(batchWeek);
	}

	/**
	 * Retrieves a List of ChartData by batch name from the formResponseService
	 * 
	 * @param batchName String which represents the name of a batch
	 * @return a List of chart data based on batchName
	 */
	@GetMapping("/batch/chartdatabatch/name/{name}")
	public List<ChartData> getChartDataByBatchName(@PathVariable(name = "name") String batchName) {
		return formResponseService.getChartDataByBatch(batchName);
	}

	/**
	 * Retrieves all the ChartData from the formResponseService
	 * 
	 * @return all the Chart Data from the formResponseService
	 */
	@GetMapping("/batch/chartdatabatch/all")
	public ChartData getAllChartData() {
		return formResponseService.getAllChartData();
	}

	/**
	 * Retrieves the ChartData based on all the weeks of all batches from the formResponseService
	 * 
	 * @param week String which represents the week of all batches
	 * @return the average of all the batches by week
	 */
	@GetMapping("/batch/chartdatabatch/all/week/{week}")
	public ChartData getBatchesAverageByWeek(@PathVariable(name = "week") String week) {
		return formResponseService.getBatchesAverageByWeek(week);
	}
	/**
	 * Retrieves the ChartData of all batches from the formResponseService
	 * 
	 * @param batch String which represents the batch name
	 * @return the average of all the batches
	 */
	@GetMapping("/batch/chartdatabatch/all/batch/{batch}")
	public ChartData getWeeksAverageByBatch(@PathVariable(name = "batch") String batch) {
		return formResponseService.getWeeksAverageByBatch(batch);
	}
	/**
	 * Retrieves the ChartData of specific batch by name and by specific week from the formResponseService
	 * 
	 * @param batchName String which represents the batch name
	 * @return the chart data of a specific batch by a specific week
	 */
	@GetMapping("/batch/chartdatabatch/{name}/{week}")
	public ChartData getChartDataByBatchNameAndWeek(@PathVariable(name = "name") String batchName,
			@PathVariable(name = "week") String batchweek) {
		return formResponseService.getChartDataByBatchAndWeek(batchName, batchweek);
	}

}
