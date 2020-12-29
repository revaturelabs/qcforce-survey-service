package com.revature.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * This service is responsible for parsing a given CSV as a MultipartFile, and
 * returning a List<String> containing email addresses within the file.
 */
@Service
public class CSVParserImpl implements CSVParser {

	/**
	 * This method takes in a MultipartFile containing emails separated by either a
	 * comma and/or a space. It then returns a list containing each email as a
	 * string.
	 * 
	 * @param file the CSV to parse as a MultipartFile
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	@Override
	public List<String> parseFileForEmails(MultipartFile file)
			throws IOException, FileNotFoundException, IllegalArgumentException {

		if (file.isEmpty()) {
			throw new IllegalArgumentException("Cannot parse empty file");
		}
		return Arrays.asList(new String(file.getBytes()).split("[,\\ ]"));
	}

}
