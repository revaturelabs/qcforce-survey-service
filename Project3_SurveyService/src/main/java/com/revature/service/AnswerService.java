package com.revature.service;

import java.util.List;

import com.revature.entity.Answer;

public interface AnswerService {
	
	public List<Answer> getAllAnswers();

	public Answer getAnswerByResponseId(int responseId);
	
	public Answer getAnswerByQuestionId(int questionId);
	
	public void createAnswer(Answer answer);

	public void updateAnswer(Answer answer);

	public void deleteAnswer(Answer answer);
	
	public List<String> getAnswerByQuestionString(String qString);

}
