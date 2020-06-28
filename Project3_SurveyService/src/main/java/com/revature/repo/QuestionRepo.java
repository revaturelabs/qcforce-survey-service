package com.revature.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.model.Question;

@Repository
@Transactional
public interface QuestionRepo extends JpaRepository<Question,Integer> {

	@Query("SELECT * FROM qcforce_survey.question WHERE question.question_type=(:questionType)")
	List<Question> findByQuestionType(String questionType); //can be Stream or List


}
