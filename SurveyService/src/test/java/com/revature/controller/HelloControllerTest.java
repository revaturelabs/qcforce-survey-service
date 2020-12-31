package com.revature.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes= {HelloController.class})
class HelloControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testHello() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/hello");
		MvcResult result = mvc.perform(request).andReturn();
		assertEquals("Hello, World", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testHelloWithName() throws Exception {
		mvc.perform(get("/hello?name=Nick")).andExpect(content().string("Hello, Nick"));
	}

}
