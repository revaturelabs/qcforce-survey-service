package com.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.entity.Form;

@Repository
@Transactional
public interface FormRepo extends JpaRepository<Form, Integer> {

}
