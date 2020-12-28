package com.revature.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CSVParserImpl implements CSVParser {

	@Override
	public List<String> parseFileForEmails(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TODO Create Parser for regular string of emails separated by comma? separated by space?

}
