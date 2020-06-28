package com.revature.service;

import java.util.List;

import com.revature.model.Question;


public interface QuestionService {
	
	public List<Question> getAllQuestion();

	public Question getQuestionByFormId(int formId);
	
	public List<Question> getQuestionByQuestionType(String questionType);
	
	public void createQuestion(Question question);

	public void updateQuestion(Question question);

	public void deleteQuestion(Question question);

}
