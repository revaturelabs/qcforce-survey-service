package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entity.Answer;

@Repository
public interface AnswerRepo extends JpaRepository<Answer,Integer>{

}
