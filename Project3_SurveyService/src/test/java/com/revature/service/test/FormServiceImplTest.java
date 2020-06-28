package com.revature.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.model.Form;
import com.revature.service.FormServiceImpl;

public class FormServiceImplTest {
	
	private FormServiceImpl formService  =new FormServiceImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {	
	}

	@After
	public void tearDown() throws Exception {
	}

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
		Form f = new Form();
		//f.setFormId(1);
		f.setNewFormTimestamp("2020-05-05");
		formService.createForm(f);

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
