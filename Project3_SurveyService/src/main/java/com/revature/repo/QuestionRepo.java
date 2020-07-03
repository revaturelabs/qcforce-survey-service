package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entity.Question;

@Repository
@Transactional
public interface QuestionRepo extends JpaRepository<Question, Integer> {

	/*
	 * @Query("SELECT ques FROM Question ques WHERE ques.questionType=(:questionType)"
	 * ) List<Question> findByQuestionType(@Param("questionType") String
	 * questionType); // can be Stream or List
	 */
}
