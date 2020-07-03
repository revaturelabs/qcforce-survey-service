package com.revature.service;

import java.util.List;

import com.revature.entity.Response;

public interface ResponseService {

	public List<Response> getAllResponses();

	/*
	 * public List<Response> getResponseByBatchName(String batchName);
	 */
	public List<Response> getResponseByFormId(int formId);

	public void saveResponse(Response response);

	public void updateResponse(Response response);

	public void deleteResponse(Response response);

}
