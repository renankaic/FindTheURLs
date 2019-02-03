package com.renankaic.findtheurls.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.renankaic.findtheurls.domain.CrawledUrl;
import com.renankaic.findtheurls.services.CrawledUrlService;
import com.renankaic.findtheurls.services.crawlers.SpiderWebCrawler;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FindTheUrLsApplicationTest {

	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CrawledUrlService crawledUrlService;
	
	@MockBean
	SpiderWebCrawler spiderWebCrawlerService;
	
	@Test
	public void contextLoads() throws Exception {
		
		//Here I will try to get a Crawled URL with id 1 from the database using the method find() in CrawledUrlService class
		long id = 2;
		when(crawledUrlService.find(id)).thenReturn(new CrawledUrl());
		
		MvcResult mvcResult = mockmvc.perform(
				MockMvcRequestBuilders.get("/urls")
							.accept(MediaType.APPLICATION_JSON)				
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		
		System.out.println(mvcResult.getResponse());
		
	}
	
}

