package com.revature.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entity.Question;
import com.revature.service.QuestionService;

@RestController
public class QuestionController {
	private QuestionService questionService;

	@Autowired
	public void setquestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping("/question")
	public List<Question> getAllForms() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/question/{formId}")
	public List<Question> getQuestionByFormId(@PathVariable("formId") int formId) {
		return (List<Question>) questionService.getQuestionByFormId(formId);
	}

	/*
	 * @GetMapping("/question/{questionType}") public List<Question>
	 * getQuestionByQuestionType(@PathVariable("questionType")String questionType) {
	 * return (List<Question>)
	 * questionService.getQuestionByQuestionType(questionType); }
	 */

	@PostMapping("/question")
	public String createQuestion(@RequestBody Question question) {
		questionService.createQuestion(question);
		return "Question successfully created";
	}

	@DeleteMapping("/Question/{questionId}")
	public String deleteQuestion(@PathParam("questionId") int questionId) {
		Question question = new Question();
		question.setId(questionId);
		questionService.deleteQuestion(question);
		return "Form successfully deleted";
	}

}
