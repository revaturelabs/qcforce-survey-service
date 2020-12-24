package com.revature.service;

import java.util.List;

public interface DistributionService {
	
	public List<String> sendEmailsByBatchId(int batchId);

}
