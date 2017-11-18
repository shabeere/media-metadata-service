/**
 * This document is a part of the source code and related artifacts
 * for Starz Play FZLLC  
 *
 * http://www.starzplay.com/
 *
 * Copyright 2017
 *
 */
package com.splay.content.controller;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.splay.util.AppConstants;

/**
 * The test class that contains the junit tests for /media service.
 * The tests covers all the possible filter combinations and verifies
 * the output with expected result.
 * 
 * @author Shabeer Ellath
 * @Version 1.0
 * Created Date: Nov 18, 2017
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MetadataControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * Test method for {@link com.splay.content.controller.MetadataController#media(com.splay.content.bean.MediaRequestParam)}.
	 */
	@Test
	public void testMediaRequestType()
			throws Exception{
		// Only GET should be supported.
		//Test POST request (Expected Http repose 404 - Not found
		mockMvc.perform(post("/media")
				.contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}
	
	/**
	 * Test all the invalid cases by sending the requests with invalid parameters.
	 * 
	 * Test method for {@link com.splay.content.controller.MetadataController#media(com.splay.content.bean.MediaRequestParam)}.
	 */
	@Test
	public void testMediaInvalidRequest()
			throws Exception {
		
		// Test GET request with spaces as filter parameter value
		mockMvc.perform(get("/media").param("filter", "   ")).andExpect(status().isBadRequest());
		
		// Test GET request with empty filter parameter
		mockMvc.perform(get("/media").param("filter", "")).andExpect(status().isBadRequest());
				
		// Test GET request with invalid filter parameter
		mockMvc.perform(get("/media").param("filter", "wrong")).andExpect(status().isBadRequest());
		
		// Test GET request with valid filter and empty level parameter
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "")).andExpect(status().isBadRequest());
		
		// Test GET request with valid filter and spaces as level parameter value
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "  ")).andExpect(status().isBadRequest());
				
		// Test GET request with valid filter and invalid level parameter
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "invalid")).andExpect(status().isBadRequest());
		
		// Test GET request with valid level and invalid filter parameter
		mockMvc.perform(get("/media").param("filter", "incorrect").param("level", "Uncensored")).andExpect(status().isBadRequest());
	}
	
	/**
	 * Test all the valid parameter combinations where a filter still will not be applied.
	 * 
	 * Test method for {@link com.splay.content.controller.MetadataController#media(com.splay.content.bean.MediaRequestParam)}.
	 */
	@Test
	public void testMediaValidRequestNoFilter()
			throws Exception {
		// Test GET request with no parameter.Should return full content without any filtering
		// Expect an OK response with JSON response in UTF-8 charset
		mockMvc.perform(get("/media")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
		
		// Test GET request with valid filter parameter
		// Expect an OK response with JSON response in UTF-8 charset
		mockMvc.perform(get("/media").param("filter", "censoring")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
		
		// Test GET request with valid filter parameter (With spaces that is expected to be trimmed by the application)
		// Expect an OK response with JSON response in UTF-8 charset
		mockMvc.perform(get("/media").param("filter", "  censoring  ")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
		
		// Test GET request with valid filter and level parameter
		// Expect an OK response with JSON response in UTF-8 charset
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "uncensored")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
		
		// Test GET request with No filter and INVALID level parameter
		// Expect an OK response with JSON response in UTF-8 charset since absence of filter makes the other parameters irrelevant
		mockMvc.perform(get("/media").param("level", "Wrong")).andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
	}
	
	/**
	 * Test all the valid parameter combinations where a filter will be applied. 
	 * Checks the resurned JSON for correctness of data.
	 * Checks the length of returned array
	 * 
	 * Test method for {@link com.splay.content.controller.MetadataController#media(com.splay.content.bean.MediaRequestParam)}.
	 */
	@Test
	public void testMediaValidRequestWithFilter()
			throws Exception {
		// Test GET request with valid parameters to enable filtering
		// Expect an OK response with JSON response in UTF-8 charset
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "censored")).andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE));
		
		// Apply 'censored' filter
		// Check only if the 'censored' media under 'censored' entry is present
		// by checking is the value of 'guid' element in the 'media' array end with 'C'
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "censored")).andExpect(status().isOk())
			.andExpect(jsonPath("$.entries[?(@.peg$contentClassification == 'Censored')].media[*].guid").
					value(everyItem(endsWith(AppConstants.CONTENT_CENSORED_GUID_CHAR))));
		
		// Apply 'censored' filter
		// Make sure that the media array contains only one element
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "censored")).andExpect(status().isOk())
			.andExpect(jsonPath("$.entries[?(@.peg$contentClassification == 'Censored')].media[*]", hasSize(1)));
		
		// Apply 'uncensored' filter
		// Check only if the 'uncensored' media under 'censored' entry is present
		// by checking is the value of 'guid' element in the 'media' array  does NOT end with 'C'
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "uncensored")).andExpect(status().isOk())
		.andExpect(jsonPath("$.entries[?(@.peg$contentClassification == 'Censored')].media[*].guid").
				value(everyItem(not((endsWith(AppConstants.CONTENT_CENSORED_GUID_CHAR))))));

		// Apply 'uncensored' filter
		// Make sure that the media array contains only one element
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "uncensored")).andExpect(status().isOk())
			.andExpect(jsonPath("$.entries[?(@.peg$contentClassification == 'Censored')].media[*]", hasSize(1)));
				
		// Apply 'censored' filter
		// Check if non-classified entries are included
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "censored")).andExpect(status().isOk())
		.andExpect(jsonPath("$.entries[?(@.peg$contentClassification == '')].media").exists());
		
		// Apply 'uncensored' filter
		// Check if non-classified entries are included
		mockMvc.perform(get("/media").param("filter", "censoring").param("level", "uncensored")).andExpect(status().isOk())
		.andExpect(jsonPath("$.entries[?(@.peg$contentClassification == '')].media").exists());		
		
	}
}
