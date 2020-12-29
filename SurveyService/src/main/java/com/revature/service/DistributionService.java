package com.revature.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface DistributionService {

	public List<String> sendEmailsByBatchId(int batchId, int surveyId);
	
	public List<String> sendEmailsByBatchIdAndCSV(int batchId, int surveyId, MultipartFile csv);

}
