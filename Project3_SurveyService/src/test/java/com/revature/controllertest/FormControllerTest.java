package com.revature.controllertest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FormControllerTest {

	@Autowired
	private WebTestClient wtc;
	
	@Test
	void testGetBatchNames() {
		wtc.get().uri("/batch/list").exchange().expectStatus().is2xxSuccessful();
		
	}

	@Test
	void testGetBatchWeeks() {
		wtc.get().uri("/batch/weeks").exchange().expectStatus().is2xxSuccessful();
	}

	@Test
	void testGetChartDataByWeek() {
		wtc.get().uri("/batch/chartdatabatch/week/3").exchange().expectStatus().isOk();
	}

	@Test
	void testGetChartDataByBatchName() {
		wtc.get().uri("/batch/chartdatabatch/name/Wei").exchange().expectStatus().isOk();
	}

	@Test
	void testGetAllChartData() {
		wtc.get().uri("/batch/chartdatabatch/all").exchange().expectStatus().isOk();
	}

	@Test
	void testGetBatchesAverageByWeek() {
		wtc.get().uri("/batch/chartdatabatch/all/week/5").exchange().expectStatus().isOk();
	}

	@Test
	void testGetWeeksAverageByBatch() {
		wtc.get().uri("/batch/chartdatabatch/all/batch/2005-Nick").exchange().expectStatus().isOk();
	}

	@Test
	void testGetChartDataByBatchNameAndWeek() {
		wtc.get().uri("/batch/chartdatabatch/Ana/7").exchange().expectStatus().isOk();
	}

}