package com.revature.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.revature.model.Response;

@Repository
@Transactional
public interface ResponseRepo extends JpaRepository<Response, Integer> {

	@Query("SELECT resp FROM Response resp WHERE resp.batchName = :batchName")
	List<Response> findByBatchName(@Param("batchName")String batchName); //can be Stream or List

}
