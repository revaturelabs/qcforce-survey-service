package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

}
