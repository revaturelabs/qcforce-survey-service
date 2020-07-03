package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entity.Response;
import com.revature.repo.ResponseRepo;

@Service
public class ResponseServiceImpl implements ResponseService {

	private ResponseRepo responseRepo;

	@Autowired
	public void setReponseRepo(ResponseRepo responseRepo) {
		this.responseRepo = responseRepo;
	}

	@Override
	public List<Response> getAllResponses() {
		return responseRepo.findAll();
	}

	/*
	 * @Override public List<Response> getResponseByBatchName(String batchName) {
	 * return responseRepo.findByBatchName(batchName); }
	 */
	@Override
	public List<Response> getResponseByFormId(int formId) {
		return (List<Response>) responseRepo.findById(formId).get();
	}

	@Override
	public void saveResponse(Response response) {
		responseRepo.save(response);

	}

	@Override
	public void updateResponse(Response response) {
		responseRepo.findById(response.getId()).ifPresent((existingResponse) -> responseRepo.save(response));
	}

	@Override
	public void deleteResponse(Response response) {
		responseRepo.delete(response);

	}

}
