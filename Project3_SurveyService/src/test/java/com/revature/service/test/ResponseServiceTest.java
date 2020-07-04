package com.revature.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.entity.Response;
import com.revature.service.ResponseServiceImpl;

@SpringBootTest
@Transactional
class ResponseServiceTest {

	@Autowired
	ResponseServiceImpl responseService;

	@Test
	void test() {
		List<Response> results = responseService.getByWeekAndBatch("2003 Mar09 AP-UTA JwA Extended", "Week 3");
		for(Response re : results) {
			for(int i=0; i<re.getAnswers().size(); i++) {
				System.out.println(re.getAnswers().get(i).getAnswerString());
			}
		}
	}
	
	@Test
	void test2() {
		List<Response> results = responseService.getByWeek("Week 3");
		for(Response re : results) {
			for(int i=0; i<re.getAnswers().size(); i++) {
				System.out.println(re.getAnswers().get(i).getAnswerString());
			}
		}
	}
	
	@Test
	void test3() {
		List<Response> results = responseService.getByBatch("2003 Mar09 AP-UTA JwA Extended");
		for(Response re : results) {
			for(int i=0; i<re.getAnswers().size(); i++) {
				System.out.println(re.getAnswers().get(i).getAnswerString());
			}
		}
	}

}
