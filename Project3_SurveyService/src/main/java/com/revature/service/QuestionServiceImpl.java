package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Question;
import com.revature.repo.QuestionRepo;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private QuestionRepo questionRepo;
	
	@Autowired
	public void setQuestionRepo(QuestionRepo questionRepo) {
		this.questionRepo=questionRepo;
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionRepo.findAll();
	}

	@Override
	public Question getQuestionByFormId(int formId) {
		return questionRepo.findById(formId).get();
	}

	@Override
	public Question getQuestionByQuestionType(String questionType) {
		return questionRepo.findByQuestionType(questionType).get(index);
	}

	@Override
	public void createQuestion(Question question) {
		questionRepo.save(question);

	}

	@Override
	public void updateQuestion(Question question) {
		questionRepo.findById(question.getQuestionId()).ifPresent((existingQuestion) -> questionRepo.save(question));

	}

	@Override
	public void deleteQuestion(Question question) {
		questionRepo.delete(question);

	}

}
