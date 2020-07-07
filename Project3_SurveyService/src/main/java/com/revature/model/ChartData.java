package com.revature.model;

import java.util.HashMap;

/**
 * This class represents the information for Chart Data.
 *  @author Anastasia Miagkii
 *  @author Andres Toledo
 *  @author Jose Canela
 *  @author Monica Datta
 *  @author Wei Wu 
 *  @author Zachary Reagin
 */
public class ChartData {
	
	/**
	 * The label or title describing the chart data to be displayed.
	 */
	private String label;

	/**
	 * The batch name associated with the chart data to be displayed.
	 */
	private String batch;

	/**
	 * A {@link HashMap} where every key represents a question and their corresponding value 
	 * represents the metric used to analyze the weights for the answers submitted for that 
	 * question.
	 */
	private HashMap<String, Double> data;

	/**
	 * Creates a ChartData object with a particular batch name and label .
	 * @param batch a batch name.
	 * @param label a label describing the chart data to be displayed.
	 */
	public ChartData(String batch, String label) {
		super();
		this.label = label;
		this.batch = batch;
		this.data = new HashMap<String, Double>();
	}

	/**
	 * Gets a {@link ChartData} object's label 
	 * @return a label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets a {@link ChartData} object's label 
	 * @param label a new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Gets a {@link ChartData} object's {@link HashMap} of data 
	 * @return a {@link HashMap} of questions and averaged data associated with each question.
	 */
	public HashMap<String, Double> getData() {
		return data;
	}

	/**
	 * Sets a {@link ChartData} object's {@link HashMap} of data
	 * @param data a {@link HashMap} of questions and averaged data associated with each question.
	 */
	public void setData(HashMap<String, Double> data) {
		this.data = data;
	}
	
	/**
	 * Gets a {@link ChartData} object's batch name 
	 * @return a batch name
	 */
	public String getBatch() {
		return batch;
	}

	/**
	 * Sets a {@link ChartData} object's batch name
	 * @param batch a batch name
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Override
	public String toString() {
		return "ChartData [getLabel()=" + getLabel() + ", getData()=" + getData() + "]";
	}
}
