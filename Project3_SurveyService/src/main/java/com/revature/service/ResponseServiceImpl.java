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

	@Override
	public Boolean checkIfResponseExist(int formId) {
		if (responseRepo.findById(formId).isPresent()) {
			return true;
		} else {
			return false;
		}
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

	@Override
	public List<Response> getByWeekAndBatch(String batchName, String week) {
		return responseRepo.getByWeekAndBatch(batchName, week);
	}

	@Override
	public List<Response> getByBatch(String batchName) {
		return responseRepo.getByBatch(batchName);
	}

	@Override
	public List<Response> getByWeek(String week) {
		return responseRepo.getByWeek(week);
	}

}
