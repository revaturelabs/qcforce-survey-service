package com.revature.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface CSVParser {

	public List<String> parseFileForEmails(MultipartFile file);

}
