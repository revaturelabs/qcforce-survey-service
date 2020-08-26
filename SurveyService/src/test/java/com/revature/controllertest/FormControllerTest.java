package com.revature.controllertest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

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