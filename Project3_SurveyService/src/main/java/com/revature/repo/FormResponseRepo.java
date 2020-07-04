package com.revature.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.revature.model.FormResponse;

public interface FormResponseRepo extends MongoRepository<FormResponse, Integer> {

	public FormResponse findByResponseId(int responseID);

	public List<FormResponse> findByBatch(String batch);

	public List<FormResponse> findByWeek(String week);

	public List<FormResponse> findByBatchAndWeek(String batch, String week);

}