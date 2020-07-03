package com.revature.service.test;

import java.sql.Timestamp;
import java.util.Calendar;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.entity.Form;
import com.revature.service.FormService;

@SpringBootTest()
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
public class FormServiceImplTest {
	
/*
	private FormService formService;
	
	Calendar calendar = Calendar.getInstance();
	java.util.Date now = calendar.getTime();
	
	
	@Autowired
	public void setFormService(FormService formService) {
		this.formService=formService;
	}
	
	private Form form;


	@Test
	public void testGetAllForms() {
		//List<Form> 
		formService.getAllForms();
	}

	@Test
	public void testGetFormById() {
		formService.getFormById(4);
	}

	@Test
	public void testCreateForm() {
		
		form = new Form();
		form.setCreationFormTs(new Timestamp(System.currentTimeMillis()));
		formService.createForm(form);

	}

	@Test
	public void testUpdateFrom() {
		form = new Form();
		form.setFormId(4);
		form.setCreationFormTs(new Timestamp(now.getTime()));  
		formService.updateForm(form);
	}

	@Test
	public void testDeleteForm() {
		form = new Form();
		form.setFormId(3);
		formService.deleteForm(form);
	}
*/
}
