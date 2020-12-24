package com.revature.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * This service receives requests from the distribution controller, and then
 * validates if the emails are correctly formatted and the batch id exists. If
 * the emails are invalid then the invalid emails will be returned. If the batch
 * id does not exist then an exception is thrown.
 */

@Service
public class DistributionServiceImpl implements DistributionService {
	/**
	 * TODO: Document after implementation
	 */
	@Override
	public List<String> sendEmailsByBatchId(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * TODO: Document after implementation
	 */
	@Override
	public List<String> sendEmailsByBatchIdAndCSV(int batchId, File csv) {
		// TODO Auto-generated method stub
		return null;
	}

}
