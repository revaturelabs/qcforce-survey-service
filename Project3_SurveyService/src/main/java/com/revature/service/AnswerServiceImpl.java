package com.revature.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.entity.Answer;
import com.revature.repo.AnswerRepo;

@Service
public class AnswerServiceImpl implements AnswerService {

	private AnswerRepo answerRepo;
	
	@Autowired
	public void setAnswerRepo(AnswerRepo answerRepo) {
		this.answerRepo=answerRepo;
	}
	
	@Override
	public List<Answer> getAllAnswers() {
		return answerRepo.findAll();
	}

	@Override
	public Answer getAnswerByResponseId(int responseId) {
		return answerRepo.findById(responseId).get(); 
	}

	@Override
	public Answer getAnswerByQuestionId(int questionId) {
		return answerRepo.findById(questionId).get();
	}

	@Override
	public void createAnswer(Answer answer) {
		answerRepo.save(answer);

	}

	@Override
	public void updateAnswer(Answer answer) {
		answerRepo.findById(answer.getAnswerId()).ifPresent((existingAnswer) ->answerRepo.save(answer));

	}

	@Override
	public void deleteAnswer(Answer answer) {
		answerRepo.delete(answer);

	}

}
