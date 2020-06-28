package com.revature.repo;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

	Stream<Question> findByQuestionType(String questionType); //can be Stream or List


}
