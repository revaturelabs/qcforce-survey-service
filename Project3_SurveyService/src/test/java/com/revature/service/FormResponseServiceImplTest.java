package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mongodb.client.DistinctIterable;
import com.revature.model.FormResponse;
import com.revature.repo.FormResponseRepo;

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
	
	/* SUPPOSED TO BE DONE BY ANDRES AND JOSE :-p
	 * @Test void getBatchNames() {
	 * when(formResponse.getUniqueTask()).thenReturn(new
	 * DistinctIterable<String>()); }
	 */
}
