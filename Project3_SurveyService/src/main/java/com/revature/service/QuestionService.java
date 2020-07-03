package com.revature.service;

import java.util.List;

import com.revature.entity.Question;

public interface QuestionService {

	public List<Question> getAllQuestions();

	public Question getQuestionByFormId(int formId);

	/*
	 * public List<Question> getQuestionByQuestionType(String questionType);
	 */

	public void createQuestion(Question question);

	public void updateQuestion(Question question);

	public void deleteQuestion(Question question);

}
