package com.revature.service;

import java.io.File;
import java.util.List;

public interface DistributionService {
	
	public List<String> sendEmailsByBatchId(int batchId);
	
	public List<String> sendEmailsByBatchIdAndCSV(int batchId, File csv);

}
