package com.revature.servicetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.revature.model.FormResponse;
import com.revature.repo.FormResponseRepo;
import com.revature.service.FormResponseServiceImpl;

@ExtendWith(SpringExtension.class)
class FormResponseServiceImplTest {

	@Mock
	FormResponseRepo formRepo;
	@InjectMocks
	FormResponseServiceImpl formResponse;

	@Test
	void findAll() {
		when(formRepo.findAll()).thenReturn(new ArrayList<FormResponse>());
		assertNotNull(formResponse.findAll());
	}

	@Test
	void findById() {
		when(formRepo.findById(2)).thenReturn(Optional.of(new FormResponse()));
		assertNotNull(formResponse.findById(2));
	}

	/*
	 * SUPPOSED TO BE DONE BY ANDRES AND JOSE :-p
	 * 
	 * @Test void getBatchNames() {
	 * when(formResponse.getUniqueTask()).thenReturn(new
	 * DistinctIterable<String>()); }
	 */
}
