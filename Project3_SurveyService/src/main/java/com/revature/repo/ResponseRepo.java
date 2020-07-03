package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entity.Response;

@Repository
@Transactional
public interface ResponseRepo extends JpaRepository<Response, Integer> {

	/*
	 * @Query("SELECT resp FROM Response resp WHERE resp.batchName = :batchName")
	 * List<Response> findByBatchName(@Param("batchName")String batchName); //can be
	 * Stream or List
	 */
}
