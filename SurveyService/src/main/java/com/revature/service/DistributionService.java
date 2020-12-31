package com.revature.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.revature.response.EmailResponse;

public interface DistributionService {

	public List<String> sendEmailsByBatchId(int batchId, int surveyId);
	
	public EmailResponse sendEmailsByBatchIdAndCSV(int batchId, int surveyId, MultipartFile csv);

}
