package com.revature.service;

import java.io.File;
import java.util.List;

public interface CSVParser {
	
	public List<String> parseFileForEmails(File file);

}
