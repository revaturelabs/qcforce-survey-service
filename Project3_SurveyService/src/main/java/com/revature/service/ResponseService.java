package com.revature.service;

import java.util.List;
import com.revature.model.Response;

public interface ResponseService {

	public List<Response> getAllResponses();

	public List<Response> getResponseByBatchName(String batchName);
	
	public Response getResponseByFormId(int formId);

	public void createResponse(Response response);

	public void updateResponse(Response response);

	public void deleteResponse(Response response);

}
