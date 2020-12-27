package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.service.CSVParser;
@SpringBootTest
class CSVParserTest {
	@Autowired
	CSVParser csvParser;

	/**
	 * Tests CSVParser service, creates a valid CSV and inputs it as parameter, asserts that it does not
	 * throw an exception
	 */
	@Test
	void testCSVParser_withValidCSVFormattedFile() {
		FileWriter writer = null;

		try {

			writer = new FileWriter("emails.csv");
			writer.append("acacia.holliday@revature.net");
			writer.append(',');
			writer.append("marc.roy@revature.net");
			writer.append(',');
			writer.append("ksenia.milstein@revature.net");
			writer.append("zachary.leonardo@revature.net");
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		File csvFile = new File("emails.csv");
		assertDoesNotThrow(() -> csvParser.parseFileForEmails(csvFile));

	}
	/**
	 * Tests CSVParser service with empty string, should throw a FileNotFoundException
	 */
	@Test
	void testCVSParser_withCSVFileNotFound() {
		File csvFile = new File("");
		assertThrows(FileNotFoundException.class, () -> csvParser.parseFileForEmails(csvFile));

	}

}
