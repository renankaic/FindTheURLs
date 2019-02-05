package com.renankaic.findtheurls.resources;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import net.minidev.json.JSONObject;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CrawlerResourceTest {

	MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	CrawlerResource crawlerResource;
	
	private JSONObject requestContentType;
	
	@Before
	public void setup() throws Exception {			
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.crawlerResource).build();
	}
	
	@Test
	public void testfindAllByCrawler() throws Exception {
		
		//Scenario
		//Create a JSON Object to use in content() method in perform below
		requestContentType = new JSONObject();
		requestContentType.put("siteName", "Monkkee");
		requestContentType.put("url", "http://monkkee.com");
		requestContentType.put("depth", 3);
		
		//This test takes some time to finish
		//Action and verify
		mockMvc.perform(post("/urls/find/crawler").content(requestContentType.toJSONString()).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*.siteName", hasItem(is("Monkkee"))));
		
	}
	
	@Test
	public void testfindAllBySpider() throws Exception {
		
		//Scenario
		//Create a JSON Object to use in content() method in perform below
		requestContentType = new JSONObject();
		requestContentType.put("siteName", "Monkkee");
		requestContentType.put("url", "http://monkkee.com");
		requestContentType.put("depth", 10);
		
		//This test takes some time to finish
		//Action and verify
		mockMvc.perform(post("/urls/find/spider").content(requestContentType.toJSONString()).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*.siteName", hasItem(is("Monkkee"))))
			.andDo(print())
			.andReturn();
		
	}
	
	
}
