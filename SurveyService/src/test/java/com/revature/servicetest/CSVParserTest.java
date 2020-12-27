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

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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

	@Test
	void testCVSParser_withCSVFileNotFound() {
		File csvFile = new File("");
		assertThrows(FileNotFoundException.class, () -> csvParser.parseFileForEmails(csvFile));

	}

}
