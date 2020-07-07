package com.revature.model;

import java.util.HashMap;

/**
 * This class represents the information for Chart Data.
 * @author Anastasia Miagkii, Andres Toledo, Jose Canela, Monica Datta, Wei Wu, Zachary Reagin
 */
public class ChartData {
	
	String label;

	String batch;

	HashMap<String, Double> data;

	public ChartData(String batch, String label) {
		super();
		this.label = label;
		this.batch = batch;
		this.data = new HashMap<String, Double>();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public HashMap<String, Double> getData() {
		return data;
	}

	public void setData(HashMap<String, Double> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ChartData [getLabel()=" + getLabel() + ", getData()=" + getData() + "]";
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

}
