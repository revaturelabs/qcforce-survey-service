package com.revature.service;

import java.util.List;

import com.revature.entity.Response;

public interface ResponseService {

	public List<Response> getAllResponses();

	public Boolean checkIfResponseExist(int formId);

	public void saveResponse(Response response);

	public void updateResponse(Response response);

	public void deleteResponse(Response response);

	List<Response> getByWeekAndBatch(String batchName, String week);

	List<Response> getByBatch(String batchName);

	List<Response> getByWeek(String week);
}
