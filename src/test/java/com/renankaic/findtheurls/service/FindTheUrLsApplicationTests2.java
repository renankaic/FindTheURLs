package com.renankaic.findtheurls.service;

import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.renankaic.findtheurls.services.CrawledUrlService;
import com.renankaic.findtheurls.services.crawlers.SpiderWebCrawler;

@RunWith(SpringRunner.class)
@WebMvcTest
public class FindTheUrLsApplicationTests2 {

	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CrawledUrlService crawledUrlService;
	
	@MockBean
	SpiderWebCrawler spiderWebCrawlerService;
	
	@Test
	public void contextLoads() throws Exception {
		
		Mockito.when(crawledUrlService.findAll()).thenReturn(
				Collections.emptyList()
		);
		
		MvcResult mvcResult = mockmvc.perform(
				MockMvcRequestBuilders.get("/urls")
				.accept(MediaType.APPLICATION_JSON)
		).andReturn();
		
		System.out.println(mvcResult.getResponse());
		System.out.println("Everything runs ok");
		verify(crawledUrlService).findAll();
		
	}

}

