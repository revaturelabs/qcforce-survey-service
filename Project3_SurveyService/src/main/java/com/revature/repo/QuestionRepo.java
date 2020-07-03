package com.revature.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entity.Question;

@Repository
@Transactional
public interface QuestionRepo extends JpaRepository<Question, Integer> {

	@Query("SELECT ques FROM Question ques WHERE ques.questionString = :questionString")
	List<Question> findByQuestionString(@Param("questionString") String questionString); // can be Stream or List

}
