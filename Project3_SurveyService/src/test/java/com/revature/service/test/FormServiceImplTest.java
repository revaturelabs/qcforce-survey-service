package com.revature.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.model.Form;
import com.revature.service.FormService;
import com.revature.service.FormServiceImpl;

@SpringBootTest()
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class FormServiceImplTest {
	
	
	private FormService formService;
	
	@Autowired
	public void setFormService(FormService formService) {
		this.formService=formService;
	}
	
	private Form form;


	@Test
	public void testSetFormRepo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllForms() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFormById() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateForm() {
		
		form = new Form();
		form.setFormId(1);
		form.setNewFormTimestamp("2020-05-05");
		formService.createForm(form);

	}

	@Test
	public void testUpdateFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteForm() {
		fail("Not yet implemented");
	}

}
