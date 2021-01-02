package com.revature.service;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service 
public interface AssociateService {
	
	public Set<Integer> getAssociatesByBatchId(String batchId);

}
