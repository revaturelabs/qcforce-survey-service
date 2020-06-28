package com.revature.repo;

import java.util.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.model.Response;

@Repository
public interface ResponseRepo extends JpaRepository<Response, Integer> {

	Stream<Response> findByBatchName(String batchName); //can be Stream or List

}
