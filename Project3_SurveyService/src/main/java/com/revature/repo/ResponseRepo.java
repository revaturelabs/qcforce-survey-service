package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.model.Response;

@Repository
public interface ResponseRepo extends JpaRepository<Response, Integer> {

}
