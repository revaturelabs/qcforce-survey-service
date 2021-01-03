package com.revature.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service 
public interface AssociateService {
	
	public HashMap<String, Integer> getAssociatesByBatchId(String batchId);

}
