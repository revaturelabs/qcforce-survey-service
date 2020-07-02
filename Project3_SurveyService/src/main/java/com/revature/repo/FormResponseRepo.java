package com.revature.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.FormResponse;

@Repository
public interface FormResponseRepo extends JpaRepository<FormResponse, Integer> {

	@Query("SELECT NEW list(b.batch) FROM FormResponse b")
	List<String> getBatches();

	@Query("SELECT NEW list(b.week) FROM FormResponse b")
	List<String> getWeeks();

	@Query("SELECT b FROM FormResponse b WHERE b.batch = :batch")
	List<FormResponse> getBatchForms(@Param("batch") String batch);

	@Query("SELECT b FROM FormResponse b WHERE b.batch= :batch AND b.week= :week")
	List<FormResponse> getBatchFormsbyWeek(@Param("batch") String batch, @Param("week") String week);

}
