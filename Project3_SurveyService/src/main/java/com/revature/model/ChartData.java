package com.revature.model;

import java.util.HashMap;

public class ChartData {
	String label;

	HashMap<String, Double> data;

	public ChartData(String label) {
		super();
		this.label = label;
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

}
