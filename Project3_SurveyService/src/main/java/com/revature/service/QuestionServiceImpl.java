package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entity.Question;
import com.revature.repo.QuestionRepo;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	private QuestionRepo questionRepo;

	@Autowired
	public void setQuestionRepo(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	@Override
	public List<Question> getAllQuestions() {
		return questionRepo.findAll();
	}

	@Override
	public Question getQuestionByFormId(int formId) {
		return questionRepo.findById(formId).get();
	}

	/*
	 * @Override public List<Question> getQuestionByQuestionType(String
	 * questionType) { return questionRepo.findByQuestionType(questionType); }
	 */
	@Override
	public void createQuestion(Question question) {
		questionRepo.save(question);

	}

	@Override
	public Question getOrCreateQuestion(Question question) {
		List<Question> qu = questionRepo.findByQuestionString(question.getQuestionString());
		if (!qu.isEmpty()) {
			return qu.get(0);
		} else {
			questionRepo.save(question);
			return question;
		}
	}

	@Override
	public void deleteQuestion(Question question) {
		questionRepo.delete(question);

	}

}
