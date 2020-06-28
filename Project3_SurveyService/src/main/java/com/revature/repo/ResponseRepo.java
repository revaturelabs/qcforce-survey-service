package com.revature.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.revature.model.Response;

@Repository
@Transactional
public interface ResponseRepo extends JpaRepository<Response, Integer> {

	@Query("SELECT * FROM qcforce_survey.response WHERE response.batch_name=(:batchName)")
	List<Response> findByBatchName(String batchName); //can be Stream or List

}
