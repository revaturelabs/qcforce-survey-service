package com.revature.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.entity.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Integer> {
	
//	@Query("SELECT NEW list(b.batch) FROM FormResponse b")
//	List<String> getBatches();
//
//	@Query("SELECT b FROM FormResponse b WHERE b.batch= :batch AND b.week= :week")
//	List<FormResponse> getBatchFormsbyWeek(@Param("batch") String batch, @Param("week") String week);
	
	//select distinct(answer_string) from qcforce_survey.answer a where a.question_id = (select id from qcforce_survey.question q where q.question_string = 'What batch are you in?');
	@Query("select distinct a.answerString from Answer a where a.question.id = (select q.id from Question q where q.questionString = :questionString)")
	List<String> getAnswers(@Param("questionString") String questionString); 

}
