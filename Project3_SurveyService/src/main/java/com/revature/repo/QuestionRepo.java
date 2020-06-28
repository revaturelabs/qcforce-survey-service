package com.revature.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revature.model.Question;

@Repository
@Transactional
public interface QuestionRepo extends JpaRepository<Question,Integer> {

	@Query("SELECT ques FROM Question ques WHERE ques.questionType=(:questionType)")
	List<Question> findByQuestionType(@Param("questionType")String questionType); //can be Stream or List


}
