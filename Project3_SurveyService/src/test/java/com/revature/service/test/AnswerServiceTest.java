package com.revature.service.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.revature.entity.Answer;
import com.revature.service.AnswerServiceImpl;

@SpringBootTest
@Transactional
class AnswerServiceTest {

	@Autowired
	AnswerServiceImpl answerService;
	
	@Test
	void test() {
		List<String> result = answerService.getAnswerByQuestionString("What batch are you in?");
		System.out.println(result.toString());
	}
	
	@Test
	void test2() {
		List<String> result = answerService.getAnswerByQuestionString("Name (Optional)");
		System.out.println(result.toString());
	}

}
