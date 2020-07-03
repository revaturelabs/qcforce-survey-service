package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Answer;
import com.revature.service.AnswerService;

@RestController
public class AnswerController {

	private AnswerService answerService;

	@Autowired
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	@GetMapping("/answer")
	public List<Answer> getAllForms() {
		return answerService.getAllAnswers();
	}

	@GetMapping("/Answer/{responseId}")
	public List<Answer> getAnswerByResponseId(@PathVariable("responseId") int responseId) {
		return (List<Answer>) answerService.getAnswerByResponseId(responseId);
	}

	@GetMapping("/Answer/{questionId}")
	public List<Answer> getAnswerByQuestionId(@PathVariable("questionId") int questionId) {
		return (List<Answer>) answerService.getAnswerByQuestionId(questionId);
	}

	@PostMapping("/answer")
	public String createAnswer(@RequestBody Answer answer) {
		answerService.createAnswer(answer);
		return "Answer successfully created";
	}

	@PutMapping("/answer")
	public String updateAnswer(@RequestBody Answer answer) {
		answerService.updateAnswer(answer);
		return "Answer successfully updated";
	}

}
