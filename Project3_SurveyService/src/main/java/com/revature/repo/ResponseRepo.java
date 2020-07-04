package com.revature.repo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.entity.Form;
import com.revature.entity.Response;

@Repository
public interface ResponseRepo extends JpaRepository<Response, Integer> {

	
	
     @Query("select r from Response r INNER JOIN r.answers ans1 INNER JOIN r.answers ans2 WHERE ans1.answerString = :batchName AND ans2.answerString = :week")
     List<Response> getByWeekAndBatch(@Param("batchName") String batchName, @Param("week") String week);
	
     @Query("select r from Response r INNER JOIN r.answers ans1 WHERE ans1.answerString = :batchName")
     List<Response> getByBatch(@Param("batchName") String batchName);
     
     @Query("select r from Response r INNER JOIN r.answers ans1 WHERE ans1.answerString = :week")
     List<Response> getByWeek(@Param("week") String week);

	
}
